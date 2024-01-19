package andreamarchica.U5W2L5.payloads.devices;

import andreamarchica.U5W2L5.entities.DeviceStatus;
import andreamarchica.U5W2L5.entities.DeviceType;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record NewDeviceDTO(

        @Nullable
        UUID userId,
        @NotNull(message = "Il tipo di dispositivo è obbligatorio")
        DeviceType deviceType,

        @NotNull(message = "Lo stato del dispositivo è obbligatorio")
        DeviceStatus deviceStatus
) {

}
