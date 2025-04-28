package controllers;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;

public class AddEvent extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String type = request.getParameter("type");
        String location = request.getParameter("location");
        String dateStr = request.getParameter("date");
        String description = request.getParameter("description");

        if (name == null || name.isBlank() ||
            type == null || type.isBlank() ||
            location == null || location.isBlank() ||
            dateStr == null || dateStr.isBlank() ||
            description == null || description.isBlank()) {

            out.println("<script type='text/javascript'>");
            out.println("alert('Please fill in all fields!');");
            out.println("window.location.href = 'createEvent.html';");
            out.println("</script>");
            return;
        }

        try {
            java.sql.Date date = java.sql.Date.valueOf(dateStr); // Format: yyyy-MM-dd

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/eventlydb", "root", "abc@2003");

            String sql = "INSERT INTO events(name, type, location, date, description) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, type);
            ps.setString(3, location);
            ps.setDate(4, date);
            ps.setString(5, description);

            int result = ps.executeUpdate();
            if (result > 0) {
                out.println("<script type='text/javascript'>");
                out.println("alert('Event added successfully!');");
                out.println("window.location.href = 'ViewEvent1';");
                out.println("</script>");
            }

            ps.close();
            con.close();

        } catch (IllegalArgumentException e) {
            out.println("<h3>Error: Invalid date format. Please use yyyy-MM-dd.</h3>");
        } catch (SQLException | ClassNotFoundException e) {
            out.println("<h3>Database Error: " + e.getMessage() + "</h3>");
        }
    }
}
