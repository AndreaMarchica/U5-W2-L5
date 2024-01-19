package andreamarchica.U5W2L5.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Getter
@ToString
@Table(name = "devices")
public class Device {
    @Id
    @GeneratedValue
    private UUID id;
    @Enumerated(EnumType.STRING)
    private DeviceStatus deviceStatus;
    @Enumerated(EnumType.STRING)
    private DeviceType deviceType;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    public void setDeviceStatus(DeviceStatus deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

