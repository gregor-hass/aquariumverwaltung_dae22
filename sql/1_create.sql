/* Als DBMS wird PostgreSQL verwendet. Es entspricht den Anforderungen an relationelle Datenbanken und wurde auch in der VO verwendet.*/

/* Erstellen der Tabellen laut Logischem Entwurf */
DROP SCHEMA IF EXISTS AV CASCADE;
CREATE SCHEMA AV;
SET SEARCH_PATH TO AV;

/*-------------------------------------------------------------------*/
/* Aquarien */
CREATE TABLE aquariums (
    aquarium_id SERIAL PRIMARY KEY,
    aquarium_vol decimal(7,3),
    aquarium_room VARCHAR(30)
);

/*-------------------------------------------------------------------*/
/* Tier */
CREATE TABLE animals (
    animal_id SERIAL PRIMARY KEY,
    genus VARCHAR(30),
    species VARCHAR(30),
    morph VARCHAR(30)
);

/* Tierdaten */
CREATE TABLE animaldata (
    animal_id INTEGER NOT NULL,
    common_name VARCHAR(100),
    feed VARCHAR(100),
    behaviour VARCHAR(100),
    zone VARCHAR(30),
    temperature_max INTEGER,
    temperature_min INTEGER,
    CHECK (temperature_min < temperature_max),
    length_max decimal(4,1),
    husbandry VARCHAR(100),
    min_group INTEGER,
    cost INTEGER,
    FOREIGN KEY (animal_id) REFERENCES animals (animal_id) ON DELETE CASCADE

);

/*-------------------------------------------------------------------*/
/* Pflanze */
CREATE TABLE plants (
    plant_id SERIAL PRIMARY KEY,
    genus VARCHAR(30),
    species VARCHAR(30),
    morph VARCHAR(30)
);

/* Pflanzendaten */
CREATE TABLE plantdata (
    plant_id INTEGER NOT NULL,
    common_name VARCHAR(50),
    height decimal(4,1),
    temperature_max INTEGER,
    temperature_min INTEGER,
    CHECK (temperature_min <= temperature_max),
    usage VARCHAR(30),
    FOREIGN KEY (plant_id) REFERENCES plants (plant_id) ON DELETE CASCADE

);


/*-------------------------------------------------------------------*/
/* Messpunkt */
CREATE TABLE measurements(
    aquarium_id INTEGER NOT NULL,
    time_stamp timestamp NOT NULL default current_timestamp,
    val VARCHAR,
    amount decimal(4,2),
    FOREIGN KEY (aquarium_id) REFERENCES aquariums (aquarium_id) ON DELETE CASCADE
);

/*-------------------------------------------------------------------*/
/* Wasserwechsel */
CREATE TABLE waterchanges(
    aquarium_id INTEGER NOT NULL,
    time_stamp timestamp NOT NULL default current_timestamp,
    amount  decimal(5,1),
    FOREIGN KEY (aquarium_id) REFERENCES aquariums (aquarium_id) ON DELETE CASCADE

);

/*-------------------------------------------------------------------*/
/* Aquarium_enthält_Tier */
CREATE TABLE aquarium_contains_animal(
    aquarium_id INTEGER NOT NULL,
    animal_id INTEGER NOT NULL,
    amount_male INTEGER,
    amount_female INTEGER,
    amount_unknown INTEGER,
    FOREIGN KEY (aquarium_id) REFERENCES aquariums (aquarium_id) ON DELETE CASCADE,
    FOREIGN KEY (animal_id) REFERENCES animals (animal_id) ON DELETE CASCADE
);
/*-------------------------------------------------------------------*/
/* Aquarium_enthält_Pflanze */
CREATE TABLE aquarium_contains_plant(
    aquarium_id INTEGER NOT NULL,
    plant_id INTEGER NOT NULL,
    FOREIGN KEY (aquarium_id) REFERENCES aquariums (aquarium_id) ON DELETE CASCADE,
    FOREIGN KEY (plant_id) REFERENCES plants (plant_id) ON DELETE CASCADE
);


/*-------------------------------------------------------------------*/
/* Views */
CREATE VIEW allanimaldata AS
SELECT * FROM animals NATURAL JOIN animaldata;
CREATE VIEW allplantdata AS
SELECT * FROM plants NATURAL JOIN plantdata;