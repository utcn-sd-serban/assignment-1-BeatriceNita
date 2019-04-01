package ro.utcn.sd.btn.assig1.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.btn.assig1.model.Question;
import ro.utcn.sd.btn.assig1.model.User;
import ro.utcn.sd.btn.assig1.persistence.api.QuestionRepository;
import ro.utcn.sd.btn.assig1.persistence.api.RepositoryFactory;
import ro.utcn.sd.btn.assig1.persistence.api.UserRepository;

@Component
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class UserSeed implements CommandLineRunner {
    private final RepositoryFactory factory;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        UserRepository repository = factory.createUserRepository();
        if (repository.findAll().isEmpty()) {
            repository.save(new User("otto", "food"));
            repository.save(new User("otto1", "food1"));
            repository.save(new User("otto2", "food2"));
        }
    }
}