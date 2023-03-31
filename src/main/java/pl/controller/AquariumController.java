package pl.controller;

import bl.AquariumLogic;
import model.Aquarium;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
  
  /**
   * stores a new aquarium
   * 
   * @return a success / failure message
   */
  @RequestMapping(value = "/snewAquarium/{room}/{vol}", method = { RequestMethod.POST, RequestMethod.PUT })
  @ResponseBody
  public ResponseEntity<String> addAquarium( @PathVariable("room") String roomname, @PathVariable("vol") Double volume) {
    Aquarium aq = new Aquarium(0,volume,roomname);

    boolean saved = new AquariumLogic().save(aq);
    if (saved) {
      return new ResponseEntity<String>("new " + volume + "l Aquarium saved for " + roomname,
          HttpStatus.CREATED);
    } else {
      return new ResponseEntity<String>("new " + volume + "l Aquarium NOT saved",
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
}

