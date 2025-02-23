package config;
import java.sql.Connection;

//---------- Test database connection to "project_travel_agency" ----------//
public class test {
  public static void main(String[] args) {
      try (Connection conn = DatabaseConnection.getConnection()) {
          System.out.println("Connection successful");
      } catch (Exception e) {
          e.printStackTrace();
      }
  }
}
