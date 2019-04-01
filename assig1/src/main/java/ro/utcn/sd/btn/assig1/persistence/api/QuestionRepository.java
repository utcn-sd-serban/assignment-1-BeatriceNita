package ro.utcn.sd.btn.assig1.persistence.api;

import ro.utcn.sd.btn.assig1.model.Question;
import ro.utcn.sd.btn.assig1.model.Tag;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository {

    Question save(Question q);

    Optional<Question> findById(int id);

    List<Question> findByTag(Tag tag);

    List<Question> findByTitle(String title);

    void remove(Question question);

    List<Question> findAll();
}
