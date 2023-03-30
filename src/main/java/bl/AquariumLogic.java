package bl;


import dal.implementation.AquariumDao;
import dal.interfaces.Dao;
import java.util.List;
import java.util.Optional;

import Model.Aquarium;

public class AquariumLogic {

    private Dao<Aquarium> dao = new AquariumDao();
    
    
  public boolean delete(Aquarium aq) {
    boolean deleted = false;
    try {
      deleted = dao.delete(aq);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return deleted;
  }
  
  public List<Aquarium> getAllAquariums() {
    Optional<List<Aquarium>> aquariumList = null;
    try {
      aquariumList = dao.getAll();
    } catch (Exception e) {
      e.printStackTrace();
    }

    if (aquariumList.isPresent()) {
      return aquariumList.get();
    } else {
      return null;
    }
  }

  
  public boolean save(Aquarium aq) {
    boolean saved = false;
    try {
      saved = dao.save(aq);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return saved;
  }
}
