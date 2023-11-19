package Medibot.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorDto {

    int code;

    String message;

    public ErrorDto(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
