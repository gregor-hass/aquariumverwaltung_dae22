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

import model.Aquarium;

public class AquariumDao extends DaoBase implements Dao<Aquarium>{
    
  /**
   * deletes the given Aquarium from the db
   */
  @Override
  public boolean delete(Aquarium aq) {
    // Connection conn = ConnectionFactory.getConnection();
    int affectedRows = 0;
    PreparedStatement deleteStatement;
    try {
      deleteStatement = conn.prepareStatement("DELETE FROM aquariums WHERE aquarium_id = ?");
      deleteStatement.setInt(1, aq.getAquariumId());
      affectedRows = deleteStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeConnection();
    }
    return affectedRows == 1 ? true : false;

  }

  
  /**
   * save the aquarium in the db
   *
   * @param aq the aq object that should be stored in the database
   */
  @Override
  public boolean save(Aquarium aq) {
    // Connection conn = ConnectionFactory.getConnection();
    int i = 0;
    PreparedStatement insertStatement;
    try {
      insertStatement = conn
          .prepareStatement("INSERT INTO aquariums (aquarium_vol, aquarium_room) "
              + "VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS
          // see https://www.arundhaj.com/blog/getGeneratedKeys-with-postgresql.html
          );
      insertStatement.setDouble(1, aq.getVolume());
      insertStatement.setString(2, aq.getRoom());

      // return 1 or 0
      i = insertStatement.executeUpdate();
      if (i == 1) {
        ResultSet generatedKey = insertStatement.getGeneratedKeys();
        if (generatedKey.next()) {
            aq.setAquariumId(generatedKey.getInt(1));
            System.out.println(aq.getAquariumId());
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
public Optional<List<Aquarium>> getAll() {
    List<Aquarium> aqs = new ArrayList<Aquarium>();

    PreparedStatement stmt;
    try {
        stmt = conn.prepareStatement("SELECT * FROM aquariums");

        // 3. execute the query
        ResultSet result = stmt.executeQuery();
        // 4. result set will be retrieved
        // as long as there is a next result, we create employee objects and add them to
        // the list (to be returned)
        
        while (result.next()) {
            Aquarium aq = extractAquariumFromResultSet(result);
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
public Optional<Aquarium> getById(int id) {
    // TODO Auto-generated method stub
    return Optional.empty();
}


@Override
public boolean update(Aquarium t) {
    // TODO Auto-generated method stub
    return false;
}

private Aquarium extractAquariumFromResultSet(ResultSet result) throws SQLException {
    Aquarium aq;
    aq = new Aquarium(result.getInt("aquarium_id"), result.getDouble("aquarium_vol"), result.getString("aquarium_room"));
    return aq;
  }
}
