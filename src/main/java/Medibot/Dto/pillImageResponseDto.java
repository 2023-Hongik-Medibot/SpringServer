package Medibot.Dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class pillImageResponseDto {

//    private List<String> S3key;

    private List<String> S3path;    // 사용자가 업로드한 약 사진 url

    private String pillName;        // 약 이름

    private String pillImage;       // 약 사진

    private String efcyQesitm;      // 효과

    private String sideEffect;      // 부작용

    private String precaution;      // 주의사항

    private String method;          // 복용법

    @Builder
    public pillImageResponseDto(List<String> s3path, String pillName, String pillImage, String efcyQesitm, String sideEffect, String precaution, String method) {
        S3path = s3path;
        this.pillName = pillName;
        this.pillImage = pillImage;
        this.efcyQesitm = efcyQesitm;
        this.sideEffect = sideEffect;
        this.precaution = precaution;
        this.method = method;
    }
}
