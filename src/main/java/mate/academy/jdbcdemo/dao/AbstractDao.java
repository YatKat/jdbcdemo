package mate.academy.jdbcdemo.dao;

import java.sql.Connection;

public class AbstractDao {
    protected final Connection connection;

    protected AbstractDao (Connection connection){
        this.connection = connection;
    }
}
