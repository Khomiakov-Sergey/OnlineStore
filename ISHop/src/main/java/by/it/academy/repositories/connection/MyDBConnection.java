package by.it.academy.repositories.connection;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MyDBConnection implements DBConnection {
    private String connectionURL;
    private String userName;
    private String password;
    private String driver;

    private Connection connection;

    private final static Logger log = Logger.getLogger(MyDBConnection.class);

    public MyDBConnection() {
    }

    private void getConfig() {
        Properties properties = new Properties();
        try (InputStream is = MyDBConnection.class.getClassLoader().getResourceAsStream("db.properties")) {
            properties.load(is);
        } catch (IOException e) {
            log.info("Can`t get resource bundle" + e.getMessage());
        }

        connectionURL = properties.getProperty("jdbc.url");
        userName = properties.getProperty("jdbc.userName");
        password = properties.getProperty("jdbc.password");
        driver = properties.getProperty("jdbc.driver");
    }

    @Override
    public Connection getConnection() {
        getConfig();
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            log.info("Can`t get driver" + e.getMessage());
        }
        try {
            connection = DriverManager.getConnection(connectionURL, userName, password);
            log.info("Connection to DB was successful");
        } catch (SQLException e) {
            log.info("Connection to DB was unsuccessful" + e.getMessage());
        }
        return connection;
    }
}
