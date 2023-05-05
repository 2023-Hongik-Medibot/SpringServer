package Medibot.Dto;

import lombok.Data;

@Data
public class ReqHospitalDto {
    private String place;
    private String category;

    public ReqHospitalDto(String place, String category) {
        this.place = place;
        this.category = category;
    }
}
