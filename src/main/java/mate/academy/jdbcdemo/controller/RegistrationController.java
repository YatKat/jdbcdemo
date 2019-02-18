package mate.academy.jdbcdemo.controller;

import mate.academy.jdbcdemo.config.Password;
import mate.academy.jdbcdemo.model.User;
import mate.academy.jdbcdemo.service.UserService;
import mate.academy.jdbcdemo.web.Request;
import mate.academy.jdbcdemo.web.ViewModel;

public class RegistrationController implements Controller {
    private  final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }


    @Override
    public ViewModel process(Request request) {
        String name = request.getParamByName("name");
        String login = request.getParamByName("login");
        ViewModel vm = new ViewModel("home");
        String passwordEncrypted = null;
        try {
            passwordEncrypted = Password.getSaltedHash(request.getParamByName("password"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        User user = new User(name, login, passwordEncrypted );
        System.out.println("RegistrationController: " + user.toString());
        //verify if there is user with such login or password
        long id = userService.getUserIdByLoginAndPassword(login, passwordEncrypted);
        System.out.println( "Check user id in DB" + id);
        if(id!=0){
            return ViewModel.of("login");
        }else{
            User user1 = userService.createUser(user);
            vm.addAttributes("username", user1.getName());
            System.out.println("Saved in DB: " + user1.toString());//save user in DB
        }
        return vm;
    }
}
