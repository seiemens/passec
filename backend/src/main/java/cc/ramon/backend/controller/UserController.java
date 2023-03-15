package cc.ramon.backend.controller;

import cc.ramon.backend.dto.PasteDto;
import cc.ramon.backend.dto.UserDto;
import cc.ramon.backend.models.Paste;
import cc.ramon.backend.models.User;
import cc.ramon.backend.repository.PasteRepository;
import cc.ramon.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Configurable
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    private final String base = "/user";

    @CrossOrigin
    @PostMapping(base + "/create")
    public User createPaste(@RequestBody UserDto body) {
        User newUser = new User();
        newUser.setUsername(body.getUsername());
        newUser.setPassword(encoder.encode(body.getPassword()));
        return userRepository.save(newUser);
    }

    @CrossOrigin
    @Secured("ROLE_ADMIN")
    @DeleteMapping(base + "/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
        userRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("User deleted!");
    }

    @CrossOrigin
    @Secured("ROLE_ADMIN")
    @GetMapping(base + "/all")
    public List<User> allUsers() {
        return userRepository.findAll();
    }

}
