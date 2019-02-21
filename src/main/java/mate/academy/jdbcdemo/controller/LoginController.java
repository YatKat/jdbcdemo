package mate.academy.jdbcdemo.controller;

import mate.academy.jdbcdemo.config.Password;
import mate.academy.jdbcdemo.model.User;
import mate.academy.jdbcdemo.service.UserService;
import mate.academy.jdbcdemo.web.Request;
import mate.academy.jdbcdemo.web.ViewModel;

import javax.servlet.http.Cookie;

public class LoginController implements Controller {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ViewModel process(Request request) {
        String login = request.getParamByName("login");
        String actualPassword = request.getParamByName("password");
        User user = userService.getUserByLogin(login);
        if (user != null) {
            System.out.println("--User is present in BD--");
            if (secureCheck(actualPassword, user)) {
                return processAuthorized(user);
            } else {
                processUnauthorized();
            }
        }
        return processUnauthorized();  //make token and cookies
    }

    private ViewModel processAuthorized(User user) {
        String userToken = user.getToken();
        Cookie cookie = new Cookie("Mate", userToken);
        ViewModel viewModel = new ViewModel("home");
        viewModel.addAttributes("username", user.getName());
        viewModel.addCookies(cookie);
        return viewModel;
    }

    private ViewModel processUnauthorized() {
        ViewModel viewModel = new ViewModel("login");
        viewModel.addAttributes("Error message: ", "Invalid login/password");
        return viewModel;
    }

    private boolean secureCheck(String actualPassword, User user) {
        try {
            if (Password.check(actualPassword, user.getPassword())) {
                System.out.println("---- Password confirmed!------");
                return true;
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        System.out.println("Check of encrypted password is failed!");
        return false;
    }
}
