package andreamarchica.U5W2L5.services;

import andreamarchica.U5W2L5.entities.User;
import andreamarchica.U5W2L5.exceptions.BadRequestException;
import andreamarchica.U5W2L5.exceptions.NotFoundException;
import andreamarchica.U5W2L5.payloads.users.NewUserDTO;
import andreamarchica.U5W2L5.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    public User save(NewUserDTO body) throws IOException {
        usersRepository.findByEmail(body.email()).ifPresent(user -> {
            throw new BadRequestException("L'email " + body.email() + " è già stata utilizzata");
        });
        User newUser = new User();
        newUser.setProfileImage("https://ui-avatars.com/api/?name=" + body.name() + "+" + body.surname());
        newUser.setUsername(body.userName());
        newUser.setName(body.name());
        newUser.setEmail(body.email());
        newUser.setSurname(body.surname());
        return usersRepository.save(newUser);
    }

    public Page<User> getUsers(int page, int size, String sort) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return usersRepository.findAll(pageable);
    }

    public User findById(UUID id) {
        return usersRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(UUID id) {
        User found = this.findById(id);
        usersRepository.delete(found);
    }

    public User findByIdAndUpdate(UUID id, User body) {

        User found = this.findById(id);
        found.setUsername(body.getUsername());
        found.setEmail(body.getEmail());
        found.setName(body.getName());
        found.setSurname(body.getSurname());
        found.setProfileImage(body.getProfileImage());
        return usersRepository.save(found);
    }

}