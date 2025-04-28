import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author adrianadewunmi
 * Participant Login Validation Servlet
 */
public class VaPa extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        
        try (PrintWriter out = response.getWriter()) {
            String userName = request.getParameter("Pausername");
            String userPassword = request.getParameter("Papassword");

            // Check if username or password is blank
            if (userName.isBlank() || userPassword.isBlank()) {
                out.println("<script type='text/javascript'>");
                out.println("alert('Please enter your login details!');");
                out.println("window.location = 'Plogin.html';");
                out.println("</script>");
                return;
            }

            try {
                if (LoginDao.validate(userName, userPassword)) {
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("ParticipantEvent.html");
                    requestDispatcher.forward(request, response);
                } else {
                    out.println("<script type='text/javascript'>");
                    out.println("alert('Sorry! Username or password is incorrect!');");
                    out.println("window.location = 'Plogin.html';");
                    out.println("</script>");
                }
            } catch (SQLException ex) {
                Logger.getLogger(VaPa.class.getName()).log(Level.SEVERE, null, ex);
                out.println("<script type='text/javascript'>");
                out.println("alert('Internal error occurred. Please try again later.');");
                out.println("window.location = 'Plogin.html';");
                out.println("</script>");
            }
        }
    }
}
