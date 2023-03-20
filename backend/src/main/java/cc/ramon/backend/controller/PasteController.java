package cc.ramon.backend.controller;

import cc.ramon.backend.dto.PasteDto;
import cc.ramon.backend.models.Paste;
import cc.ramon.backend.models.User;
import cc.ramon.backend.repository.PasteRepository;
import cc.ramon.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@Configurable
@CrossOrigin
public class PasteController {
    @Autowired
    private PasteRepository pasteRepository;
    @Autowired
    private UserRepository userRepository;
    private final String base = "/paste";

    @PostMapping(base + "/create")
    public Paste createPaste(Principal principal, @RequestBody PasteDto body) {
        Optional<User> user = userRepository.findByUsername(principal.getName());
        Paste newPaste = new Paste();
        newPaste.setContent(body.getContent());
        newPaste.setTitle(body.getTitle());
        newPaste.setUser(user.get());
        return pasteRepository.save(newPaste);
    }

    @DeleteMapping(base + "/{id}")
    public ResponseEntity<String> deletePaste(Principal principal, @PathVariable("id") String id) {
        Optional<Paste> pasteOptional = pasteRepository.getReferenceById(id);

        if (isUserNotOwningPaste(principal, pasteOptional))
            return null;

        pasteRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Paste deleted!");
    }

    @PostMapping(base + "/edit/{id}")
    public Paste editPaste(Principal principal, @RequestBody PasteDto body, @PathVariable("id") String id) {
        Optional<Paste> pasteOptional = pasteRepository.getReferenceById(id);

        if (isUserNotOwningPaste(principal, pasteOptional))
            return null;

        pasteOptional.get().setContent(body.getContent());
        pasteOptional.get().setTitle(body.getTitle());
        return pasteRepository.save(pasteOptional.get());

    }

    @GetMapping(base + "/all")
    public List<Paste> allPaste(Principal principal) {
        Optional<User> user = userRepository.findByUsername(principal.getName());
        return pasteRepository.findAllByUserId(user.get().getId());
    }

    @GetMapping(path = base + "/view/{id}")
    public Paste getPaste(@PathVariable("id") String id) {
        Optional<Paste> paste = pasteRepository.getReferenceById(id);
        return paste.orElse(null);
    }

    private boolean isUserNotOwningPaste(Principal principal, Optional<Paste> paste) {
        Optional<User> u = userRepository.findByUsername(principal.getName());

        if (paste.isEmpty()) {
            return true;
        }

        return !Objects.equals(u.get().getId(), paste.get().getUser().getId());
    }

}
