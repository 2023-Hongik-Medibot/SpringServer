package Medibot.Controller;

import Medibot.Dto.AwsS3;
import Medibot.Dto.ResPillDto;
import Medibot.Dto.pillImageResponseDto;
import Medibot.Dto.pillNameAndImageUrlDto;
import Medibot.Service.AwsS3Service;
import Medibot.Service.PillService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/s3")
@RequiredArgsConstructor
public class AwsS3Controller {

    private final AwsS3Service awsS3Service;
    private final PillService pillService;

    @PostMapping("/pillImage")
    public List<pillImageResponseDto> upload(@RequestPart(required = false) List<MultipartFile> multipartFile) throws IOException {
//    public List<AwsS3> upload(@RequestPart(required = false) List<MultipartFile> multipartFile) throws IOException {

        // Real
        pillNameAndImageUrlDto pillNameAndImageUrlDto = awsS3Service.upload(multipartFile,"upload");


        // Test
//        List<String> urls = new ArrayList<>();
//        urls.add("https://medibot2023.s3.ap-northeast-2.amazonaws.com/upload/71afa621-407b-4c14-8f87-a937fca221b2javascriptLogo.jpeg");
//        urls.add("https://medibot2023.s3.ap-northeast-2.amazonaws.com/upload/767c33ff-b70c-4f80-8dcf-407a4b14904djavascriptLogo.jpeg");
//        pillNameAndImageUrlDto pillNameAndImageUrlDto = Medibot.Dto.pillNameAndImageUrlDto.builder()
//                .pillName("타이레놀")
//                .imageUrls(urls)
//                .build();

        return pillService.pillInfo(pillNameAndImageUrlDto);
    }

    @DeleteMapping("/pillImage")
    public void remove(AwsS3 awsS3) {
        awsS3Service.remove(awsS3);
    }
}
