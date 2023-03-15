CREATE TABLE IF NOT EXISTS cars(
    ID VARCHAR(255) NOT NULL PRIMARY KEY,
    manufacturer VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL,
    engine_type VARCHAR(255) NOT NULL,
    year_of_production INT NOT NULL
);