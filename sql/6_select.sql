SET SEARCH_PATH TO AV;

/* Ausgabe der Tiere in einem bestimmten Aquarium */
SELECT * FROM aquarium_contains_animal NATURAL JOIN animals WHERE aquarium_id = 1;

/* Ausgabe der Tiere in einem bestimmten Aquarium */
SELECT * FROM aquarium_contains_plant NATURAL JOIN plants WHERE aquarium_id = 1;

/* Ausgabe des Wissenschaftlichen Namen aller Tiere und Pflanzen in einem bestimmen Aquarium */
SELECT genus, species, morph FROM (aquarium_contains_animal NATURAL JOIN animals) NATURAL FULL OUTER JOIN (aquarium_contains_plant NATURAL JOIN plants) WHERE aquarium_id=1;

/* Ausgabe aller Tiere die in keinem Aquarium gehalten werden */
SELECT * FROM animals WHERE animal_id IN (SELECT animal_id FROM animals EXCEPT SELECT animal_id FROM aquarium_contains_animal)


/* Views */
/* drop views (duplicate to 3_delete.sql) */
DROP VIEW allanimaldata;
DROP VIEW allplantdata;

/* Erstellen einer View fuer jeweils alle Tier- und Pflanzenarten (duplicate to 1_create.sql)*/
CREATE VIEW allanimaldata AS
SELECT * FROM animals NATURAL JOIN animaldata;
CREATE VIEW allplantdata AS
SELECT * FROM plants NATURAL JOIN plantdata;
 
/* Anzeigen der Daten über Views */
select * from allanimaldata
select * from allplantdata

