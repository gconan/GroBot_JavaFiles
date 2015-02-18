package groBot.servlet;

import static groBot.services.OfyService.ofy;
import groBot.dao.UserDAO;
import groBot.entity.User;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * @author conangammel
 *
 */
public class ChangePasswordServlet extends SecureServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		super.doPost(req, resp);
		if(email == null) return;
				
		String email = super.getEmail(req, resp);
		String currPassword = req.getParameter("currPassw");
		String newPassword = req.getParameter("newPassw");
		String verifyNewPassword = req.getParameter("verifNewPassw");
		
		String hashPass = ""+currPassword.hashCode();
		String newHashPass = ""+newPassword.hashCode();
		String verNewHashPass = ""+verifyNewPassword.hashCode();
		
		
		if (!newHashPass.equals(verNewHashPass)) {
			// new passwords dont match TODO change to let them retry
			String encodedURL = resp.encodeRedirectURL("errorchangepassword.html");	//TODO update html
	        resp.sendRedirect(encodedURL);
		}else{
			User fromDB = UserDAO.INSTANCE.getUserByEmail(email);
			if (!fromDB.getPassword().equals(hashPass)) {
			// curr password is invalid
				String encodedURL = resp.encodeRedirectURL("errorchangepassword.html");
				resp.sendRedirect(encodedURL);
			} else {
				fromDB.setPassword(newHashPass);
				ofy().save().entity(fromDB).now();
				String encodedURL = resp.encodeRedirectURL("profile.jsp");	//TODO update jsp
				resp.sendRedirect(encodedURL);
			}
		}
	}
}
