package Medibot.Exception;

import org.springframework.http.HttpStatus;

public class NotFoundIntentException extends MedibotException{
    public NotFoundIntentException() {
        super(HttpStatus.NOT_FOUND, "질문의 의도를 찾을 수 없습니다.", 411);
    }
}
