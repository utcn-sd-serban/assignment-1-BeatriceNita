package ro.utcn.sd.btn.assig1.persistence.api;

import ro.utcn.sd.btn.assig1.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> findAll();

    User save(User user);

    void remove(User user);

    Optional<User> findById(int id);

    Optional<User> findUserInfo(String userName, String password);
}
