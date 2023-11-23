package Medibot.Dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AIPillINameAndImageDto {

    private String pillName;

    private String pillImageUrl;

    @Builder
    public AIPillINameAndImageDto(String pillName, String pillImageUrl) {
        this.pillName = pillName;
        this.pillImageUrl = pillImageUrl;
    }
}
