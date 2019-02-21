package mate.academy.jdbcdemo.web;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Request {
    private String url;
    private RequestMethod method;
    private Map<String, String[]> param;

    public Request(String url, RequestMethod method, Map<String, String[]> param) {
        this.url = url;
        this.method = method;
        this.param = param;
    }

    enum RequestMethod{
        GET,
        POST,
        PUT,
        DELETE
    }

    public String getParamByName(String name){
        return param.get(name)[0];
    }

    public static Request of(String url, RequestMethod method){
        return new Request(url, method, new HashMap<>());
    }
    public static Request of(String url, RequestMethod method, Map<String, String[]> param){
        return new Request(url, method, param);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Objects.equals(url, request.url) &&
                method == request.method;
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, method);
    }
}
