import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * DAO for validating participant login credentials
 */
public class LoginDao {

    public static boolean validate(String username, String password) throws SQLException {
        boolean status = false;

        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish database connection
            try (Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/eventlydb", "root", "abc@2003")) {

                // Prepare SQL query to check credentials
                String query = "SELECT * FROM Participants WHERE Username = ? AND Password = ?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, username);
                pst.setString(2, password);

                // Execute query
                ResultSet rs = pst.executeQuery();
                status = rs.next(); // returns true if user exists
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return status;
    }
}
