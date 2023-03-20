package cc.ramon.backend.repository;

import cc.ramon.backend.models.Paste;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PasteRepository extends JpaRepository<Paste, Integer> {

    List<Paste> findAll();

    @Transactional
    void deleteById(String id);

    Optional<Paste> getReferenceById(String id);

    List<Paste> findAllByUserId(int userId);
}
