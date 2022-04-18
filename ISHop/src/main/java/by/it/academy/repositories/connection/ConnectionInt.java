package by.it.academy.repositories.connection;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionInt {

    Connection getConnection() throws ClassNotFoundException, SQLException;

    void closeConnection() throws SQLException;


}
