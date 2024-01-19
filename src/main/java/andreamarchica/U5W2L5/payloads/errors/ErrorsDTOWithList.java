package andreamarchica.U5W2L5.payloads.errors;

import java.util.Date;
import java.util.List;

public record ErrorsDTOWithList(
        String message,
        Date timestamp,
        List<String> errorsList
) {
}