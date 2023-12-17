package mk.ukim.finki.foafvisualizerbackend.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadResourceException extends  RuntimeException {
    public BadResourceException() {
        super("Oops! The provided FOAF resource is not valid. Try using <#me>");
    }
}
