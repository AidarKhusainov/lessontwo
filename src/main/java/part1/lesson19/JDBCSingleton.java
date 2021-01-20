package part1.lesson19;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCSingleton {

    private static JDBCSingleton instance;
    private static Connection connection;
    private final String url = "jdbc:postgresql://localhost:5432/lesson_19";
    private final String username = "postgres";
    private final String password = "123";

    private JDBCSingleton() throws SQLException {
        this.connection = DriverManager.getConnection(url, username, password);
    }

    public Connection getConnection() {
        return connection;
    }

    public static JDBCSingleton getInstance() throws SQLException {
        if (instance == null) {
            instance = new JDBCSingleton();
        } else if (instance.getConnection().isClosed()) {
            instance = new JDBCSingleton();
        }
        return instance;
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}