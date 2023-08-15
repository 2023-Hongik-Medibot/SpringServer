package Medibot.Dto;

import lombok.Builder;
import lombok.Data;

@Data
public class ResPillDto {
    private String name;
    private String efcyQesitm; // 효과
    private String sideEffect; // 부작용
    private String precaution; // 주의사항
    private String method; // 복용법

    @Builder

    public ResPillDto(String name, String efcyQesitm, String sideEffect, String precaution, String method) {
        this.name = name;
        this.efcyQesitm = efcyQesitm;
        this.sideEffect = sideEffect;
        this.precaution = precaution;
        this.method = method;
    }
}
