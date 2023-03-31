package pl.controller;

import bl.MeasurementLogic;
import model.Measurement;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeasurementController {
    
  /**
   * 
   * @return a list of all Measurement for a specific aquarium_id
   *          this
   */
  @RequestMapping(value = "/measurements/{aquarium_id}", produces = { "application/json" }, method = { RequestMethod.GET })
  @ResponseBody
  public ResponseEntity<List<Measurement>> get_measurement_forId(@PathVariable("aquarium_id") Integer aquarium_id) {
    List<Measurement> measurement = new MeasurementLogic().getAllMeasurement(aquarium_id);
    return new ResponseEntity<List<Measurement>>(measurement, HttpStatus.OK);
  }

  /**
   * 
   * @return adds new Measurement to aquarium
   *          this
   */
  @RequestMapping(value = "/newMeasurement/{aquarium_id}//{value}/{amount}",method = { RequestMethod.POST, RequestMethod.PUT })
  @ResponseBody
  public ResponseEntity<String> addWaterchange(@PathVariable("aquarium_id") Integer aquarium_id,@PathVariable("value") String value, @PathVariable("amount") Double amount) {
    Measurement measurement = new Measurement(aquarium_id, value, amount);

    boolean saved = new MeasurementLogic().save(measurement);
    if (saved) {
      return new ResponseEntity<String>("new Measurement at " + measurement.getTime() + " (Aquarium " + measurement.getAquarium_id() + ": " + measurement.getAmount() + " "+ measurement.getType() + " ) saved",
          HttpStatus.CREATED);
    } else {
      return new ResponseEntity<String>("new Measurement NOT saved",
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}

