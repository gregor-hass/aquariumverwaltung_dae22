package dal.implementation;

import dal.ConnectionFactory;
import dal.interfaces.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Sensor;

/**
 * 
 * @author Julian-Paul Haslinger (P22080)
 *
 */
public class SensorDao extends DaoBase implements Dao<Sensor> {

  // TODO this method still needs to be implemented in this version of the REST
  // API
  @Override
  public Optional<List<Sensor>> getAll() {
    List<Sensor> sensors = new ArrayList<Sensor>();

    Connection conn = ConnectionFactory.getConnection();
    try{
      Statement stmt = conn.createStatement();
      ResultSet result = stmt.executeQuery("SELECT * FROM sensor;");
      while(result.next()){
        Sensor sensor = new Sensor(
          result.getInt("sensor_id"), 
          result.getDate("created_at"), 
          result.getString("friendly_name"), 
          result.getInt("employee_id")
          );
          sensors.add(sensor);
      }
    }catch(SQLException e){
      e.printStackTrace();
    }finally{
      closeConnection();
    }
    return Optional.ofNullable(sensors);
  }

  @Override
  public boolean save(Sensor t) {
    String addNewSensorQuery;
    addNewSensorQuery = "INSERT INTO sensor (created_at, friendly_name, employee_id) VALUES (?, ?, ?) RETURNING sensor_id;";
    boolean saved = false;

    try {
      PreparedStatement stmt = conn.prepareStatement(addNewSensorQuery);
      stmt.setDate(1, new java.sql.Date(t.getCreated_at().getTime()));
      stmt.setString(2, t.getFriendly_name());
      stmt.setInt(3, t.getEmployee_id());
      stmt.execute();
      ResultSet lastInsertedSensor = stmt.getResultSet();
      saved = lastInsertedSensor.next();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeConnection();
    }
    return saved;
  }

  // TODO this method has not been implemented in this version of the REST API
  @Override
  public boolean delete(Sensor t) {
    // Auto-generated method stub
    return false;
  }

  // TODO this method has not been implemented in this version of the REST API
  @Override
  public boolean update(Sensor t) {
    // Auto-generated method stub
    return false;
  }

  // TODO this method has not been implemented in this version of the REST API
  @Override
  public Optional<Sensor> getById(int id) {
    return null;
  }

}
