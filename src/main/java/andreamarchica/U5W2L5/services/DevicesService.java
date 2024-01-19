package andreamarchica.U5W2L5.services;

import andreamarchica.U5W2L5.entities.Device;
import andreamarchica.U5W2L5.entities.User;
import andreamarchica.U5W2L5.exceptions.NotFoundException;
import andreamarchica.U5W2L5.payloads.devices.NewDeviceDTO;
import andreamarchica.U5W2L5.repositories.DevicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DevicesService {
    @Autowired
    private DevicesRepository devicesRepository;
    @Autowired
    private UsersService usersService;

    public Device save(NewDeviceDTO body) {
        User user = usersService.findById(body.userId());
        Device newDevice = new Device();
        newDevice.setDeviceStatus(body.deviceStatus());
        newDevice.setDeviceType(body.deviceType());
        newDevice.setUser(user);
        return devicesRepository.save(newDevice);
    }

    public List<Device> getDevices() {
        return devicesRepository.findAll();
    }

    public Device findById(UUID id) {
        return devicesRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(UUID id) {
        Device found = this.findById(id);
        devicesRepository.delete(found);
    }

    public Device findByIdAndUpdate(UUID id, NewDeviceDTO body) {
        Device found = this.findById(id);
        found.setDeviceType(body.deviceType());
        found.setDeviceStatus(body.deviceStatus());

        if (found.getUser().getId() != body.userId()) {
            User newUser = usersService.findById(body.userId());
            found.setUser(newUser);
        }

        return devicesRepository.save(found);
    }

    public List<Device> findByUser(UUID userId) {
        User user = usersService.findById(userId);
        return devicesRepository.findByUser(user);
    }
}