SET SEARCH_PATH TO AV;
 
 /* Erstellen von Testdaten */
 
BEGIN TRANSACTION;

INSERT INTO aquariums VALUES 
    (1,60,'Wohnzimmer');

INSERT INTO aquariums VALUES 
    (2,120,'Wohnzimmer');

INSERT INTO aquariums VALUES 
    (3,4,'Schlafzimmer');

INSERT INTO aquariums VALUES 
    (4,12,'Eingang');


/*-------------------------------------------------------------------*/

INSERT INTO animals(animal_id,genus, species,morph) VALUES 
    (1,'Betta','Imbellis', 'wild');
INSERT INTO animals(animal_id,genus, species,morph) VALUES 
    (2,'Betta','Imbellis', 'Red Band');
INSERT INTO animals(animal_id,genus, species) VALUES 
    (3,'Danio','Margaritatus');
INSERT INTO animals(animal_id,genus, species) VALUES 
    (4,'Otocinclus', 'Affinis');
INSERT INTO animals(animal_id,genus, species) VALUES 
    (5,'Corydoras', 'Panda');
INSERT INTO animals(animal_id,genus, species,morph) VALUES 
    (6,'Corydoras', 'Panda', 'long fin');
INSERT INTO animals(animal_id,genus, species) VALUES 
    (7,'Corydoras','Sterbai');

/*-------------------------------------------------------------------*/
INSERT INTO animaldata VALUES 
    ((SELECT animal_id from animals WHERE genus = 'Betta' and species = 'Imbellis' and morph = 'wild'),'Friedlicher Kampffisch','Pelets, Flockenfutter, Proteinreich', 'friedlicher Einzelgänger', 'Oberflächennah', 32,24,4,'Einzeln oder Paarweise, genug Unterschlupf bieten', 1,12);

INSERT INTO animaldata VALUES 
    ((SELECT animal_id from animals WHERE genus = 'Betta' and species = 'Imbellis' and morph = 'Red Band'),'Friedlicher Kampffisch','Pelets, Flockenfutter, Proteinreich', 'friedlicher Einzelgänger', 'Oberflächennah', 32,24,4,'Einzeln oder Paarweise, genug Unterschlupf bieten', 1,12);

INSERT INTO animaldata VALUES 
    ((SELECT animal_id from animals WHERE genus = 'Danio' and species = 'Margaritatus'), 'Perlhuhnbärbling, Celestial Pearl Danio, CPD, Galaxy Rasboras', 'schwebendes Futter, fressen nicht von Boden oder Oberfläche', 'leicht scheue Gruppen- oder Schwarmtiere', 'Mitte-Unten',24,20, 2.5, 'Gruppe', 6,7);
    
INSERT INTO animaldata VALUES 
    ((SELECT animal_id from animals WHERE genus = 'Otocinclus' and species = 'Affinis'), 'Ohrgitter Harnischwels', 'Aufwuchsfresser, Algentabs oder Spirulinabelag auf Wurzeln/Stein', 'Saugwels, vermehrt auf Glasflächen oder glattem Holz/Steinen', 'Überall wo Aufwuchs wächst', 27,20,5, 'Gruppe', 6,5);

INSERT INTO animaldata VALUES 
    ((SELECT animal_id from animals WHERE genus = 'Corydoras' and species = 'Panda' and morph IS NULL), 'Panda Panzerwelse', 'Grundelnde Restefresser, rote Wurmähnliche Nahrung bevorzugt', 'quirliger Gruppenfisch', 'Boden, manchmal Mitte', 27,22,5,'Gruppe', 6,6);

/*-------------------------------------------------------------------*/

INSERT INTO plants(genus, species, morph) VALUES 
    ('Echinodorus','Grisebachii', 'Amazonicus');

INSERT INTO plants(genus, species, morph) VALUES 
    ('Rotala','sp.', 'Pearl');

INSERT INTO plants(genus, species, morph) VALUES 
    ('Microsorum','Pteropus', 'Trident');
INSERT INTO plants(genus, species, morph) VALUES 
    ('Microsorum','Pteropus', 'Windeløv');

INSERT INTO plants(genus, species, morph) VALUES 
    ('Bucephalandra','sp.', 'Serimbu Brown');

INSERT INTO plants(genus, species) VALUES 
    ('Utricularia','Graminifolia');


INSERT INTO plantdata VALUES 
    ((SELECT plant_id from plants WHERE genus = 'Echinodorus' and morph = 'Amazonicus'),'Amazonas-Schwertpflanze',30,28,22,'Mitte - Hintergrund');
INSERT INTO plantdata VALUES 
    ((SELECT plant_id from plants WHERE genus = 'Rotala' and morph = 'Pearl'),NULL,25,30,18,'Mitte - Hintergrund');
INSERT INTO plantdata(plant_id, usage) VALUES 
    ((SELECT plant_id from plants WHERE genus = 'Bucephalandra' and morph = 'Serimbu Brown'),'Aufsitzer');

INSERT INTO plantdata(plant_id, usage) VALUES 
    ((SELECT plant_id from plants WHERE genus = 'Utricularia' and species = 'Graminifolia'),'Bodendecker');



INSERT INTO aquarium_contains_animal VALUES
    (1,1,1,1,0);
INSERT INTO aquarium_contains_animal VALUES
    (1,5,3,8,0);
INSERT INTO aquarium_contains_animal VALUES
    (2,4,0,0,10);
INSERT INTO aquarium_contains_animal VALUES
    (2,3,5,10,1);
INSERT INTO aquarium_contains_animal VALUES
    (2,7,8,7,0);
INSERT INTO aquarium_contains_animal VALUES
    (3,3,0,0,12);


INSERT INTO aquarium_contains_plant VALUES
    (1,(SELECT plant_id from plants WHERE genus = 'Echinodorus' and morph = 'Amazonicus'));
INSERT INTO aquarium_contains_plant VALUES
    (1,(SELECT plant_id from plants WHERE genus = 'Utricularia' and species = 'Graminifolia'));
INSERT INTO aquarium_contains_plant VALUES
    (2,(SELECT plant_id from plants WHERE genus = 'Bucephalandra' and morph = 'Serimbu Brown'));
INSERT INTO aquarium_contains_plant VALUES
    (3,(SELECT plant_id from plants WHERE genus = 'Microsorum' and species = 'Pteropus' and morph = 'Trident'));



INSERT INTO measurements(aquarium_id, val, amount) VALUES
    (1, 'PO4', 0.2),
    (1, 'NO3', 0.8),
    (1, 'NO2', 0.5),
    (1, 'CO2', 0.2);
INSERT INTO measurements(aquarium_id, val, amount) VALUES
    (2, 'PH', 6.8),
    (2, 'NO3', 0.8),
    (2, 'KH', 6),
    (2, 'GH', 8);
INSERT INTO measurements(aquarium_id, val, amount) VALUES
    (3, 'PO4', 0.2),
    (3, 'NO3', 0.8),
    (3, 'NO2', 0.5),
    (3, 'Fe', 0.2);

INSERT INTO waterchanges(aquarium_id, amount) VALUES
    (1, 20),
    (2, 30),
    (3, 1),
    (4, 3);


COMMIT;