package andreamarchica.U5W2L5.controllers;

import andreamarchica.U5W2L5.entities.User;
import andreamarchica.U5W2L5.exceptions.BadRequestException;
import andreamarchica.U5W2L5.payloads.users.NewUserDTO;
import andreamarchica.U5W2L5.payloads.users.NewUserResponseDTO;
import andreamarchica.U5W2L5.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UsersService usersService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUserResponseDTO saveUser(@RequestBody @Validated NewUserDTO body, BindingResult validation) throws Exception {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        User newUser = usersService.save(body);
        return new NewUserResponseDTO(newUser.getId());
    }

    @GetMapping("")
    public Page<User> getUsers(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
        return usersService.getUsers(page, size, sortBy);
    }

    @GetMapping("/{userId}")
    public User findById(@PathVariable UUID userId) {
        return usersService.findById(userId);
    }

    @PutMapping("/{userId}")
    public User findAndUpdate(@PathVariable UUID userId, @RequestBody User body) {
        return usersService.findByIdAndUpdate(userId, body);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAndDelete(@PathVariable UUID userId) {
        usersService.findByIdAndDelete(userId);
    }

}
