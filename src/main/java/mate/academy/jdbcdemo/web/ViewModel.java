package mate.academy.jdbcdemo.web;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewModel {
    private final String view;
    private final Map<String, Object> model = new HashMap<>();
    private final List<Cookie> cookieList = new ArrayList<>();

    public ViewModel(String view) {
        this.view = view;
    }

    public String getView() {
        return view;
    }

    public void addAttributes(String message, Object o) {
        this.model.put(message, o);
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public static ViewModel of(String view) {
        return new ViewModel(view);
    }

    public void addCookies(Cookie cookie) {
        this.cookieList.add(cookie);
    }
}
