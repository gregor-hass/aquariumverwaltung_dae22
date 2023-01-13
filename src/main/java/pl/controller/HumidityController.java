package pl.controller;

import bl.HumidityLogic;
import java.util.List;
import model.Humidity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author Julian-Paul Haslinger (P22080)
 *
 */
@RestController
public class HumidityController {

  /**
   * 
   * @return a list of all humidity entries (for all sensors)
   */
  @RequestMapping(value = "/humidity", method = { RequestMethod.GET })
  @ResponseBody
  public ResponseEntity<List<?>> getAll() {
    List<Humidity> humidities = new HumidityLogic().getAll();
    return new ResponseEntity<List<?>>(humidities, HttpStatus.OK);
  }

  /**
   * 
   * @param id the id of the humidity entry to be retrieved
   * @return the humidity entry with the given id
   */
  @RequestMapping(value = "/humidity/{id}", method = { RequestMethod.GET })
  @ResponseBody
  public ResponseEntity<Humidity> getHumidityById(@PathVariable("id") Integer id) {
    return new ResponseEntity<Humidity>(new HumidityLogic().getById(id), HttpStatus.OK);
  }

  /**
   * deletes a certain humidity entry, identified by its id
   * 
   * @param id the id of the humidity entry to be deleted
   * @return a success / failure message
   */
  @RequestMapping(value = "/humidity/{id}", method = { RequestMethod.DELETE })
  @ResponseBody
  public ResponseEntity<String> deleteHumidityById(@PathVariable("id") String id) {
    boolean deleted = new HumidityLogic().deleteSqlInj(id);
    if (deleted) {
      return new ResponseEntity<String>("Humidity with id " + id.toString() + " successfully deleted!", HttpStatus.OK);
    } else {
      return new ResponseEntity<String>("Humidity with id " + id.toString() + " NOT successfully deleted!",
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * stores a new humidity entry for a sensor
   * 
   * @param sensorId the id of the sensor for which the humidity entry should be
   *                 saved
   * @param rh       the relative humidity value
   * @return a success / failure message
   */
  @RequestMapping(value = "sensor/{id}/humidity/{rh}", method = { RequestMethod.POST, RequestMethod.PUT })
  @ResponseBody
  public ResponseEntity<String> addHumidity(@PathVariable("id") Integer sensorId, @PathVariable("rh") Float rh) {
    Humidity h = new Humidity();
    h.setSensor_id(sensorId);
    h.setRelativeHumidity(rh);
    boolean saved = new HumidityLogic().save(h);
    if (saved) {
      return new ResponseEntity<String>("Humidity for sensor id " + sensorId.toString() + " successfully saved!",
          HttpStatus.CREATED);
    } else {
      return new ResponseEntity<String>("Humidity for sensor id " + sensorId.toString() + " NOT successfully saved!",
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
