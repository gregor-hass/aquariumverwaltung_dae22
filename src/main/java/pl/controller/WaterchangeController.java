package pl.controller;

import bl.WaterchangeLogic;
import model.Waterchange;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WaterchangeController {
    
  /**
   * 
   * @return a list of all Waterchange for a specific aquarium_id
   *          this
   */
  @RequestMapping(value = "/waterchanges/{aquarium_id}", produces = { "application/json" }, method = { RequestMethod.GET })
  @ResponseBody
  public ResponseEntity<List<Waterchange>> get_waterchanges_forId(@PathVariable("aquarium_id") Integer aquarium_id) {
    List<Waterchange> waterchanges = new WaterchangeLogic().getAllWaterchanges(aquarium_id);
    return new ResponseEntity<List<Waterchange>>(waterchanges, HttpStatus.OK);
  }

  /**
   * 
   * @return adds new Waterchange to aquarium
   *          this
   */
  @RequestMapping(value = "/newWaterchange/{aquarium_id}/{amount}", produces = { "application/json" }, method = { RequestMethod.POST, RequestMethod.PUT })
  @ResponseBody
  public ResponseEntity<String> newWaterchange(@PathVariable("aquarium_id") Integer aquarium_id, @PathVariable("amount") Double amount) {
    Waterchange waterchange = new Waterchange(aquarium_id, amount);

    boolean saved = new WaterchangeLogic().save(waterchange);
    if (saved) {
      return new ResponseEntity<String>("new Waterchange at " + waterchange.getTime() + " (Aquarium " + waterchange.getAquarium_id() + ": " + waterchange.getAmount() + "l ) saved",
          HttpStatus.CREATED);
    } else {
      return new ResponseEntity<String>("new Waterchange NOT saved",
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}

