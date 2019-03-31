package ro.utcn.sd.btn.assig1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.btn.assig1.exception.UserNotFoundException;
import ro.utcn.sd.btn.assig1.model.User;
import ro.utcn.sd.btn.assig1.persistence.api.RepositoryFactory;
import ro.utcn.sd.btn.assig1.persistence.api.UserRepository;

import java.util.List;

@Component
@Service
@RequiredArgsConstructor
public class UserManagementService {

    private final RepositoryFactory repositoryFactory;

    @Transactional
    public List<User> listUsers() {
        return repositoryFactory.createUserRepository().findAll();
    }

    @Transactional
    public User addUser(String userName, String password) {
        return repositoryFactory.createUserRepository().save(new User(userName, password));
    }

    @Transactional
    public void removeQuestion(int id) {
        UserRepository repository = repositoryFactory.createUserRepository();
        User user = repository.findById(id).orElseThrow(UserNotFoundException::new);
        repository.remove(user);
    }

    @Transactional
    public User findUser(String userName, String password){
        UserRepository repository = repositoryFactory.createUserRepository();
        User user = repository.findUserInfo(userName,password).orElseThrow(UserNotFoundException::new);
        return user;
    }
}