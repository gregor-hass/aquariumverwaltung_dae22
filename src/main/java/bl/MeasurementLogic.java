package bl;

import dal.implementation.MeasurementDao;
import dal.interfaces.Dao;
import java.util.List;
import java.util.Optional;

import model.Measurement;
public class MeasurementLogic {
    
    private Dao<Measurement> dao = new MeasurementDao();

  public List<Measurement> getAllMeasurement(Integer aquarium_Id) {
    Optional<List<Measurement>> measurementList = null;
    try {
        measurementList = dao.getAllByAquariumId(aquarium_Id);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    if (measurementList.isPresent()) {
      return measurementList.get();
    } else {
      return null;
    }
  }
  
  public boolean save(Measurement measurement) {
    boolean saved = false;
    try {
      saved = dao.save(measurement);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return saved;
  }
    
}
