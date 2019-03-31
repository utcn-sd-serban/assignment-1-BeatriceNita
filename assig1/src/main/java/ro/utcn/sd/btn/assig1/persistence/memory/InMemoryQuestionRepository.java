package ro.utcn.sd.btn.assig1.persistence.memory;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ro.utcn.sd.btn.assig1.model.Question;
import ro.utcn.sd.btn.assig1.model.Tag;
import ro.utcn.sd.btn.assig1.persistence.api.QuestionRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryQuestionRepository implements QuestionRepository {
    private final Map<Integer, Question> data = new ConcurrentHashMap<>();
    private final AtomicInteger currentId = new AtomicInteger(0);

    @Override
    public Question save(Question question) {
        if (question.getId() == null) {
            question.setId(currentId.incrementAndGet());
        }
        data.put(question.getId(), question);
        return question;
    }

    @Override
    public void remove(Question question) {
        data.remove(question.getId());
    }

    @Override
    public Optional<Question> findById(int id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public List<Question> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public List<Question> findByTag(Tag tag) {
        return new ArrayList<>(data.values());
    }

    @Override
    public List<Question> findByTitle(String title) {
        return new ArrayList<>(data.values());
    }


}

