import java.io.FileWriter;
import java.sql.*;

public class EventDataExporter {
    public static void exportToArff(String filePath) {
        String url = "jdbc:mysql://localhost:3306/eventlydb"; // change DB details
        String user = "root";
        String password = "abc@2003";

        try (
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT name, type, location, description FROM events");
            FileWriter writer = new FileWriter(filePath)
        ) {
            writer.write("@relation events\n\n");
            writer.write("@attribute name string\n");
            writer.write("@attribute location string\n");
            writer.write("@attribute description string\n");
            writer.write("@attribute type {Conference,Wedding,Concert,Workshop,Meetup,Party}\n\n");
            writer.write("@data\n");

            while (rs.next()) {
                writer.write("'" + rs.getString("name").replace("'", "\\'") + "',");
                writer.write("'" + rs.getString("location").replace("'", "\\'") + "',");
                writer.write("'" + rs.getString("description").replace("'", "\\'") + "',");
                writer.write(rs.getString("type") + "\n");
            }

            System.out.println("Exported to: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
