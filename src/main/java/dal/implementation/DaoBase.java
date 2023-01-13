package dal.implementation;

import dal.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;

public class DaoBase {

  // 1. get connection to db
  protected Connection conn = ConnectionFactory.getConnection();

  protected void closeConnection() {
    try {
      if (!conn.isClosed()) {
        conn.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
