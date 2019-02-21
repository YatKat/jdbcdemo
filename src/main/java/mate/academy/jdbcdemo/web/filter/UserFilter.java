package mate.academy.jdbcdemo.web.filter;

import mate.academy.jdbcdemo.config.Factory;
import mate.academy.jdbcdemo.dao.UserDao;
import mate.academy.jdbcdemo.model.User;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserFilter implements Filter {
    private UserDao userDao;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        userDao = Factory.getUserDao();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        Cookie[] cookies = req.getCookies();
        System.out.println(cookies.toString());
        String token = null;
        for (Cookie c : cookies) {
            if (c.getName().equals("Mate")) {
                token = c.getValue();
            }
        }
        if (token == null) {
            String path = req.getServletPath()+ req.getPathInfo();
            String method = req.getMethod();
            System.out.println(path);
            if(path.equals("/servlet/register") || (path.equals("/servlet/login")) && method.equals("POST")){
                processAuthorized(req, resp, filterChain);
            }
            processUnauthorized(req, resp);
        } else {
            User user = userDao.findByToken(token);
            if (user == null) {
                processUnauthorized(req, resp);
            } else {
                processAuthorized(servletRequest, servletResponse, filterChain);
            }
        }
    }

    @Override
    public void destroy() {

    }

    private void processUnauthorized(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    private void processAuthorized(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(req, resp);
    }
}
