package mate.academy.jdbcdemo.dao;

import mate.academy.jdbcdemo.model.User;

import java.util.List;

public interface UserDao {
    User findByLogin(String login);

    List<User> findAll();

    long getUserIDByLoginAndPassword(String login, String password);

    User createUser(User user);

    User findByToken(String token);
}
