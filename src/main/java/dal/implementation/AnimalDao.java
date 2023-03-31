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

import model.Animal;

public class AnimalDao extends DaoBase implements Dao<Animal>{

    @Override
    public boolean delete(Animal animal) {
        // Connection conn = ConnectionFactory.getConnection();
        int affectedRows = 0;
        PreparedStatement deleteStatement;
        try {
          deleteStatement = conn.prepareStatement("DELETE FROM animals WHERE animal_id = ?");
          deleteStatement.setInt(1, animal.getAnimalId());
          affectedRows = deleteStatement.executeUpdate();
        } catch (SQLException e) {
          e.printStackTrace();
        } finally {
          closeConnection();
        }
        return affectedRows == 1 ? true : false;
    
    }

    @Override
    public Optional<List<Animal>> getAll() {
        List<Animal> aqs = new ArrayList<Animal>();
    
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement("SELECT * FROM animals");
    
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                Animal aq = extractAnimalFromResultSet(result);
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
    public Optional<Animal> getById(int id) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public boolean save(Animal animal) {
        int i = 0;
        PreparedStatement insertStatement;
        try {
            if(animal.getSpecies().isEmpty()){
                
                if(animal.getMorph().isEmpty()){
                    insertStatement = conn
                        .prepareStatement("INSERT INTO animals (genus, species) "
                            + "VALUES (?, sp.)", Statement.RETURN_GENERATED_KEYS
                        );
                    insertStatement.setString(1, animal.getGenus());
                }
                else{
                    insertStatement = conn
                        .prepareStatement("INSERT INTO animals (genus, species, morph) "
                            + "VALUES (?, sp., ?)", Statement.RETURN_GENERATED_KEYS
                        );
                    insertStatement.setString(1, animal.getGenus());
                    insertStatement.setString(2, animal.getMorph());

                }
            }
            else if(animal.getMorph().isEmpty()){
                insertStatement = conn
                    .prepareStatement("INSERT INTO animals (genus, species) "
                        + "VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS
                    );
                insertStatement.setString(1, animal.getGenus());
                insertStatement.setString(2, animal.getSpecies());

            }else{
                insertStatement = conn
                    .prepareStatement("INSERT INTO animals (genus, species, morph) "
                        + "VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS
                    );
                insertStatement.setString(1, animal.getGenus());
                insertStatement.setString(2, animal.getSpecies());
                insertStatement.setString(3, animal.getMorph());


            }
    
          i = insertStatement.executeUpdate();
          if (i == 1) {
            ResultSet generatedKey = insertStatement.getGeneratedKeys();
            if (generatedKey.next()) {
                animal.setAnimalId(generatedKey.getInt(1));
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
    public boolean update(Animal t) {
        // TODO Auto-generated method stub
        return false;
    }

    private Animal extractAnimalFromResultSet(ResultSet result) throws SQLException {
        Animal aq;
        aq = new Animal(result.getInt("animal_id"), result.getString("genus"), result.getString("species"), result.getString("morph"));
        return aq;
    }
}