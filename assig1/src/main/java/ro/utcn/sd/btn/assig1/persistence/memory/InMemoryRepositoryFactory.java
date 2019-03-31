package ro.utcn.sd.btn.assig1.persistence.memory;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ro.utcn.sd.btn.assig1.persistence.api.QuestionRepository;
import ro.utcn.sd.btn.assig1.persistence.api.RepositoryFactory;
import ro.utcn.sd.btn.assig1.persistence.api.TagRepository;
import ro.utcn.sd.btn.assig1.persistence.api.UserRepository;
import ro.utcn.sd.btn.assig1.persistence.memory.InMemoryQuestionRepository;


@Component
@ConditionalOnProperty(name = "assig1.repository-type", havingValue = "MEMORY")
public class InMemoryRepositoryFactory implements RepositoryFactory {
    private final InMemoryQuestionRepository repository = new InMemoryQuestionRepository();
    private final InMemoryUserRepository repository1 = new InMemoryUserRepository();
    private final InMemoryTagRepository repository2 = new InMemoryTagRepository();


    @Override
    public QuestionRepository createQuestionRepository() {
        return repository;
    }

    @Override
    public UserRepository createUserRepository() {
        return repository1;
    }

    @Override
    public TagRepository createTagRepository() {
        return repository2;
    }
}