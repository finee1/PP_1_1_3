package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Util {
    private Util(){}
    private static final String DB_URL = "jdbc:mysql://localhost:3306/root";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";
    private static final Logger logger = Logger.getLogger(Util.class.getName());

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            logger.info("Connected to the database successfully");
        } catch (SQLException | ClassNotFoundException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        return connection;
    }
}
