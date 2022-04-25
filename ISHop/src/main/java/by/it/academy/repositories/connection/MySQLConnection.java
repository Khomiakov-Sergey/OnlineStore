package by.it.academy.repositories.connection;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection implements DBConnection {
    private String connectionURL = "jdbc:mysql://localhost:3306/web_app_servlet_db";
    private String userName = "root";
    private String password = "root";
    private Connection connection;

    private final static Logger log = Logger.getLogger(MySQLConnection.class);
    private final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";


    public MySQLConnection(String connectionURL, String userName, String password) {
        this.connectionURL = connectionURL;
        this.userName = userName;
        this.password = password;
    }

    public MySQLConnection() {
    }

    @Override
    public Connection getConnection() {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            log.info(e.getMessage());
        }
        try {
            connection = DriverManager.getConnection(connectionURL, userName, password);
            log.info("Connection to DB was successful");
        } catch (SQLException e) {
            log.info(e.getMessage());
        }
        return connection;
    }

    @Override
    public void closeConnection() throws SQLException {
        connection.close();
    }
}
