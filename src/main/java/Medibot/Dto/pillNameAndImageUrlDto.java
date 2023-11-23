package Medibot.Dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class pillNameAndImageUrlDto {

    private List<AIPillINameAndImageDto> pillName;

//    private List<String> imageUrls;

    @Builder
    public pillNameAndImageUrlDto(List<AIPillINameAndImageDto> pillName) {
        this.pillName = pillName;
    }
//    public pillNameAndImageUrlDto(List<String> pillName, List<String> imageUrls) {
//        this.pillName = pillName;
//        this.imageUrls = imageUrls;
//    }


}
