package by.it.academy.repositories.connection;

import java.sql.Connection;
import java.sql.SQLException;

public interface DBConnection {

    Connection getConnection() throws ClassNotFoundException, SQLException;


}
