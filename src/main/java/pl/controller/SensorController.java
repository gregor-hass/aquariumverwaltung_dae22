package pl.controller;

import bl.HumidityLogic;
import bl.SensorLogic;
import java.util.List;
import model.Sensor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
public class SensorController {

  @RequestMapping(value = "/sensor", method = { RequestMethod.GET })
  @ResponseBody
  public ResponseEntity<?> getAllSensors() {
    List<Sensor> sensors = new SensorLogic().getAllSensors();
    return new ResponseEntity<List<?>>(sensors, HttpStatus.OK);
  }

  /**
   * @param id - the sensor id
   * @return the list of humidity entries for a certain sensor (by sensor_id)
   */
  @RequestMapping(value = "/sensor/{id}/humidity", method = { RequestMethod.GET })
  @ResponseBody
  public ResponseEntity<List<?>> getHumidityBySensorId(@PathVariable("id") Integer id) {
    return new ResponseEntity<List<?>>(new HumidityLogic().getHumidityValuesForSensor(id), HttpStatus.OK);
  }

  /**
   * @param sensor - the sensor that should be saved in db
   * @return a success / failure message
   */
  @RequestMapping(value = "sensor", method = { RequestMethod.POST, RequestMethod.PUT })
  @ResponseBody
  public ResponseEntity<String> addSensor(@RequestBody Sensor sensor) {
    boolean saved = new SensorLogic().saveSensor(sensor);
    if (saved) {
      return new ResponseEntity<String>("Sensor" + sensor.getFriendly_name() + ") successfully saved!",
          HttpStatus.CREATED);
    } else {
      return new ResponseEntity<String>("Sensor (" + sensor.getFriendly_name() + ") NOT successfully saved!",
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
