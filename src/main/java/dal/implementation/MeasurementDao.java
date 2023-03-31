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

import model.Measurement;

public class MeasurementDao extends DaoBase implements Dao<Measurement>{
    

    private Measurement extractMeasurementFromResultSet(ResultSet result) throws SQLException {
        Measurement measurement;
        measurement = new Measurement(result.getInt("aquarium_id"), result.getTimestamp("time_stamp"), result.getString("val"), result.getDouble("amount"));
        return measurement;
    }

    @Override
    public boolean delete(Measurement t) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Optional<List<Measurement>> getAll() {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public Optional<List<Measurement>> getAllByAquariumId(int id) {
        List<Measurement> measurements = new ArrayList<Measurement>();
    
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement("SELECT * FROM measurements WHERE aquarium_id = ?");
            stmt.setInt(1, id);
    
            // 3. execute the query
            ResultSet result = stmt.executeQuery();
            // 4. result set will be retrieved
            // as long as there is a next result, we create employee objects and add them to
            // the list (to be returned)
            
            while (result.next()) {
                Measurement measurement = extractMeasurementFromResultSet(result);
                measurements.add(measurement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        closeConnection();
        }
    
        return Optional.ofNullable(measurements);
    }

    @Override
    public Optional<Measurement> getById(int id) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public boolean save(Measurement measurement) {
        // Connection conn = ConnectionFactory.getConnection();
        int i = 0;
        PreparedStatement insertStatement;
        try {
          insertStatement = conn
              .prepareStatement("INSERT INTO measurements (aquarium_id,val,amount) "
                  + "VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS
              // see https://www.arundhaj.com/blog/getGeneratedKeys-with-postgresql.html
              );
          insertStatement.setInt(1, measurement.getAquarium_id());
          insertStatement.setString(2, measurement.getType());
          insertStatement.setDouble(3, measurement.getAmount());
    
          // return 1 or 0
          i = insertStatement.executeUpdate();
          if (i == 1) {
            ResultSet generatedKey = insertStatement.getGeneratedKeys();
            if (generatedKey.next()) {
                measurement.setTime(generatedKey.getTimestamp("time_stamp"));
            }
          }
        } catch (SQLException e) {
          e.printStackTrace();
        } finally {
          closeConnection();
        }
        return i == 1;
    }

    @Override
    public boolean update(Measurement t) {
        // TODO Auto-generated method stub
        return false;
    }


}
