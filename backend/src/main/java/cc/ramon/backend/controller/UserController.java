package cc.ramon.backend.controller;

import cc.ramon.backend.dto.UserDto;
import cc.ramon.backend.models.User;
import cc.ramon.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@Configurable
@CrossOrigin
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    private final String base = "/user";

    @PostMapping(base + "/create")
    public User createPaste(@RequestBody UserDto body) {
        User newUser = new User();
        newUser.setUsername(body.getUsername());
        newUser.setPassword(encoder.encode(body.getPassword()));
        return userRepository.save(newUser);
    }

    @DeleteMapping(base + "/{id}")
    public ResponseEntity<String> deleteUser(Principal principal, @PathVariable("id") int id) {
        Optional<User> requestUser = userRepository.findByUsername(principal.getName());
        if (!requestUser.get().isAdmin())
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden");
        userRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("User deleted!");
    }

    @GetMapping(base + "/all")
    public List<User> allUsers(Principal principal) {
        Optional<User> requestUser = userRepository.findByUsername(principal.getName());
        if (!requestUser.get().isAdmin())
            return null;
        return userRepository.findAll();
    }

    @GetMapping(base + "/self")
    public User getSelfUser(Principal principal) {
        Optional<User> requestUser = userRepository.findByUsername(principal.getName());
        return requestUser.get();
    }

}
