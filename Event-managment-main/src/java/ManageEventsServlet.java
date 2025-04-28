import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class ManageEventsServlet extends HttpServlet {

    String dbURL = "jdbc:mysql://localhost:3306/eventlydb";
    String dbUser = "root";
    String dbPass = "abc@2003";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Update Events - Evently</title>");
        out.println("<link href='https://fonts.googleapis.com/css2?family=Balsamiq+Sans&display=swap' rel='stylesheet'>");
        out.println("<style>");
        out.println("body { font-family: 'Balsamiq Sans', cursive; background-color: #f4f4f4; padding: 20px; }");
        out.println("h1 { text-align: center; color: #2c3e50; }");
        out.println("form { margin: 0; }");
        out.println("table { width: 100%; border-collapse: collapse; background: white; }");
        out.println("th, td { padding: 10px; text-align: center; border: 1px solid #ddd; }");
        out.println("input[type='text'], input[type='date'] { width: 95%; padding: 5px; border-radius: 5px; border: 1px solid #ccc; }");
        out.println("input[type='submit'] { padding: 6px 12px; border: none; border-radius: 5px; color: white; cursor: pointer; }");
        out.println(".update-btn { background-color: #3498db; }");
        out.println(".delete-btn { background-color: #e74c3c; }");
        out.println("</style>");
        out.println("</head><body>");
        out.println("<h1>Manage Events</h1>");

        try {
            // Load MySQL JDBC driver (Important!)
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM events")) {

                out.println("<table>");
                out.println("<tr><th>ID</th><th>Name</th><th>Type</th><th>Location</th><th>Date</th><th>Description</th><th>Actions</th></tr>");

                while (rs.next()) {
                    int id = rs.getInt("id");
                    out.println("<form method='post'>");
                    out.println("<tr>");
                    out.println("<td><input type='hidden' name='id' value='" + id + "'>" + id + "</td>");
                    out.println("<td><input type='text' name='name' value='" + rs.getString("name") + "'></td>");
                    out.println("<td><input type='text' name='type' value='" + rs.getString("type") + "'></td>");
                    out.println("<td><input type='text' name='location' value='" + rs.getString("location") + "'></td>");
                    out.println("<td><input type='date' name='date' value='" + rs.getDate("date") + "'></td>");
                    out.println("<td><input type='text' name='description' value='" + rs.getString("description") + "'></td>");
                    out.println("<td>");
                    out.println("<input type='submit' name='action' value='Update' class='update-btn'> ");
                    out.println("<input type='submit' name='action' value='Delete' class='delete-btn'>");
                    out.println("</td>");
                    out.println("</tr>");
                    out.println("</form>");
                }

                out.println("</table>");
            }

        } catch (ClassNotFoundException e) {
            out.println("<p style='color:red;'>MySQL JDBC Driver not found. Please add it to your classpath.</p>");
        } catch (Exception e) {
            out.println("<p style='color:red;'>Error: " + e.getMessage() + "</p>");
        }

        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        int id = Integer.parseInt(request.getParameter("id"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPass)) {

                if ("Update".equals(action)) {
                    String name = request.getParameter("name");
                    String type = request.getParameter("type");
                    String location = request.getParameter("location");
                    String date = request.getParameter("date");
                    String description = request.getParameter("description");

                    String sql = "UPDATE events SET name=?, type=?, location=?, date=?, description=? WHERE id=?";
                    try (PreparedStatement ps = conn.prepareStatement(sql)) {
                        ps.setString(1, name);
                        ps.setString(2, type);
                        ps.setString(3, location);
                        ps.setDate(4, Date.valueOf(date));
                        ps.setString(5, description);
                        ps.setInt(6, id);
                        ps.executeUpdate();
                    }

                } else if ("Delete".equals(action)) {
                    String sql = "DELETE FROM events WHERE id=?";
                    try (PreparedStatement ps = conn.prepareStatement(sql)) {
                        ps.setInt(1, id);
                        ps.executeUpdate();
                    }
                }

            }
        } catch (Exception e) {
            response.getWriter().println("<p style='color:red;'>Error: " + e.getMessage() + "</p>");
        }

        response.sendRedirect("ManageEventsServlet");
    }
}
