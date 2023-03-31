package pl.controller;

import bl.AnimalLogic;
import model.Animal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnimalController {
    
  /**
   * 
   * @return a list of all Animal
   * @apiNote TODO: improvement - only authenticated users might be able to use
   *          this
   */
  @RequestMapping(value = "/animals", produces = { "application/json" }, method = { RequestMethod.GET })
  @ResponseBody
  public ResponseEntity<List<Animal>> get_all_animals() {
    List<Animal> animals = new AnimalLogic().getAllAnimal();
    return new ResponseEntity<List<Animal>>(animals, HttpStatus.OK);
  }
  
  /**
   * stores a new animal
   * 
   * @return a success / failure message
   */
  @RequestMapping(value = "/newAnimal/{genus}/{species}/{morph}", method = { RequestMethod.POST, RequestMethod.PUT })
  @ResponseBody
  public ResponseEntity<String> addAnimal( @PathVariable("genus") String genus,  @PathVariable("species") String species, @PathVariable("morph") String morph) {
    Animal animal = new Animal(0,genus,species,morph);

    boolean saved = new AnimalLogic().save(animal);
    if (saved) {
      return new ResponseEntity<String>("new Animal " + animal.getAnimalId() +" (" + genus + " " + species + " " + morph + ") saved",
          HttpStatus.CREATED);
    } else {
      return new ResponseEntity<String>("new Animal NOT saved",
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  /**
   * stores a new animal
   * 
   * @return a success / failure message
   */
  @RequestMapping(value = "/newAnimal/{genus}/{species}", method = { RequestMethod.POST, RequestMethod.PUT })
  @ResponseBody
  public ResponseEntity<String> addAnimal( @PathVariable("genus") String genus,  @PathVariable("species") String species) {
    Animal animal = new Animal(0,genus,species,"");

    boolean saved = new AnimalLogic().save(animal);
    if (saved) {
      return new ResponseEntity<String>("new Animal " + animal.getAnimalId() +" (" + genus + " " + species + ") saved",
          HttpStatus.CREATED);
    } else {
      return new ResponseEntity<String>("new Animal NOT saved",
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  /**
   * stores a new animal
   * 
   * @return a success / failure message
   */
  @RequestMapping(value = "/newAnimalMorph/{genus}/{morph}", method = { RequestMethod.POST, RequestMethod.PUT })
  @ResponseBody
  public ResponseEntity<String> addAnimalMorph( @PathVariable("genus") String genus,  @PathVariable("morph") String morph) {
    Animal animal = new Animal(0,genus,"",morph);

    boolean saved = new AnimalLogic().save(animal);
    if (saved) {
      return new ResponseEntity<String>("new Animal " + animal.getAnimalId() +" (" + genus + " sp. " + morph + ") saved",
          HttpStatus.CREATED);
    } else {
      return new ResponseEntity<String>("new Animal NOT saved",
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
}

