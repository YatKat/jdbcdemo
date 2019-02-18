package mate.academy.jdbcdemo.controller;

import mate.academy.jdbcdemo.web.Request;
import mate.academy.jdbcdemo.web.ViewModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Controller {
    ViewModel process(Request request) throws ServletException, IOException;
}
