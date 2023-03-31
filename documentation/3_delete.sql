SET SEARCH_PATH TO AV;

/* Löscht alle Tiere der Gattung Corydoras*/
DELETE FROM animals WHERE Genus = 'Corydoras';

/* Löscht die spezifische Pflanze Microsorum Pteropus 'Trident' nicht aber den morph 'Windeløv'*/
DELETE FROM plants WHERE Genus = 'Microsorum' and species = 'Pteropus' and morph = 'Trident';

/* Löscht das Aquarium mit der id 2 */
DELETE FROM aquariums WHERE aquarium_id = 2;