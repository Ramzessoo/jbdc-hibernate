package pl.sda.jdbchibernate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {
        System.out.println("JDBC world! Going to connect to DB...");

        try (final Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + System.getenv("DB_NAME"),
                System.getenv("DB_USER"), System.getenv("DB_PASSWORD"))) {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE TEST_TABLE(TEST_COLUMN INT)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Connected to DB!");
    }
}