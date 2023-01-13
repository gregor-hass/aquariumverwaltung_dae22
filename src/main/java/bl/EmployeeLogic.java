package bl;

import dal.implementation.EmployeeDao;
import dal.interfaces.Dao;

import java.util.List;
import java.util.Optional;
import model.Employee;

/**
 * reminder: don't use Spring code here, since you want to keep your business
 * logic clean (and reusable)
 * 
 * @author Julian-Paul Haslinger (P22080)
 *
 */
public class EmployeeLogic {

  private Dao<Employee> dao = new EmployeeDao();

  public boolean delete(Employee emp) {
    boolean deleted = false;
    try {
      deleted = dao.delete(emp);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return deleted;
  }

  public List<Employee> getAllEmployees() {
    Optional<List<Employee>> employeeList = null;
    try {
      employeeList = dao.getAll();
    } catch (Exception e) {
      e.printStackTrace();
    }

    if (employeeList.isPresent()) {
      return employeeList.get();
    } else {
      return null;
    }
  }

  public Employee getById(int id) {
    Optional<Employee> employee = null;
    try {
      employee = dao.getById(id);
    } catch (Exception e) {
      e.printStackTrace();
    }

    if (employee.isPresent()) {
      return employee.get();
    } else {
      return null;
    }
  }

  public boolean save(Employee emp) {
    boolean saved = false;
    try {
      saved = dao.save(emp);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return saved;
  }

  public boolean update(Employee emp) {

    boolean updated = false;
    try {
      updated = dao.update(emp);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return updated;
  }
}
