import java.sql.*;

import com.mysql.cj.jdbc.Driver;

public class JDBCDemo {
  public static void main(String[] args) {
    try {
      DriverManager.registerDriver(new Driver());
      Config config = new Config();
      Connection connection = DriverManager.getConnection(
        config.getUrl(),
        config.getUser(),
        config.getPassword()
      );
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT * FROM albums");
      while (resultSet.next()) {
        System.out.println("id: " + resultSet.getLong("id"));
      }

      String query = "INSERT INTO albums"
        + "(artist, name_of_album, release_date, genre, sales) "
        + "VALUES('fall out boy', 'From Under the Cork Tree', 2005, 'Pop/Rock', 2.0)";

      statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
      resultSet = statement.getGeneratedKeys();
      if(resultSet.next())
        System.out.println("Inserted new record! new id: " + resultSet.getLong(1));

    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }
}
