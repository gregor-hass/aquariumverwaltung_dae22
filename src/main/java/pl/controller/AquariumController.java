package pl.controller;

import bl.AquariumLogic;
import Model.Aquarium;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

public class AquariumController {
    
  /**
   * 
   * @return a list of all aquariums
   * @apiNote TODO: improvement - only authenticated users might be able to use
   *          this
   */
  @RequestMapping(value = "/aquarium", produces = { "application/json" }, method = { RequestMethod.GET })
  @ResponseBody
  public ResponseEntity<List<Aquarium>> get_all_aquariums() {
    List<Aquarium> aquariums = new AquariumLogic().getAllAquariums();
    return new ResponseEntity<List<Aquarium>>(aquariums, HttpStatus.OK);
  }
}
