import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Servlet for handling participant registration and storing card details
 */
public class Register extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Get parameters from the form
        String ename = request.getParameter("ename");
        String enumVal = request.getParameter("enum");
        String cardno = request.getParameter("cardno");
        String edate = request.getParameter("edate");
        String cvv = request.getParameter("cvv");
        String cname = request.getParameter("cname");

        // Validate input fields
        if (ename.isBlank() || enumVal.isBlank() || cardno.isBlank() || edate.isBlank() || cvv.isBlank() || cname.isBlank()) {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Please enter all event details!');");
            out.println("</script>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("Registration.html");
            requestDispatcher.include(request, response);
        } else {
            // Store to database
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String conURL = "jdbc:mysql://localhost:3306/eventlydb";
                String dbusername = "root";
                String dbpassword = "abc@2003";

                try (Connection con = DriverManager.getConnection(conURL, dbusername, dbpassword)) {
                    con.setAutoCommit(false);
                    String sql = "INSERT INTO card (ename, enum, cardno, edate, cvv, cname) VALUES (?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement pst = con.prepareStatement(sql)) {
                        pst.setString(1, ename);
                        pst.setString(2, enumVal);
                        pst.setString(3, cardno);
                        pst.setString(4, edate);
                        pst.setString(5, cvv);
                        pst.setString(6, cname);
                        pst.executeUpdate();
                        con.commit();
                    }

                    // Redirect to payment page after success
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("Payment.html");
                    requestDispatcher.forward(request, response);

                }

            } catch (ClassNotFoundException | SQLException e) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Registration failed! Please try again.');");
                out.println("</script>");
                e.printStackTrace(out);  // for debugging
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("Registration.html");
                requestDispatcher.include(request, response);
            }
        }
    }
}
