package ro.utcn.sd.btn.assig1.persistence.memory;

import ro.utcn.sd.btn.assig1.model.Question;
import ro.utcn.sd.btn.assig1.model.User;
import ro.utcn.sd.btn.assig1.persistence.api.QuestionRepository;
import ro.utcn.sd.btn.assig1.persistence.api.UserRepository;
import ro.utcn.sd.btn.assig1.persistence.jdbc.UserMapper;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryUserRepository implements UserRepository {
    private final Map<Integer, User> data = new ConcurrentHashMap<>();
    private final AtomicInteger currentId = new AtomicInteger(0);

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            user.setId(currentId.incrementAndGet());
        }
        data.put(user.getId(), user);
        return user;
    }

    @Override
    public void remove(User user) {
        data.remove(user.getId());
    }

    @Override
    public Optional<User> findById(int id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(data.values());
    }
    @Override
    public Optional<User> findUserInfo(String userName, String password) {
        return null;
    }
}
