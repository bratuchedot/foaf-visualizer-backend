package mk.ukim.finki.foafvisualizerbackend.model.response;

import java.time.LocalDateTime;

public record ExceptionResponse(String status, String message, LocalDateTime timestamp) {
}
