package bl;

import dal.implementation.WaterchangeDao;
import dal.interfaces.Dao;
import java.util.List;
import java.util.Optional;

import model.Waterchange;

public class WaterchangeLogic {
    
    private Dao<Waterchange> dao = new WaterchangeDao();

  public List<Waterchange> getAllWaterchanges(Integer aquarium_Id) {
    Optional<List<Waterchange>> waterchangeList = null;
    try {
        waterchangeList = dao.getAllByAquariumId(aquarium_Id);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    if (waterchangeList.isPresent()) {
      return waterchangeList.get();
    } else {
      return null;
    }
  }
  
  public boolean save(Waterchange waterchange) {
    boolean saved = false;
    try {
      saved = dao.save(waterchange);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return saved;
  }
}
