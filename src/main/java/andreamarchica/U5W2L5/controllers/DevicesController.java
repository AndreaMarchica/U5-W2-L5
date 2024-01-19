package andreamarchica.U5W2L5.controllers;

import andreamarchica.U5W2L5.entities.Device;
import andreamarchica.U5W2L5.exceptions.BadRequestException;
import andreamarchica.U5W2L5.payloads.devices.NewDeviceDTO;
import andreamarchica.U5W2L5.services.DevicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/devices")
public class DevicesController {
    @Autowired
    DevicesService devicesService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Device saveDevice(@RequestBody @Validated NewDeviceDTO body, BindingResult validation) {

        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return devicesService.save(body);
    }

    @GetMapping("")
    public List<Device> getDevices(@RequestParam(required = false) UUID userId) {
        if (userId != null) return devicesService.findByUser(userId);
        else return devicesService.getDevices();
    }

    @GetMapping("/{deviceId}")
    public Device findById(@PathVariable UUID deviceId) {
        return devicesService.findById(deviceId);
    }

    @PutMapping("/{deviceId}")
    public Device findAndUpdate(@PathVariable UUID deviceId, @RequestBody NewDeviceDTO body) {
        return devicesService.findByIdAndUpdate(deviceId, body);
    }

    @DeleteMapping("/{deviceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAndDelete(@PathVariable UUID deviceId) {
        devicesService.findByIdAndDelete(deviceId);
    }
}
