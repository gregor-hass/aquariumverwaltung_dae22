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

import model.Waterchange;

public class WaterchangeDao extends DaoBase implements Dao<Waterchange>{
    

    private Waterchange extractWaterchangeFromResultSet(ResultSet result) throws SQLException {
        Waterchange waterchange;
        waterchange = new Waterchange(result.getInt("aquarium_id"), result.getTimestamp("time_stamp"), result.getDouble("amount"));
        return waterchange;
    }

    @Override
    public boolean delete(Waterchange t) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Optional<List<Waterchange>> getAll() {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public Optional<List<Waterchange>> getAllByAquariumId(int id) {
        List<Waterchange> aqs = new ArrayList<Waterchange>();
    
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement("SELECT * FROM waterchanges WHERE aquarium_id = ?");
            stmt.setInt(1, id);
    
            // 3. execute the query
            ResultSet result = stmt.executeQuery();
            // 4. result set will be retrieved
            // as long as there is a next result, we create employee objects and add them to
            // the list (to be returned)
            
            while (result.next()) {
                Waterchange aq = extractWaterchangeFromResultSet(result);
                aqs.add(aq);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        closeConnection();
        }
    
        return Optional.ofNullable(aqs);
    }

    @Override
    public Optional<Waterchange> getById(int id) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public boolean save(Waterchange waterchange) {
        // Connection conn = ConnectionFactory.getConnection();
        int i = 0;
        PreparedStatement insertStatement;
        try {
          insertStatement = conn
              .prepareStatement("INSERT INTO waterchanges (aquarium_id, amount) "
                  + "VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS
              // see https://www.arundhaj.com/blog/getGeneratedKeys-with-postgresql.html
              );
          insertStatement.setInt(1, waterchange.getAquarium_id());
          insertStatement.setDouble(2, waterchange.getAmount());
    
          // return 1 or 0
          i = insertStatement.executeUpdate();
          if (i == 1) {
            ResultSet generatedKey = insertStatement.getGeneratedKeys();
            if (generatedKey.next()) {
                waterchange.setTime(generatedKey.getTimestamp("time_stamp"));
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
    public boolean update(Waterchange t) {
        // TODO Auto-generated method stub
        return false;
    }

}
