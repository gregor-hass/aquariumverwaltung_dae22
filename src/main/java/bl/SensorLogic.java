package bl;

import dal.implementation.SensorDao;
import dal.interfaces.Dao;

import java.util.List;
import java.util.Optional;
import model.Sensor;

/**
 * reminder: don't use Spring code here, since you want to keep your business
 * logic clean (and reusable)
 * 
 * @author Julian-Paul Haslinger (P22080)
 *
 */
public class SensorLogic {

  private Dao<Sensor> dao = new SensorDao();

  public List<Sensor> getAllSensors() {
    Optional<List<Sensor>> sensors = null;
    try {
      sensors = dao.getAll();
    } catch (Exception e) {
      e.printStackTrace();
    }

    if (sensors.isPresent()) {
      return sensors.get();
    } else {
      return null;
    }
  }

  public boolean saveSensor(Sensor s) {
    boolean saved = false;
    if (s.getCreated_at() == null) {
      s.setCreated_at(new java.util.Date());
    }
    try {
      saved = dao.save(s);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return saved;
  }
}
