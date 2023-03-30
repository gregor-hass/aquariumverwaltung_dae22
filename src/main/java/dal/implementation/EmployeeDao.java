package dal.implementation;

import dal.interfaces.Dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Employee;

/**
 * 
 * @author Julian-Paul Haslinger (P22080)
 *
 */
public class EmployeeDao extends DaoBase implements Dao<Employee> {
  /**
   * deletes the given employee from the db
   */
  @Override
  public boolean delete(Employee emp) {
    // Connection conn = ConnectionFactory.getConnection();
    int affectedRows = 0;
    PreparedStatement deleteStatement;
    try {
      deleteStatement = conn.prepareStatement("DELETE FROM employees WHERE employee_id = ?");
      deleteStatement.setInt(1, emp.getEmployeeId());
      affectedRows = deleteStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeConnection();
    }
    return affectedRows == 1 ? true : false;

  }

  /**
   * retrieves all employees and returns them as a list
   *
   * @return all employees in the database
   */
  @Override
  public Optional<List<Employee>> getAll() {
    List<Employee> employees = new ArrayList<Employee>();

    // 1. get connection to db
    // Connection conn = ConnectionFactory.getConnection();

    // 2. create a statement
    Statement stmt;
    try {
      stmt = conn.createStatement();
      // 3. execute the query
      ResultSet result = stmt.executeQuery("SELECT * FROM employees");
      // 4. result set will be retrieved
      // as long as there is a next result, we create employee objects and add them to
      // the list (to be returned)
      while (result.next()) {
        Employee emp = extractEmployeeFromResultSet(result);
        employees.add(emp);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeConnection();
    }

    return Optional.ofNullable(employees);
  }

  /**
   * @param employeeId the id of the employee to be retrieved
   * @return the employee with the id @param employeeID
   */
  @Override
  public Optional<Employee> getById(int employeeId) {
    Employee emp = null; // initially no employee
    // 1. get connection to db
    // Connection conn = ConnectionFactory.getConnection();

    // 2. create a prepared statement - use ? for parameters
    PreparedStatement stmt;
    try {
      stmt = conn.prepareStatement("SELECT * FROM employees WHERE employee_id = ?");
      // 3. set the value of the first parameter to the employeeId
      stmt.setInt(1, employeeId);

      // 4. execute the query
      ResultSet result = stmt.executeQuery();

      // 5. return the result (one employee) or return null (if there is no employee)
      if (result.next()) {
        emp = extractEmployeeFromResultSet(result);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeConnection();
    }
    return Optional.ofNullable(emp);
  }

  /**
   * save the employee in the db
   *
   * @param emp the employee object that should be stored in the database
   */
  @Override
  public boolean save(Employee emp) {
    // Connection conn = ConnectionFactory.getConnection();
    int i = 0;
    PreparedStatement insertStatement;
    try {
      insertStatement = conn
          .prepareStatement("INSERT INTO employees (first_name, last_name, email, hire_date, job_id, salary) "
              + "VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS
          // see https://www.arundhaj.com/blog/getGeneratedKeys-with-postgresql.html
          );
      insertStatement.setString(1, emp.getFirstName());
      insertStatement.setString(2, emp.getLastName());
      insertStatement.setString(3, emp.getEmail());
      insertStatement.setDate(4, (Date) emp.getHireDate());
      insertStatement.setString(5, emp.getJob());
      insertStatement.setDouble(6, emp.getSalary());

      // return 1 or 0
      i = insertStatement.executeUpdate();
      if (i == 1) {
        ResultSet generatedKey = insertStatement.getGeneratedKeys();
        if (generatedKey.next()) {
          emp.setEmployeeId(generatedKey.getInt(1));
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeConnection();
    }
    return i == 1;
  }

  /**
   * updates the given employee emp in the database (identified by the employeeId)
   */
  @Override
  public boolean update(Employee emp) {
    int affectedRows = 0;
    PreparedStatement updateStatement;
    try {
      updateStatement = conn.prepareStatement("Update employees SET first_name = ?, last_name = ?, email = ?,"
          + " hire_date = ?, job_id = ? WHERE employee_id = ?");
      updateStatement.setString(1, emp.getFirstName());
      updateStatement.setString(2, emp.getLastName());
      updateStatement.setString(3, emp.getEmail());
      updateStatement.setDate(4, (Date) emp.getHireDate());
      updateStatement.setString(5, emp.getJob());
      updateStatement.setInt(6, emp.getEmployeeId());
      affectedRows = updateStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeConnection();
    }
    return affectedRows == 1 ? true : false;
  }

  private Employee extractEmployeeFromResultSet(ResultSet result) throws SQLException {
    Employee emp;
    emp = new Employee(result.getInt("employee_id"), result.getString("first_name"), result.getString("last_name"),
        result.getDouble("salary"), result.getDate("hire_date"), result.getString("job_id"), result.getString("email"));
    return emp;
  }
}
