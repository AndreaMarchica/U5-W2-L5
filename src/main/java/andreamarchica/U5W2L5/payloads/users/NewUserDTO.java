package andreamarchica.U5W2L5.payloads.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewUserDTO(
        @NotEmpty(message = "L'username è obbligatorio")
        @Size(min = 5, max = 15, message = "L'username deve avere minimo 5 caratteri")
        String userName,
        @NotEmpty(message = "Il nome è obbligatorio")
        @Size(min = 3, max = 30, message = "Il nome deve avere minimo 3 caratteri, massimo 30")
        String name,
        @NotEmpty(message = "Il cognome è obbligatorio")
        @Size(max = 40, message = "Il cognome deve avere massimo 40 caratteri")
        String surname,
        @NotEmpty(message = "L'email è obbligatoria")
        @Email(message = "L'email inserita non è valida")
        String email
) {
}
