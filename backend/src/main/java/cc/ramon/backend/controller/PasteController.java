package cc.ramon.backend.controller;

import cc.ramon.backend.dto.PasteDto;
import cc.ramon.backend.repository.PasteRepository;
import cc.ramon.backend.models.Paste;
import cc.ramon.backend.repository.UserRepository;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

import java.util.List;

@RestController
@Configurable
public class PasteController {
    @Autowired
    private PasteRepository pasteRepository;
    @Autowired
    private UserRepository userRepository;
    private final String base = "/paste";

    @CrossOrigin
    @Secured("ROLE_USER")
    @PostMapping(base + "/create")
    public Paste createPaste(@RequestBody PasteDto body) {
        Paste p = new Paste();
        p.setContent(body.getContent());
        p.setTitle(body.getTitle());
        p.setUser(userRepository.getReferenceById(body.getUserId()));
        return pasteRepository.save(p);
    }

    @CrossOrigin
    @Secured("ROLE_USER")
    @DeleteMapping(base + "/{id}")
    public ResponseEntity<String> deletePaste(@PathVariable("id") String id) {
        pasteRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Paste deleted!");
    }

    @CrossOrigin
    @Secured("ROLE_USER")
    @PostMapping(base + "/edit/{id}")
    public Paste editPaste(@RequestBody PasteDto body, @PathVariable("id") String id) {
        Paste p = pasteRepository.getReferenceById(id);
        p.setContent(body.getContent());
        p.setTitle(body.getTitle());
        pasteRepository.save(p);
        return pasteRepository.save(p);
    }

    @CrossOrigin
    @GetMapping(base + "/all")
    public List<Paste> allPaste() {
        return pasteRepository.findAll();
    }

    @CrossOrigin
    @GetMapping(path = base + "/{id}")
    public Paste getPaste(@PathVariable("id") String id) {
        return pasteRepository.getReferenceById(id);
    }

}
