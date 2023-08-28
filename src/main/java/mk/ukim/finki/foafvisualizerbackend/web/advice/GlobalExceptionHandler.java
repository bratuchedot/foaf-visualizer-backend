package mk.ukim.finki.foafvisualizerbackend.web.advice;

import mk.ukim.finki.foafvisualizerbackend.model.exception.BadUrlException;
import mk.ukim.finki.foafvisualizerbackend.model.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadUrlException.class)
    public ResponseEntity<ExceptionResponse> handleBadUrlException(BadUrlException exception) {
        var response = new ExceptionResponse(HttpStatus.BAD_REQUEST.name(), exception.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
