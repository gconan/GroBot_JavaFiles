package groBot.servlet;

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
public class RegistrationServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String verifpassword = req.getParameter("verifyPass");
		Integer p = password.hashCode();
		String pass = p.toString();
		p = verifpassword.hashCode();
		String vpass = p.toString();		
		
		
		if (!password.equals(verifpassword)) {    
			resp.sendRedirect("/registrationerror.html");
			return;
		}

		// If email address is not in utexas domain 
		if (!email.endsWith("@utexas.edu")) {
			resp.sendRedirect("/registrationerror.html");
			return;
		}

    	if(UserDAO.INSTANCE.getUserByEmail(email) != null) {
    		resp.sendRedirect("/emailinuse.html");
    		return;
    	}
    	
    	User user = new User(pass, email);
    	UserDAO.INSTANCE.addUser(user);
    	Email regEmail = new Email();
    	try {
    		regEmail.emailVerification(user);
    		resp.sendRedirect("/thanksforreg.html");
    	} catch (MessagingException m) {
    		UserDAO.INSTANCE.removeUser(user);
    		resp.sendRedirect("wrongemail.html");
    	}	
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	    User user = UserDAO.INSTANCE.getUserByAccessCode(req.getParameter("access"));
	    if (user == null) {
	    	// user not found. User may have typed the link manually; 
	    	resp.sendRedirect("error.html");
	    } else {
	    	// thanks for confirmation
	    	user.activate();
	    	UserDAO.INSTANCE.activateUser(user);
	    	resp.sendRedirect("confirm.html");
	    }
	}
}
