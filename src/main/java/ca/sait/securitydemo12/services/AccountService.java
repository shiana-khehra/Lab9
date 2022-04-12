package ca.sait.securitydemo12.services;

import ca.sait.securitydemo12.dataaccess.UserDB;
import ca.sait.securitydemo12.models.User;
import ca.sait.securitydemo12.servlets.ForgotPasswordServlet;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountService {
    
    public User login(String email, String password) {
        UserDB userDB = new UserDB();
        
        try {
            User user = userDB.get(email);
            if (password.equals(user.getPassword())) {
                return user;
            }
        } catch (Exception ex) {
            Logger.getLogger(ForgotPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public boolean forgotPassword(String email, String path) {
        UserDB userDB = new UserDB();
        
        try {
            User user = userDB.get(email);
            if (user != null) {
                String to = user.getEmail();
                String subject = "Password Change";
                String template = path + "/emailTemplate.html";
                
                HashMap<String, String> tags = new HashMap<>();
                tags.put("firstName", user.getFirstName());
                tags.put("lastName", user.getLastName());
                tags.put("email", user.getEmail());
                tags.put("password", user.getPassword());
            
                GmailService.sendMail(to, subject, template, tags);
                
                return true;
            }
        } catch (Exception e) {
        }
        
        return false;
    }
}
