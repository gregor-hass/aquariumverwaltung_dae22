package bl;

import dal.implementation.AnimalDao;
import dal.interfaces.Dao;
import java.util.List;
import java.util.Optional;

import model.Animal;

public class AnimalLogic {
    
    private Dao<Animal> dao = new AnimalDao();

  public List<Animal> getAllAnimal() {
    Optional<List<Animal>> animalList = null;
    try {
        animalList = dao.getAll();
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    if (animalList.isPresent()) {
      return animalList.get();
    } else {
      return null;
    }
  }
  
  public boolean save(Animal animal) {
    boolean saved = false;
    try {
      saved = dao.save(animal);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return saved;
  }
}
