SET SEARCH_PATH TO AV;

CREATE ROLE viewgroup;
/* Gruppe die Daten über Tiere und Pflanzen abrufen darf, aber keine Daten ändern. z.B. für Öffentlichen Zugriff auf Informationen oder digitale Infotafeln. Es werden nur select Rechte gewährt, da dieser User keine änderungen vornehmen darf*/

CREATE ROLE sciencegroup;
/* Gruppe die wissenschaftliche Messdaten erfassen darf. Bekommt auch Lesenden Zugriff auf die Aquarenliste, um die passende ID finden zu können.*/

GRANT CONNECT ON DATABASE railway to viewgroup;
GRANT USAGE ON SCHEMA av to viewgroup;
GRANT SELECT ON av.aquariums,av.animals,av.animaldata,av.aquarium_contains_animal,av.plants,av.plantdata,av.aquarium_contains_plant,av.allanimaldata,av.allplantdata TO viewgroup;


GRANT CONNECT ON DATABASE railway to sciencegroup;
GRANT USAGE ON SCHEMA av to sciencegroup;
GRANT SELECT ON av.aquariums TO sciencegroup;
GRANT SELECT, INSERT, UPDATE, DELETE ON av.measurements TO sciencegroup;


CREATE USER testuser WITH PASSWORD 'testpassword' IN ROLE viewgroup;
CREATE USER scienceuser WITH PASSWORD 'testpassword' IN ROLE sciencegroup;

/* -------------------------------------
/* test grants with user testuser*/

select * from av.animals
select * from av.allanimaldata
/* outputs the TABLE*/


INSERT INTO plants(genus, species) VALUES 
    ('Testplant','Graminifolia');
/*
ERROR:  permission denied for table plants
SQL state: 42501
*/

select * from av.measurements
/*
ERROR:  permission denied for table measurements
SQL state: 42501
*/

/* -------------------------------------
/* test grants with user scienceuser*/
select * from aquariums
/* outputs the TABLE*/

INSERT INTO aquariums VALUES 
    (1,60,'Test');
/*
ERROR:  permission denied for table aquariums
SQL state: 42501
*/

INSERT INTO measurements(aquarium_id, val, amount) VALUES
    (1, 'PO4', 0.2),
/* add the measurement*/
	
select * from measurements
/* outputs the TABLE*/


/* drop user */
REVOKE ALL PRIVILEGES ON ALL TABLES IN SCHEMA av FROM viewgroup;
REVOKE ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA av FROM viewgroup;
REVOKE ALL PRIVILEGES ON ALL FUNCTIONS IN SCHEMA av FROM viewgroup;
REVOKE ALL PRIVILEGES ON SCHEMA av FROM viewgroup;
REVOKE ALL PRIVILEGES ON DATABASE railway FROM viewgroup;
DROP USER viewgroup;