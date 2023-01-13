
SET SEARCH_PATH TO hr; 

-- login as su / postgres
-- create new user for sensor and humidity access

DROP TABLE IF EXISTS sensor CASCADE;
DROP TABLE IF EXISTS humidity CASCADE;

-- sensor table
-- unique sensor id (auto generated), a timestamp (time of db!), a "friendly" (readable) 
-- name for the sensor and a location (POINT is a geo data type in PostgreSQL (X,Y))
CREATE TABLE IF NOT EXISTS sensor  (
    sensor_id serial primary key,
    created_at timestamp not null default current_timestamp,
    friendly_name TEXT,
    location POINT
);

-- add new column for employee id
ALTER TABLE sensor
    ADD COLUMN employee_id int;

ALTER TABLE sensor
    ADD FOREIGN KEY (employee_id) 
    REFERENCES employees(employee_id);

-- insert a sensor information in the db - the sensor would be located near LH01
INSERT 
    INTO sensor (friendly_name, location) 
    VALUES ('Humidity Sensor #1', POINT(48.368352, 14.512758)); --FH Hagenberg

-- select all test data from the sensor table
SELECT * FROM sensor; 

-- humidity table
-- unique id (auto generated), a timestamp (time of db!) and a value for the relative humidity (rh)
DROP TABLE IF EXISTS humidity; 
CREATE TABLE humidity (
    id serial primary key,
    created_at timestamp not null default current_timestamp,
    rh REAL NOT NULL -- relative humidity - real
);

-- add new column for sensor id
ALTER TABLE humidity
    ADD COLUMN sensor_id int;

ALTER TABLE humidity
    ADD FOREIGN KEY (sensor_id) 
    REFERENCES sensor(sensor_id);

-- insert some test data in the humidity table
INSERT 
    INTO humidity (rh, sensor_id) 
    SELECT 1.0, sensor_id FROM sensor WHERE friendly_name ='Humidity Sensor #1'; 
INSERT 
    INTO humidity (rh, sensor_id) 
    SELECT 1.1, sensor_id FROM sensor WHERE friendly_name ='Humidity Sensor #1'; 
INSERT 
    INTO humidity (rh, sensor_id) 
    SELECT 2.0, sensor_id FROM sensor WHERE friendly_name ='Humidity Sensor #1'; 
INSERT 
    INTO humidity (rh, sensor_id) 
    SELECT 22.0, sensor_id FROM sensor WHERE friendly_name ='Humidity Sensor #1'; 
INSERT 
    INTO humidity (rh, sensor_id) 
    SELECT 1.0, sensor_id FROM sensor WHERE friendly_name ='Humidity Sensor #1'; 

-- select all test data in the humidity table
SELECT * FROM humidity; 