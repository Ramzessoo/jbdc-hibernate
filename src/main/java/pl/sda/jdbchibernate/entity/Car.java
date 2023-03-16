package pl.sda.jdbchibernate.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class Car {

    private final String id;

    private final String manufacturer;

    private final String model;

    private final EngineType engineType;

    private final int yearOfProduction;

    public Car(String manufacturer, String model, EngineType engineType, int yearOfProduction) {
        this.id = UUID.randomUUID().toString();
        this.manufacturer = manufacturer;
        this.model = model;
        this.engineType = engineType;
        this.yearOfProduction = yearOfProduction;
    }

    private Car(String id, String manufacturer, String model, EngineType engineType, int yearOfProduction) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.engineType = engineType;
        this.yearOfProduction = yearOfProduction;
    }

    public Car copy(String manufacturer, String model, EngineType engineType, int yearOfProduction) {
        return new Car(
                this.id,
                manufacturer,
                model,
                engineType,
                yearOfProduction
        );
    }

    public Car(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getString("ID");
        this.manufacturer = resultSet.getString("MANUFACTURER");
        this.model = resultSet.getString("MODEL");
        this.engineType = EngineType.valueOf(resultSet.getString("ENGINE_TYPE"));
        this.yearOfProduction = resultSet.getInt("YEAR_OF_PRODUCTION");
    }

    public String getId() {
        return id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    @Override
    public String toString() {
        return "Car{" + "id='" + id + '\'' + ", manufacturer='" + manufacturer + '\'' + ", model='" + model + '\''
                + ", engineType=" + engineType + ", yearOfProduction=" + yearOfProduction + '}';
    }
}
