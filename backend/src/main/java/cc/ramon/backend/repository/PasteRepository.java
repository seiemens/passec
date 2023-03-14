package cc.ramon.backend.repository;

import cc.ramon.backend.models.Paste;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PasteRepository extends JpaRepository<Paste, Integer> {
    Paste findByTitle(String name);
    List<Paste> findAll();

    @Transactional
    void deleteById(String id);
    Paste getReferenceById(String id);
}
