import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Servlet for adding participant details to the database
 */
public class StoreP extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Fetching form data
        String fullName = request.getParameter("Pname");
        String username = request.getParameter("Pusername");
        String password = request.getParameter("Ppassword");
        String confirmPassword = request.getParameter("Cpassword");

        // Basic validation
        if (fullName == null || fullName.isBlank() ||
            username == null || username.isBlank() ||
            password == null || password.isBlank() ||
            confirmPassword == null || confirmPassword.isBlank()) {

            out.println("<script type=\"text/javascript\">");
            out.println("alert('Please fill in all fields.');");
            out.println("location='Psignup.html';");
            out.println("</script>");
            return;
        }

        if (!password.equals(confirmPassword)) {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Password and Confirm Password must match.');");
            out.println("location='Psignup.html';");
            out.println("</script>");
            return;
        }

        try {
            // Load driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to DB
            String conURL = "jdbc:mysql://localhost:3306/eventlydb";
            String dbUsername = "root";
            String dbPassword = "abc@2003";
            Connection con = DriverManager.getConnection(conURL, dbUsername, dbPassword);

            // Insert participant into Participants table
            String query = "INSERT INTO Participants (Username, Password, FullName) VALUES (?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, password);
            pst.setString(3, fullName);

            int rows = pst.executeUpdate();

            if (rows > 0) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Registration successful! Please log in.');");
                out.println("location='Plogin.html';");
                out.println("</script>");
            }

            con.close();
        } catch (SQLException e) {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Username already exists or database error.');");
            out.println("location='Psignup.html';");
            out.println("</script>");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            out.println("<p>Database driver not found.</p>");
        }
    }
}
