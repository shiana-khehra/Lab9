package ca.sait.securitydemo12.servlets;

import ca.sait.securitydemo12.services.AccountService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Shiana Khehra
 */
public class ForgotPasswordServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/forgot.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         AccountService acService = new AccountService();
         String email = request.getParameter("email");
         String path = getServletContext().getRealPath("/WEB-INF");
         
         try {
            if (acService.forgotPassword(email, path)) {
                request.setAttribute("message", "If the address you entered is valid, you will receive an email very soon. Please check your email for your password.");
            } else {
                request.setAttribute("message", "Your email could not be reached. Please enter a valid email.");
            }
         } catch(Exception ex) {
             Logger.getLogger(ForgotPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
             return;
         }
         getServletContext().getRequestDispatcher("/WEB-INF/forgot.jsp").forward(request, response);
    }
    
}
