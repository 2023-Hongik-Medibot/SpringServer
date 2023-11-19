package Medibot.Controller;

import Medibot.Dto.ErrorDto;
import Medibot.Exception.NotFoundIntentException;
import Medibot.Exception.NotFoundPillException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(NotFoundIntentException.class)
    public ResponseEntity<ErrorDto> notFoundIntent(NotFoundIntentException e){
        ErrorDto errorDto = new ErrorDto(e.getCode(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }

    @ExceptionHandler(NotFoundPillException.class)
    public ResponseEntity<ErrorDto> notFoundPill(NotFoundPillException e){
        System.out.println(e.getMessage());
        ErrorDto errorDto = new ErrorDto(e.getCode(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }
}
