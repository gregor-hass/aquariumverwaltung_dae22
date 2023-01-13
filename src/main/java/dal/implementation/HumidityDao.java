package dal.implementation;

import dal.interfaces.Dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import model.Humidity;

/**
 * 
 * @author Julian-Paul Haslinger (P22080)
 *
 */
public class HumidityDao extends DaoBase implements Dao<Humidity> {

  // TODO this method has not been implemented in this version of the REST API
  @Override
  public boolean update(Humidity t) {
    return false;
  }

  public boolean deleteV1(String id) {
    String deleteHumidityQuery;
    deleteHumidityQuery = "DELETE FROM humidity WHERE id = " + id + ";";
    int affectedRows = 0;
    try {
      Statement deleteHumidityStatement = conn.createStatement();
      affectedRows = deleteHumidityStatement.executeUpdate(deleteHumidityQuery);
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeConnection();
    }
    return affectedRows == 1;
  }

  public boolean deleteV2(String id) {
    String deleteHumidityQuery;
    deleteHumidityQuery = "DELETE FROM humidity WHERE id = ?;";
    int affectedRows = 0;
    try {
      PreparedStatement deleteHumidityStatement = conn.prepareStatement(deleteHumidityQuery);
      deleteHumidityStatement.setInt(1, Integer.parseInt(id));
      affectedRows = deleteHumidityStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeConnection();
    }
    return affectedRows == 1;
  }

  @Override
  public boolean delete(Humidity t) {
    String deleteHumidityQuery;
    deleteHumidityQuery = "DELETE FROM humidity WHERE id = ?;";
    int affectedRows = 0;
    try {
      PreparedStatement deleteHumidityStatement = conn.prepareStatement(deleteHumidityQuery);
      deleteHumidityStatement.setInt(1, t.getId());
      affectedRows = deleteHumidityStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeConnection();
    }
    return affectedRows == 1;
  }

  @Override
  public Optional<List<Humidity>> getAll() {
    String selectHumidityQuery;
    selectHumidityQuery = "SELECT * FROM humidity;";
    List<Humidity> humidities = new ArrayList<Humidity>();
    // 3. execute the query
    try {
      ResultSet rs = conn.createStatement().executeQuery(selectHumidityQuery);
      // 4. result set will be retrieved
      // as long as there is a next result, we create sensor objects and add them to
      // the list (to be returned)
      while (rs.next()) {
        int idValue = rs.getInt("id");
        Timestamp tsValue = rs.getTimestamp("created_at");
        float rhValue = rs.getFloat("rh");
        int sensorIdValue = rs.getInt("sensor_id");
        humidities.add(new Humidity(idValue, tsValue, rhValue, sensorIdValue));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      // 5. close an existing connection
      closeConnection();
    }
    return Optional.ofNullable(humidities);
  }

  @Override
  public Optional<Humidity> getById(int id) {
    String selectHumidityByIdQuery;
    selectHumidityByIdQuery = "SELECT * FROM humidity WHERE id = " + id + ";";
    Humidity h = null;

    Statement stmt;
    try {
      stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(selectHumidityByIdQuery);
      if (rs.next()) {
        int idValue = rs.getInt("id");
        Timestamp tsValue = rs.getTimestamp("created_at");
        float rhValue = rs.getFloat("rh");
        int sensorIdValue = rs.getInt("sensor_id");
        h = new Humidity(idValue, tsValue, rhValue, sensorIdValue);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeConnection();
    }
    return Optional.ofNullable(h);
  }

  public Optional<List<Humidity>> getBySensorId(int sensorId) {
    String selectHumidityBySensorIdQuery;
    selectHumidityBySensorIdQuery = "SELECT * FROM humidity WHERE sensor_id = " + sensorId + ";";
    List<Humidity> humidities = new ArrayList<Humidity>();
    // 3. execute the query
    ResultSet rs;
    try {
      rs = conn.createStatement().executeQuery(selectHumidityBySensorIdQuery);
      // 4. result set will be retrieved
      // as long as there is a next result, we create sensor objects and add them to
      // the list (to be returned)
      while (rs.next()) {
        int idValue = rs.getInt("id");
        Timestamp tsValue = rs.getTimestamp("created_at");
        float rhValue = rs.getFloat("rh");
        int sensorIdValue = rs.getInt("sensor_id");
        humidities.add(new Humidity(idValue, tsValue, rhValue, sensorIdValue));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      // 5. close an existing connection
      closeConnection();
    }
    return Optional.ofNullable(humidities);
  }

  @Override
  public boolean save(Humidity t) {
    Timestamp ts = new Timestamp(new Date().getTime());
    String addNewHumidityQuery = "INSERT INTO humidity (rh, created_at, sensor_id) values ("
        + t.getRelativeHumidity().toString() + ", '" + ts.toString() + "', " + t.getSensor_id() + ");";
    int affectedRows = 0;
    try {
      Statement stmt = conn.createStatement();
      affectedRows = stmt.executeUpdate(addNewHumidityQuery);
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeConnection();
    }
    return affectedRows == 1;
  }
}
