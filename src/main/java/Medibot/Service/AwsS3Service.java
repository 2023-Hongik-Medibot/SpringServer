package Medibot.Service;

import Medibot.Domain.Pill;
import Medibot.Dto.AwsS3;
import Medibot.Dto.pillImageResponseDto;
import Medibot.Dto.pillNameAndImageUrlDto;
import Medibot.Repository.PillRepository;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AwsS3Service {

    private final AmazonS3 amazonS3;
    private final PillRepository pillRepository;

    private static final String AI_SERVER_HOST  = "http://ec2-13-209-89-237.ap-northeast-2.compute.amazonaws.com:8000/ask/first/";


    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public pillNameAndImageUrlDto upload(List<MultipartFile> multipartFile, String dirName) throws IOException {
//    public List<AwsS3> upload(List<MultipartFile> multipartFile, String dirName) throws IOException {

        List<String> urls = new ArrayList<>();
        List<AwsS3> awsS3s = new ArrayList<>();

        multipartFile.stream()
                .forEach((multipartFile1 -> {
                    try {
                       File file = convertMultipartFileToFile(multipartFile1)
                                .orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File convert fail"));

                        AwsS3 awsS3 = upload(file, dirName);
                        awsS3s.add(awsS3);

                        urls.add(awsS3.getPath());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }));

        String pillName = getPillName(awsS3s);

        return pillNameAndImageUrlDto.builder()
                .pillName(pillName)
                .imageUrls(urls)
                .build();


//        return pillImageResponseDto.builder()
//                .s3path(urls)
//                .pillName(pillName)
//                .build();
//        return pillImageResponseDto.builder()
//                .s3path(urls)
//                .pillName("타이레놀")
//                .build();
    }

    private String getPillName(List<AwsS3> awsS3){
        URI url = URI.create(AI_SERVER_HOST);

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject body = new JSONObject();

        body.put("body", awsS3);

        HttpEntity<String> entity = new HttpEntity<>(body.toString(), headers);

        ResponseEntity<JSONObject> response = restTemplate.exchange(url, HttpMethod.POST, entity, JSONObject.class);

        // DB에서 해당 특징 가진 알약 구분하기
        String pillShape = response.getBody().get("shape").toString();
        String frontSign = response.getBody().get("frontSign").toString();
        String backSign = response.getBody().get("backSign").toString();

        Pill thepill = pillRepository.findByShapeAndFrontSignAndAndBackSign(pillShape, frontSign, backSign);

        return thepill.getItemName();
    }

    private AwsS3 upload(File file, String dirName) {
        String key = randomFileName(file, dirName);
        String path = putS3(file, key);

        removeFile(file);

        return AwsS3
                .builder()
                .key(key)
                .path(path)
                .build();
    }

    private String randomFileName(File file, String dirName) {
        return dirName + "/" + UUID.randomUUID() + file.getName();
    }

    private String putS3(File uploadFile, String fileName) {
        amazonS3.putObject(new PutObjectRequest(bucket, fileName, uploadFile)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        return getS3(bucket, fileName);
    }

    private String getS3(String bucket, String fileName) {
        return amazonS3.getUrl(bucket, fileName).toString();
    }

    private void removeFile(File file) {
        file.delete();
    }

    public Optional<File> convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
        File file = new File(System.getProperty("user.dir") + "/" + multipartFile.getOriginalFilename());

        multipartFile.transferTo(file);
        return Optional.of(file);

//        File file = new File(System.getProperty("user.dir") + "/" + multipartFile.getOriginalFilename());

//        if (file.createNewFile()) {
//            try (FileOutputStream fos = new FileOutputStream(file)){
//                fos.write(multipartFile.getBytes());
//            }
//            return Optional.of(file);
//        }
//        return Optional.empty();
    }

    public void remove(AwsS3 awsS3) {
        if (!amazonS3.doesObjectExist(bucket, awsS3.getKey())) {
            throw new AmazonS3Exception("Object " +awsS3.getKey()+ " does not exist!");
        }
        amazonS3.deleteObject(bucket, awsS3.getKey());
    }
}
