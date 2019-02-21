package mate.academy.jdbcdemo.dao;

import mate.academy.jdbcdemo.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserDaoImpl extends AbstractDao implements UserDao {

    public UserDaoImpl(Connection connection) {
        super(connection);
    }

    public User findByLogin(String login) {
        String sql = "SELECT * FROM user WHERE login = \"" + login + "\"";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            return resultSet.next() ? getUser(resultSet) : null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM user";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                users.add(getUser(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public long getUserIDByLoginAndPassword(String login, String password) {
        String sql = "SELECT user_id FROM user WHERE login = ? AND password = ?";
        PreparedStatement statement = null;
        long id = 0;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
               id = resultSet.getLong("user_id");
            }
        } catch (SQLException e) {
            System.out.println("SQL custom message--------There is no such user in DB------");
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public User createUser(User user) {
        user.setToken(getRandomToken());
        String sql = "INSERT INTO user (name, login, password, token) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getToken());
            statement.execute();
            user.setId(findByLogin(user.getLogin()).getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User findByToken(String token) {
        String sql = "SELECT * FROM user WHERE token = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, token);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next() ? getUser(resultSet) : null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private User getUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("user_id"));
        user.setLogin(resultSet.getString("login"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));
        user.setToken(resultSet.getString("token"));
        return user;
    }

    private String getRandomToken(){
        return UUID.randomUUID().toString();
    }
}
