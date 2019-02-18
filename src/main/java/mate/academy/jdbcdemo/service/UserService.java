package mate.academy.jdbcdemo.service;

import mate.academy.jdbcdemo.model.User;

import java.util.List;

public interface UserService {
    User getUserByLogin(String login);
    long getUserIdByLoginAndPassword(String login, String password);
    User createUser(User user);
    List<String> getAllNamesUpperCase();

}
