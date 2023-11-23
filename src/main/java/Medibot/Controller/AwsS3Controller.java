package Medibot.Controller;

import Medibot.Dto.*;
import Medibot.Service.AwsS3Service;
import Medibot.Service.PillService;
import com.nimbusds.jose.shaded.json.parser.ParseException;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Test;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/s3")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AwsS3Controller {

    private final AwsS3Service awsS3Service;
    private final PillService pillService;

    @PostMapping("/pillImage")
    public List<pillImageResponseDto> upload(@RequestPart(required = false) List<MultipartFile> multipartFile) throws IOException, ParseException {
//    public List<AwsS3> upload(@RequestPart(required = false) List<MultipartFile> multipartFile) throws IOException {

        System.out.println(multipartFile.size());
        System.out.println(multipartFile);
        // Real
        List<AIPillINameAndImageDto> pillNameAndImageUrlDto = awsS3Service.upload(multipartFile,"upload");


        // Test
//        List<String> urls = new ArrayList<>();
//        List<String> pillNames = new ArrayList<>();
//        pillNames.add("타이레놀");
//        pillNames.add("타세놀");
//        urls.add("https://medibot2023.s3.ap-northeast-2.amazonaws.com/upload/5dc15cb9-b5e3-469f-8d45-e061c1c220aftestPill2.jpeg");
//        urls.add("https://medibot2023.s3.ap-northeast-2.amazonaws.com/upload/7fcd17dd-9625-4958-b169-df88d88b0724testPill.jpeg");
//        pillNameAndImageUrlDto pillNameAndImageUrlDto = Medibot.Dto.pillNameAndImageUrlDto.builder()
//                .pillName(pillNames)
//                .imageUrls(urls)
//                .build();

        return pillService.pillInfo(pillNameAndImageUrlDto);
    }

    @DeleteMapping("/pillImage")
    public void remove(AwsS3 awsS3) {
        awsS3Service.remove(awsS3);
    }
}
