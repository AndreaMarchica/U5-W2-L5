package andreamarchica.U5W2L5.controllers;

import andreamarchica.U5W2L5.entities.User;
import andreamarchica.U5W2L5.exceptions.BadRequestException;
import andreamarchica.U5W2L5.payloads.users.NewUserDTO;
import andreamarchica.U5W2L5.payloads.users.NewUserResponseDTO;
import andreamarchica.U5W2L5.services.AuthService;
import andreamarchica.U5W2L5.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UsersService usersService;
    @Autowired
    AuthService authService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUserResponseDTO saveUser(@RequestBody @Validated NewUserDTO body, BindingResult validation) throws Exception {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        User newUser = authService.save(body);
        return new NewUserResponseDTO(newUser.getId());
    }

    @GetMapping("")
    public Page<User> getUsers(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size,
                               @RequestParam(defaultValue = "id") String sortBy) {
        return usersService.getUsers(page, size, sortBy);
    }

    @GetMapping("/{userId}")
    public User findById(@PathVariable UUID userId) {
        return usersService.findById(userId);
    }

    @PutMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User findAndUpdate(@PathVariable UUID userId, @RequestBody User body) {
        return usersService.findByIdAndUpdate(userId, body);
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAndDelete(@PathVariable UUID userId) {
        usersService.findByIdAndDelete(userId);
    }
    @PatchMapping("/{authorId}/profileimage")
    public User uploadProfileImage(@RequestParam("profileimage") MultipartFile file, @PathVariable UUID userId) {
        try {
            return usersService.uploadProfileImage(userId, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/me")
    public User getProfile(@AuthenticationPrincipal User currentUser) {
        // @AuthenticationPrincipal permette di accedere ai dati dell'utente attualmente autenticato
        // (perchè avevamo estratto l'id dal token e cercato l'utente nel db)
        return currentUser;
    }


    @PutMapping("/me")
    public User getMeAndUpdate(@AuthenticationPrincipal User currentUser, @RequestBody User body) {
        return usersService.findByIdAndUpdate(currentUser.getId(), body);
    }

    @DeleteMapping("/me")
    public void getMeAnDelete(@AuthenticationPrincipal User currentUser) {
        usersService.findByIdAndDelete(currentUser.getId());
    }
}
