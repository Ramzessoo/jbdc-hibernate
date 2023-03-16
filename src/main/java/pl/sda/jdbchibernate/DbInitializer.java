package pl.sda.jdbchibernate;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DbInitializer {

    private final Connection connection;

    public DbInitializer(Connection connection) {
        this.connection = connection;
    }

    public void initDb() throws IOException, SQLException {
        try (InputStream resourceAsStream = getClass().getResourceAsStream("/sql/init_db.sql")) {
            if (resourceAsStream == null) {
                System.err.println("Failed to open sql/init_db.sql");
                return;
            }
            String sql = new String(resourceAsStream.readAllBytes());

            System.out.println("Going to execute sql: \n" + sql);

            Statement statement = connection.createStatement();
            statement.execute(sql);

            System.out.println("SQL executed successfully!");
        }
    }
}
