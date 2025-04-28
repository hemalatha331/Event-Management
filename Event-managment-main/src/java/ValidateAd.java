import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

/**
 * Admin Login Validation Servlet
 * for administrator login
 * @author adrianadewunmi
 */
public class ValidateAd extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()) {
            
            String userName = request.getParameter("Ausername");
            String userPassword = request.getParameter("Apassword");

            // Valid admin credentials
            String[][] adminCredentials = {
                {"A101", "Admin101"},
                {"A202", "Admin202"},
                {"A303", "Admin303"},
                {"A404", "Admin404"}
            };

            boolean isValid = false;

            // Check if the credentials match any valid admin
            for (String[] admin : adminCredentials) {
                if (admin[0].equals(userName) && admin[1].equals(userPassword)) {
                    isValid = true;
                    break;
                }
            }

            if (isValid) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("AdminEvent.html");
                dispatcher.forward(request, response);
            } else {
                out.println("<script type='text/javascript'>");
                out.println("alert('Please Enter Valid Username & Password for Admin!!!');");
                out.println("window.location = 'Alogin.html';");
                out.println("</script>");
            }
        }
    }
}
