package Medibot.Controller;

import Medibot.Dto.AwsS3;
import Medibot.Service.AwsS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/s3")
@RequiredArgsConstructor
public class AwsS3Controller {

    private final AwsS3Service awsS3Service;

    @PostMapping("/pillImage")
    public AwsS3 upload(@RequestPart(value="file",required = false) MultipartFile multipartFile) throws IOException {
        return awsS3Service.upload(multipartFile,"upload");
    }

    @DeleteMapping("/pillImage")
    public void remove(AwsS3 awsS3) {
        awsS3Service.remove(awsS3);
    }
}
