package pl.sda.jdbchibernate.repository;

import pl.sda.jdbchibernate.entity.Car;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class CarsRepository {

    private final Connection connection;

    public CarsRepository(Connection connection) {
        this.connection = connection;
    }

    // Create
    public void create(Car car) throws SQLException {
        String insertSql = "INSERT INTO CARS(ID, MANUFACTURER, MODEL, ENGINE_TYPE, YEAR_OF_PRODUCTION)"
                + "VALUES('%s', '%s', '%s', '%s', %d)";
        String sql = String.format(insertSql, car.getId(), car.getManufacturer(), car.getModel(), car.getEngineType(),
                car.getYearOfProduction());

        System.out.println("Going to execute SQL:\n" + sql);
        connection.createStatement().execute(sql);
        System.out.println("SQL executed successfully!");
    }

    // Read
    public List<Car> findAll() throws SQLException {
        String sql = "SELECT * FROM CARS";

        System.out.println("Going to execute SQL:\n" + sql);
        ResultSet resultSet = connection.createStatement().executeQuery(sql);
        System.out.println("SQL executed successfully!");

        final List<Car> listOfCars = new ArrayList<>();
        while (resultSet.next()) {
            listOfCars.add(new Car(resultSet));
        }
        return listOfCars;
    }

    // Update
    public void update(Car car) throws SQLException {
        String sql = String.format(
                "UPDATE CARS SET YEAR_OF_PRODUCTION='%d', MODEL='%s', ENGINE_TYPE='%s', MANUFACTURER='%s' WHERE ID='%s'",
                car.getYearOfProduction(), car.getModel(), car.getEngineType(), car.getManufacturer(), car.getId());

        System.out.println("Going to execute SQL:\n" + sql);
        final int updatedRows = connection.createStatement().executeUpdate(sql);
        System.out.println("Updated " + updatedRows + " cars!");
    }

    // Delete
    public void deleteAll(Car car) throws SQLException {
        String sql = String.format("DELETE FROM CARS WHERE ID='%s'", car.getId());

        System.out.println("Going to execute SQL:\n" + sql);
        final int deletedRows = connection.createStatement().executeUpdate(sql);
        System.out.println("Deleted " + deletedRows + " cars!");
    }
}
