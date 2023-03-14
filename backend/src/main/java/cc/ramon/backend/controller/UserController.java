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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Configurable
public class UserController {
    @Autowired
    private UserRepository userRepository;
    private final String base = "/user";

    @CrossOrigin
    @PostMapping(base + "/create")
    public User createPaste(@RequestBody UserDto body) {
        User u = new User();
        u.setUsername(body.getUsername());
        u.setPassword(body.getPassword());
        return userRepository.save(u);
    }

    @CrossOrigin
    @DeleteMapping(base + "/{id}")
    public ResponseEntity<String> deletePaste(@PathVariable("id") int id) {
        userRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("User deleted!");
    }

}
