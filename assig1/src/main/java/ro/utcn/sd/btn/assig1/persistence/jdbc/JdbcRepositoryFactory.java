package ro.utcn.sd.btn.assig1.persistence.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ro.utcn.sd.btn.assig1.persistence.api.QuestionRepository;
import ro.utcn.sd.btn.assig1.persistence.api.RepositoryFactory;
import ro.utcn.sd.btn.assig1.persistence.api.TagRepository;
import ro.utcn.sd.btn.assig1.persistence.api.UserRepository;
import ro.utcn.sd.btn.assig1.persistence.jdbc.JdbcQuestionRepository;


@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "assig1.repository-type", havingValue = "JDBC")
public class JdbcRepositoryFactory implements RepositoryFactory {
    private final JdbcTemplate template;

    @Override
    public QuestionRepository createQuestionRepository() {
        return new JdbcQuestionRepository(template);
    }

    @Override
    public UserRepository createUserRepository() {
        return new JdbcUserRepository(template);
    }

    @Override
    public TagRepository createTagRepository() {
        return new JdbcTagRepository(template);
    }
}