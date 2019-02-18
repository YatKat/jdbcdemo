package mate.academy.jdbcdemo.service;

import mate.academy.jdbcdemo.dao.UserDao;
import mate.academy.jdbcdemo.model.User;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getUserByLogin(String login) {
        return userDao.findByLogin(login);
    }

    @Override
    public long getUserIdByLoginAndPassword(String login, String password) {
        return userDao.getUserIDByLoginAndPassword(login, password);
    }

    @Override
    public User createUser(User user) {
        return userDao.createUser(user);
    }

    @Override
    public List<String> getAllNamesUpperCase() {
        List<User> allUsers = userDao.findAll();
        return allUsers.stream()
                .map(User::getName)
                .filter(Objects::nonNull)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }
}
