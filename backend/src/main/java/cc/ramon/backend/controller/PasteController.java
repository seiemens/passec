package cc.ramon.backend.controller;

import cc.ramon.backend.repository.PasteRepository;
import cc.ramon.backend.models.Paste;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Configurable
public class PasteController {
    @Autowired
    private PasteRepository pasteRepository;
    private final String base = "/paste";

    @CrossOrigin
    @PostMapping(base + "/create")
    public Paste createPaste(@RequestBody Paste body) {
        Paste p = new Paste();
        p.setContent(body.getContent());
        p.setTitle(body.getTitle());
        return pasteRepository.save(p);
    }

    @CrossOrigin
    @GetMapping(base + "/all")
    public List<Paste> allVideos() {
        return pasteRepository.findAll();
    }

    @CrossOrigin
    @GetMapping(path = base + "/{id}/{key}")
    public Paste download(@PathVariable("id") String id, @PathVariable("key") String key) {
        return null;
    }

}
