package Medibot.Dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class pillImageResponseDto {

    private String S3key;

    private String S3path;

    private String pillName;

    @Builder
    public pillImageResponseDto(String s3key, String s3path, String pillName) {
        S3key = s3key;
        S3path = s3path;
        this.pillName = pillName;
    }
}
