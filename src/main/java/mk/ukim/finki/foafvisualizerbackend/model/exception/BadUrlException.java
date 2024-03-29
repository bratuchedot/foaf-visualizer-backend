package mk.ukim.finki.foafvisualizerbackend.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadUrlException extends RuntimeException {
    public BadUrlException(String foafUrl) {
        super("Oops! The provided FOAF URL is not valid: %s".formatted(foafUrl));
    }
}
