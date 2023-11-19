package Medibot.Exception;

import org.springframework.http.HttpStatus;

public class NotFoundPillException extends MedibotException{
    public NotFoundPillException() {
        super(HttpStatus.NOT_FOUND, "약을 찾을 수 없습니다.", 412);
    }
}
