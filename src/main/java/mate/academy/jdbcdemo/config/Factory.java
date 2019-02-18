package mate.academy.jdbcdemo.config;

import mate.academy.jdbcdemo.ConnectionUtil;
import mate.academy.jdbcdemo.controller.LoginController;
import mate.academy.jdbcdemo.controller.RegistrationController;
import mate.academy.jdbcdemo.dao.UserDao;
import mate.academy.jdbcdemo.dao.UserDaoImpl;
import mate.academy.jdbcdemo.service.UserService;
import mate.academy.jdbcdemo.service.UserServiceImpl;

import java.sql.Connection;

public class Factory {
    private  static  final Connection CONNECTION;
    private Factory(){

    }

    static {
        CONNECTION = ConnectionUtil.getConnection();
    }

    public static RegistrationController getRegisterController(){
        return new RegistrationController(getUserService());
    }

    public static LoginController getLoginController(){
        return new LoginController(getUserService());
    }



    public static UserService getUserService(){
        return new UserServiceImpl(getUserDao());
    }

    public  static UserDao getUserDao(){
        return  new UserDaoImpl(CONNECTION);
    }


}
