package Medibot.Dto;

import lombok.Builder;
import lombok.Data;

@Data
public class HospitalDto {

    private String name;
    private String address;
    private String distance;
    private String phone;
    private String place_url;

    @Builder
    public HospitalDto(String name, String address, String distance, String phone, String place_url) {
        this.name = name;
        this.address = address;
        this.distance = distance;
        this.phone = phone;
        this.place_url = place_url;
    }
}
