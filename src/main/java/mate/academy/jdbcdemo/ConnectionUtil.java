package mate.academy.jdbcdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    private static Connection connection = null;
    private static String url = "jdbc:mysql://localhost:3306/book-shop?serverTimezone=UTC";
    private static String password = "root";
    private static String user = "root";


    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Jdbc Driver was not loaded");
            e.printStackTrace();
            System.exit(1);
        }
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Connection to DB was not established");
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        return connection;
    }
}
