package pl.sda.jdbchibernate;

import pl.sda.jdbchibernate.entity.Car;
import pl.sda.jdbchibernate.entity.EngineType;
import pl.sda.jdbchibernate.repository.CarsRepository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {

    public static final String JDBC_URL = "jdbc:mysql://localhost:3306/";

    public static final String DB_NAME_ENV = "DB_NAME";

    public static final String DB_USER_ENV = "DB_USER";

    public static final String DB_PASSWORD_ENV = "DB_PASSWORD";

    public static void main(String[] args) throws IOException, SQLException {
        System.out.println("JDBC world! Going to connect to DB...");

        try (final Connection connection = DriverManager.getConnection(JDBC_URL + System.getenv(DB_NAME_ENV),
                System.getenv(DB_USER_ENV), System.getenv(DB_PASSWORD_ENV))) {
            System.out.println("Connected to DB!");

            final DbInitializer dbInitializer = new DbInitializer(connection);
            dbInitializer.initDb();

            final CarsRepository carsRepository = new CarsRepository(connection);
            carsRepository.create(new Car("Porsche", "911", EngineType.GASOLINE, 2000));
            List<Car> cars = carsRepository.findAll();
            System.out.println("Cars: " + cars);
            final Car car = cars.get(0);
            carsRepository.update(
                    car.copy(car.getManufacturer(), car.getModel(), EngineType.DIESEL, car.getYearOfProduction()));
            cars = carsRepository.findAll();
            System.out.println("Cars: " + cars);
            carsRepository.deleteAll(cars.get(0));
            cars = carsRepository.findAll();
            System.out.println("Cars: " + cars);
        }
    }
}
