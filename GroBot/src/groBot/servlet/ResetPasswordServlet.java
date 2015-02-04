package groBot.servlet;

import static groBot.services.OfyService.ofy;
import groBot.dao.UserDAO;
import groBot.email.Email;
import groBot.entity.User;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author namaz
 *
 */
public class ResetPasswordServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String email = req.getParameter("email");
		
		// email field was left blank or not valid email
		if (email == null || !email.endsWith("@utexas.edu")) {
			resp.sendRedirect("/forgotpassword.html");
    		return;
		}
		
		User user = UserDAO.INSTANCE.getUserByEmail(email);
		
		// user was not found in database
		if(user == null) {
        	resp.sendRedirect("/notregisteredresetpass.html");
        	return;
        }
		
        Email fpEmail = new Email();
        try {
        	String newpass = user.resetPassword();
        	ofy().save().entity(user).now();
			fpEmail.forgotPassword(user, newpass);
	        resp.sendRedirect("/passresetemail.html");
		} catch (MessagingException m) {
			resp.sendRedirect("wrongemail.html");
		}
	}
}
