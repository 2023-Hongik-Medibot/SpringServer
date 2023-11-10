package Medibot.Dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class pillNameAndImageUrlDto {

    private String pillName;

    private List<String> imageUrls;

    @Builder
    public pillNameAndImageUrlDto(String pillName, List<String> imageUrls) {
        this.pillName = pillName;
        this.imageUrls = imageUrls;
    }
}
