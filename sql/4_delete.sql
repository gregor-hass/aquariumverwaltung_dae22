SET SEARCH_PATH TO av;
 
-- drop the views
DROP VIEW IF EXISTS allanimaldata;
DROP VIEW IF EXISTS allplantdata;

-- drop the tables one by one (and cascade the delete)
DROP TABLE IF EXISTS aquariums CASCADE;
DROP TABLE IF EXISTS animals CASCADE;
DROP TABLE IF EXISTS animaldata CASCADE;
DROP TABLE IF EXISTS plants CASCADE;
DROP TABLE IF EXISTS plantdata CASCADE;
DROP TABLE IF EXISTS measurements CASCADE;
DROP TABLE IF EXISTS waterchanges CASCADE;
DROP TABLE IF EXISTS aquarium_contains_plant CASCADE;
DROP TABLE IF EXISTS aquarium_contains_animal CASCADE;

-- drop the whole schema
DROP SCHEMA IF EXISTS av CASCADE;