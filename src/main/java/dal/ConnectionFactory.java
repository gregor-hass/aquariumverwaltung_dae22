package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.postgresql.Driver;
import org.springframework.beans.factory.annotation.Value;

/**
 * documentation to the jdbc drivers can be found here:
 * https://jdbc.postgresql.org/documentation/head/index.html
 * 
 * @author Julian-Paul Haslinger (P22080)
 */
public class ConnectionFactory {

  // URL for connecting :
  // https://jdbc.postgresql.org/documentation/head/connect.html
  
  @Value("${spring.datasource.url}")
  private static final String localPostgresDbUrl = "jdbc:postgresql://containers-us-west-70.railway.app:6735/postgres";
  private static final String USER = "postgres";
  private static final String PASS = "FGbk2LJP3hpWyuCeh0z6";
  private static final String SCHEMA = "hr";
  private static final String LOGLEVEL = "TRACE"; // OFF, DEBUG, TRACE

  /**
   * Get a connection to database.
   *
   * @return Connection object
   */
  public static Connection getConnection() {
    try {
      return getRemoteDatabaseConnection();
    } catch (Throwable t) {
      t.printStackTrace();
    }
    return null;
  }

  private static Connection getRemoteDatabaseConnection() {
    try {
      DriverManager.registerDriver(new Driver());
      Properties connectionProperties = new Properties();
      connectionProperties.setProperty("user", USER);
      connectionProperties.setProperty("password", PASS);
      connectionProperties.setProperty("currentSchema", SCHEMA);
      connectionProperties.setProperty("loggerLevel", LOGLEVEL);
      return DriverManager.getConnection(localPostgresDbUrl, connectionProperties);
    } catch (SQLException ex) {
      throw new RuntimeException("Error connecting to the database", ex);
    }
  }
}