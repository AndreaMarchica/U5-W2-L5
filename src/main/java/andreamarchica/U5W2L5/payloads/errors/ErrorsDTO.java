package andreamarchica.U5W2L5.payloads.errors;

import java.util.Date;

public record ErrorsDTO(
        String message,
        Date timestamp) {
    public ErrorsDTO(String message, Date timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }
}