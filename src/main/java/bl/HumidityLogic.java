package bl;

import dal.implementation.HumidityDao;
import dal.interfaces.Dao;

import java.util.List;
import java.util.Optional;
import model.Humidity;

/**
 * reminder: don't use Spring code here, since you want to keep your business
 * logic clean (and reusable)
 * 
 * @author Julian-Paul Haslinger (P22080)
 *
 */
public class HumidityLogic {

  private Dao<Humidity> dao = new HumidityDao();

  public List<Humidity> getAll() {
    Optional<List<Humidity>> humidityList = null;
    try {
      humidityList = dao.getAll();
    } catch (Exception e) {
      e.printStackTrace();
    }

    if (humidityList.isPresent()) {
      return humidityList.get();
    } else {
      return null;
    }
  }

  public List<Humidity> getHumidityValuesForSensor(Integer sensorId) {
    Optional<List<Humidity>> humidityList = null;
    HumidityDao humidityDao = new HumidityDao();
    try {
      humidityList = humidityDao.getBySensorId(sensorId);
    } catch (Exception e) {
      e.printStackTrace();
    }

    if (humidityList.isPresent()) {
      return humidityList.get();
    } else {
      return null;
    }
  }

  public boolean delete(String id) {
    Humidity humidityToDelete = new Humidity();
    humidityToDelete.setId(Integer.parseInt(id));
    boolean deleted = false;
    try {
      deleted = dao.delete(humidityToDelete);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return deleted;
  }

  public boolean deleteSqlInj(String id) {
    boolean deleted = false;
    HumidityDao humidityDao = new HumidityDao();
    try {
      // attention!
      deleted = humidityDao.deleteV1(id);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return deleted;
  }

  public Humidity getById(int id) {
    Optional<Humidity> foundHumidity = null;
    try {
      foundHumidity = dao.getById(id);
    } catch (Exception e) {
      e.printStackTrace();
    }

    if (foundHumidity.isPresent()) {
      return foundHumidity.get();
    } else {
      return null;
    }
  }

  public boolean save(Humidity h) {
    boolean saved = false;
    try {
      saved = dao.save(h);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return saved;
  }
}
