import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class RSVPFormServlet extends HttpServlet {

    String dbURL = "jdbc:mysql://localhost:3306/eventlydb";
    String dbUser = "root";
    String dbPass = "abc@2003";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head><meta charset='UTF-8'>");
        out.println("<title>RSVP for Event - Evently</title>");
        out.println("<link rel='stylesheet' href='total.css'>");
        out.println("<link href='https://fonts.googleapis.com/css2?family=Balsamiq+Sans&display=swap' rel='stylesheet'>");

        // ✅ Updated styling
        out.println("<style>");
        out.println("body { font-family: 'Balsamiq Sans', cursive; background-color: #f9f9f9; margin: 0; padding: 0; }");
        out.println("h1 { background-color: #2c3e50; color: white; padding: 20px; text-align: center; margin: 0; }");
        out.println(".form-container { max-width: 500px; margin: 40px auto; background-color: white; padding: 30px; border-radius: 10px; box-shadow: 0 0 15px rgba(0,0,0,0.1); }");
        out.println("label { display: block; margin-top: 15px; font-size: 16px; }");
        out.println("input, select { width: 100%; padding: 10px; margin-top: 5px; font-size: 14px; border: 1px solid #ccc; border-radius: 5px; background-color: white; color: #000; }");
        out.println("select option { color: #000; background-color: #fff; }");  // ✅ Fix white text issue
        out.println("input[type=submit] { background-color: #3498db; color: white; padding: 12px; font-size: 16px; border: none; border-radius: 6px; margin-top: 20px; cursor: pointer; }");
        out.println("input[type=submit]:hover { background-color: #2980b9; }");
        out.println("footer { text-align: center; padding: 20px; color: #777; font-size: 14px; margin-top: 40px; }");
        out.println("</style>");

        // Optional JavaScript to filter dropdown (if needed)
        out.println("<script>");
        out.println("function filterEvents() {");
        out.println("  const input = document.getElementById('event_search');");
        out.println("  const select = document.getElementById('event_id');");
        out.println("  const filter = input.value.toLowerCase();");
        out.println("  for (let i = 0; i < select.options.length; i++) {");
        out.println("    const option = select.options[i];");
        out.println("    const text = option.text.toLowerCase();");
        out.println("    option.style.display = text.includes(filter) ? '' : 'none';");
        out.println("  }");
        out.println("}");
        out.println("</script>");

        out.println("</head><body>");

        out.println("<h1>Welcome To Evently ... An Event Management Portal!</h1>");
        out.println("<div class='form-container'>");
        out.println("<h2>RSVP for an Event</h2>");
        out.println("<form method='post'>");

        out.println("<label for='user_name'>Your Name:</label>");
        out.println("<input type='text' id='user_name' name='user_name' required>");

        out.println("<label for='event_search'>Search Event:</label>");
        out.println("<input type='text' id='event_search' onkeyup='filterEvents()' placeholder='Type to search event...'>");

        out.println("<label for='event_id'>Select Event:</label>");
        out.println("<select id='event_id' name='event_id' required>");
        out.println("<option value=''>-- Select an Event --</option>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, name FROM events");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                out.println("<option value='" + id + "'>" + name + "</option>");
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            out.println("<option disabled>Error loading events</option>");
            e.printStackTrace(out);
        }

        out.println("</select>");

        out.println("<label for='attendees'>Number of Attendees:</label>");
        out.println("<input type='number' id='attendees' name='attendees' min='1' required>");

        out.println("<input type='submit' value='Submit RSVP'>");
        out.println("</form></div>");
        out.println("<footer>© 2025 Evently. All rights reserved.</footer>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userName = request.getParameter("user_name");
        String eventId = request.getParameter("event_id");
        String attendees = request.getParameter("attendees");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPass);

            String sql = "INSERT INTO rsvp (event_id, user_name, attendees) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(eventId));
            pstmt.setString(2, userName);
            pstmt.setInt(3, Integer.parseInt(attendees));

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();

            response.sendRedirect("ViewEvent");

        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<h2>Error saving RSVP: " + e.getMessage() + "</h2>");
        }
    }
}
