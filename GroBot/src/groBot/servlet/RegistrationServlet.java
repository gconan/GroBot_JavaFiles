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
 * Registers a GroBot user.
 * @author conangammel
 *
 */
public class RegistrationServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	/**
	 * Reads the credentials from the HTML form, verifies that the passwords provided match, creates a new user, 
	 * sends he/she a verification email, then saves the User to the DataStore.
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		String first = req.getParameter("firstName");
		String last = req.getParameter("lastName");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String verifpassword = req.getParameter("verifyPass");
		
		
		if(first == null){
			resp.sendRedirect("/registrationerror.html");
			return;
			
		}else if(last == null){
			resp.sendRedirect("/registrationerror.html");
			return;
			
		}else if(email == null){
			resp.sendRedirect("/registrationerror.html");
			return;
			
		}else if(password == null){
			resp.sendRedirect("/registrationerror.html");
			return;
			
		}else if(verifpassword == null){
			resp.sendRedirect("/registrationerror.html");
			return;
		}
		
		
		if(password.length()<6 && verifpassword.length()<6){
			resp.sendRedirect("/registrationPasswordError.html");
			return;
		}
		
		if (!password.equals(verifpassword)) {    
			resp.sendRedirect("/passwordsDontMatch.html");
			return;
		}
		
		if(!email.contains("@")||!email.contains(".")){
			resp.sendRedirect("/invalidRegEmail.html");
			return;
		}
		
		Integer p = password.hashCode();
		p = verifpassword.hashCode();
		String vpass = p.toString();
		
    	if(UserDAO.INSTANCE.getUserByEmail(email) != null) {
    		resp.sendRedirect("/emailinuse.html");	//TODO update html page
    		return;
    	}
    	
    	User user = new User (first, last, vpass, email);
    	UserDAO.INSTANCE.addUser(user);
    	Email regEmail = new Email();
    	try {
    		regEmail.emailVerification(user);
    		resp.sendRedirect("/thanksforreg.html");	//TODO update html page
    	} catch (MessagingException m) {
    		UserDAO.INSTANCE.removeUser(user);
    		resp.sendRedirect("error.html");	//TODO update html page
    	}	
	}
	
	/**
	 * Activates a user. This method is called when the user clicks the link in his/her email to verify registration.
	 * If the user is saved in the DataStore, then he/she is activated and saved, then redirected to the confirmation page.
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	    User user = UserDAO.INSTANCE.getUserByAccessCode(req.getParameter("access"));
	    if (user == null) {
	    	// user not found. User may have typed the link manually; 
	    	resp.sendRedirect("error.html");	//TODO update html page
	    } else {
	    	// thanks for confirmation
	    	user.activate();
	    	UserDAO.INSTANCE.activateUser(user);
	    	resp.sendRedirect("confirm.html");	//TODO update html page
	    }
	}
}
