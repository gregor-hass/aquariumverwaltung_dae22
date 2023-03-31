# REST Client Plugin
* see here: https://marketplace.visualstudio.com/items?itemName=humao.rest-client 
* Should replace the postman collection in the future
* Check also the **swagger ui documentation** for more infos about possible requests: 
    * ``https://dae5-raindrops-ws2022-test-1-production.up.railway.app/swagger-ui.html`` 

# Variables 
* Definition with @ and usage with {{...}}
@url = https://aquariumverwaltungdae22-production.up.railway.app/

# Info - Controller
GET {{url}}/info HTTP/1.1

# Aquarium Controller

GET {{url}}/newAquarium/testroom/56 HTTP/1.1 

answer: 
new 56.0l Aquarium saved for testroom
## Get All Aquariums
GET {{url}}/aquarium HTTP/1.1 

answer: 
[
  {
    "aquariumId": 1,
    "volume": 60,
    "room": "Wohnzimmer"
  },
  ...
  {
    "aquariumId": 5,
    "volume": 56,
    "room": "testroom"
  }
]


# Animal Controller

## Add new animals
all 3 ways to add an animal:

POST {{url}}/newAnimal/TestGenusREST1/TestSpeciesREST1 HTTP/1.1

answer: 
new Animal 8 (TestGenusREST1 TestSpeciesREST1) saved

---------------
POST {{url}}/newAnimal/TestGenusREST2/TestSpeciesREST2/TestMorphREST2 HTTP/1.1

answer: 
new Animal 9 (TestGenusREST2 TestSpeciesREST2 TestMorphREST2) saved

---------------
POST {{url}}/newAnimalMorph/TestGenus3/TestMorph3 HTTP/1.1

answer: 
new Animal 10 (TestGenus3 sp. TestMorph3) saved

## Get All Animals
GET {{url}}/animals HTTP/1.1 

answer:
[
  {
    "animalId": 1,
    "genus": "Betta",
    "species": "Imbellis",
    "morph": "wild"
  },
  ...
  {
    "animalId": 9,
    "genus": "TestGenusREST2",
    "species": "TestSpeciesREST2",
    "morph": "TestMorphREST2"
  },
  {
    "animalId": 10,
    "genus": "TestGenus3",
    "species": "sp.",
    "morph": "TestMorph3"
  }
]
# Waterchange Controller
## Add new waterchanges

POST {{url}}/newWaterchange/1/50 HTTP/1.1

answer: 
new Waterchange at 2023-03-31 08:39:29.312605 (Aquarium 1: 50.0l ) saved

## Get Waterchanges for aquarium_id
POST {{url}}/waterchanges/1 HTTP/1.1

answer: 
[
  {
    "aquarium_id": 1,
    "time": "2023-03-30T14:29:07.713+0000",
    "amount": 20
  },
  {
    "aquarium_id": 1,
    "time": "2023-03-31T08:39:29.312+0000",
    "amount": 50
  }
]