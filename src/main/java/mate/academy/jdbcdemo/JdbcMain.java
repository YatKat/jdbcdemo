package mate.academy.jdbcdemo;

import mate.academy.jdbcdemo.dao.UserDao;
import mate.academy.jdbcdemo.dao.UserDaoImpl;
import mate.academy.jdbcdemo.model.User;
import mate.academy.jdbcdemo.service.UserService;
import mate.academy.jdbcdemo.service.UserServiceImpl;
//HW:
//1. Мати сторінку логін (повинна відображатися в браузері як було на практиці )
//2. Мати сторінку реєстрації (повинна відображатися в браузері)
//3. Мати реалізовані методи для логіну, реєстрації в дао / сервісі
//4. Мати сторінку home (повинна відображатися в браузері)

public class JdbcMain {
    public static void main(String[] args) {
        UserDao userDao =  new UserDaoImpl(ConnectionUtil.getConnection());
        UserService userService =  new UserServiceImpl(userDao);
        User katia = userService.getUserByLogin("AkaKat");
        System.out.println(katia.toString());
    }
}

