package ro.utcn.sd.btn.assig1.persistence.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ro.utcn.sd.btn.assig1.persistence.api.QuestionRepository;
import ro.utcn.sd.btn.assig1.persistence.api.RepositoryFactory;
import ro.utcn.sd.btn.assig1.persistence.api.TagRepository;
import ro.utcn.sd.btn.assig1.persistence.api.UserRepository;

import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "assig1.repository-type", havingValue = "JPA")
public class HibernateRepositoryFactory implements RepositoryFactory {
    private final EntityManager entityManager;

    @Override
    public QuestionRepository createQuestionRepository() {
        return new HibernateQuestionRepository(entityManager);
    }

    @Override
    public UserRepository createUserRepository() {
        return new HibernateUserRepository(entityManager);
    }

    @Override
    public TagRepository createTagRepository() {
        return new HibernateTagRepository(entityManager);
    }

}

