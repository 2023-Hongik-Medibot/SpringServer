package Medibot.Dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@Builder
@NoArgsConstructor
public class HosAndPharDto {

    private String name;
    private String address;
    private String distance;
    private String phone;
    private String place_url;

    @Builder
    public HosAndPharDto(String name, String address, String distance, String phone, String place_url) {
        this.name = name;
        this.address = address;
        this.distance = distance;
        this.phone = phone;
        this.place_url = place_url;
    }
}
