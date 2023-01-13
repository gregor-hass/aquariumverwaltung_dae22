package pl.controller;

import bl.EmployeeLogic;
import java.util.List;
import model.Employee;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class EmployeeController {

  /**
   * 
   * @return a list of all employees
   * @apiNote TODO: improvement - only authenticated users might be able to use
   *          this
   */
  @RequestMapping(value = "/employee", produces = { "application/json" }, method = { RequestMethod.GET })
  @ResponseBody
  public ResponseEntity<List<Employee>> get_all_employees() {
    List<Employee> employees = new EmployeeLogic().getAllEmployees();
    return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
  }
}
