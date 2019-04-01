package ro.utcn.sd.btn.assig1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.btn.assig1.exception.QuestionNotFoundException;
import ro.utcn.sd.btn.assig1.model.Question;
import ro.utcn.sd.btn.assig1.model.Tag;
import ro.utcn.sd.btn.assig1.model.User;
import ro.utcn.sd.btn.assig1.persistence.api.QuestionRepository;
import ro.utcn.sd.btn.assig1.persistence.api.RepositoryFactory;

import java.sql.Timestamp;
import java.util.List;

@Component
@Service
@RequiredArgsConstructor
public class QuestionManagementService {

    private final RepositoryFactory repositoryFactory;

    @Transactional
    public List<Question> listQuestions() {
        return repositoryFactory.createQuestionRepository().findAll();
    }

    @Transactional
    public Question askQuestion(User author, Timestamp creationDate, String title, Tag tag, String text) {
        return repositoryFactory.createQuestionRepository().save(new Question(author, creationDate, title, tag, text));
    }

    @Transactional
    public void removeQuestion(int id) {
        QuestionRepository repository = repositoryFactory.createQuestionRepository();
        Question question = repository.findById(id).orElseThrow(QuestionNotFoundException::new);
        repository.remove(question);
    }

    @Transactional
    public List<Question> findQuestionByTitle(String title) {
        return repositoryFactory.createQuestionRepository().findByTitle(title);
    }

    @Transactional
    public List<Question> findQuestionByTag(Tag tag) {
        return repositoryFactory.createQuestionRepository().findByTag(tag);
    }
}
