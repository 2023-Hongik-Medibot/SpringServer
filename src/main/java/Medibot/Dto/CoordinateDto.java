package Medibot.Dto;

import lombok.Data;

@Data
public class CoordinateDto {

    private String x;
    private String y;

    public CoordinateDto(String x, String y) {
        this.x = x;
        this.y = y;
    }
}
