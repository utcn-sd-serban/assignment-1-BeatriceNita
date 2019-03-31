package ro.utcn.sd.btn.assig1.persistence.api;

import ro.utcn.sd.btn.assig1.model.Tag;

import java.util.List;
import java.util.Optional;

public interface TagRepository {

    Tag save(Tag t);

    Optional<Tag> findById(int id);

    Optional<Tag> findByName(String name);

    void remove(Tag tag);

    List<Tag> findAll();
}
