import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;

/**
 * Servlet to view all events from the 'events' table
 */
public class ViewEvent1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>View Events - Evently</title>");
        out.println("<link rel='stylesheet' href='total.css'>");
        out.println("<link href='https://fonts.googleapis.com/css2?family=Balsamiq+Sans&display=swap' rel='stylesheet'>");
        out.println("</head>");
        out.println("<body>");

        out.println("<h1 style='text-align: center;'>Welcome To Evently - Event Management Portal</h1>");
        out.println("<center><h2>All Event Details</h2></center>");

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Load JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Database connection
            String dbURL = "jdbc:mysql://localhost:3306/eventlydb";
            String username = "root";
            String password = "abc@2003";

            con = DriverManager.getConnection(dbURL, username, password);
            stmt = con.createStatement();

            String query = "SELECT * FROM events";
            rs = stmt.executeQuery(query);

            out.println("<center>");
            out.println("<table border='1' cellpadding='10' cellspacing='0' width='90%' style='border-collapse: collapse; text-align: center;'>");
            out.println("<tr style='background-color: #f2f2f2;'>");
            out.println("<th>ID</th>");
            out.println("<th>Event Name</th>");
            out.println("<th>Type</th>");
            out.println("<th>Location</th>");
            out.println("<th>Date</th>");
            out.println("<th>Description</th>");
            out.println("</tr>");

            boolean hasResults = false;
            while (rs.next()) {
                hasResults = true;
                out.println("<tr>");
                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getString("name") + "</td>");
                out.println("<td>" + rs.getString("type") + "</td>");
                out.println("<td>" + rs.getString("location") + "</td>");
                out.println("<td>" + rs.getDate("date") + "</td>");
                out.println("<td>" + rs.getString("description") + "</td>");
                out.println("</tr>");
            }

            if (!hasResults) {
                out.println("<tr><td colspan='6'>No events found.</td></tr>");
            }

            out.println("</table>");
            out.println("</center>");

        } catch (ClassNotFoundException e) {
            out.println("<p style='color: red; text-align: center;'>JDBC Driver not found: " + e.getMessage() + "</p>");
        } catch (SQLException e) {
            out.println("<p style='color: red; text-align: center;'>Database error: " + e.getMessage() + "</p>");
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                out.println("<p style='color: red; text-align: center;'>Error closing DB: " + e.getMessage() + "</p>");
            }
        }

        out.println("<div style='text-align:center; margin-top: 30px;'>");
        out.println("<a href='CreateE.html'><button>Add New Event</button></a>");
        out.println("<a href='ManageEventsServlet'><button>Update Event</button></a>");
        out.println("<a href='index.html'><button>Logout</button></a>");
        out.println("</div>");

        out.println("<footer style='text-align:center; margin-top: 50px;'>");
        out.println("<p>© 2025 Evently. All rights reserved.</p>");
        out.println("</footer>");

        out.println("</body>");
        out.println("</html>");
    }
}
