package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.util.UsersUtil;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryUserRepository implements UserRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepository.class);

    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        UsersUtil.USERS.forEach(this::save);
    }

    @Override
    public User save(User user) {
        if (user.isNew()) {
            user.setId(counter.incrementAndGet());
            repository.put(counter.incrementAndGet(), user);
            return user;
        }
        // treat case: update, but not present in storage
        return repository.computeIfPresent(user.getId(), (id, oldUser) -> user);
    }

    @Override
    public boolean delete(int id) {
        log.info("delete {}", id);
        if (repository.get(id).getId() == id) {
            return repository.remove(id) != null;
        } else {
            return false;
        }
    }


    @Override
    public User get(int id) {
        log.info("get {}", id);
        if (repository.get(id).getId() == id) {
            return repository.get(id);
        } else {
            return null;
        }
    }

    @Override
    public List<User> getAll() {
        log.info("getAll");
        List<User> user = repository.values().stream().collect(Collectors.toList());
        Collections.sort(user, Comparator.comparing(User::getName));
        return user;
    }

    @Override
    public User getByEmail(String email) {
        log.info("getByEmail {}", email);
        List<User> usersAll = getAll();
        User userFind = null;
        for (User user : usersAll) {
            if (user.getEmail() == email) {
                userFind = user;
            }
        }
        return userFind == null ? null : userFind;
    }
}
