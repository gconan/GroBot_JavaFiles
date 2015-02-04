package groBot.servlet;

import static groBot.services.OfyService.ofy;
import groBot.dao.UserDAO;
import groBot.entity.User;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author namaz
 *
 */
public class ChangePasswordServlet extends SecureServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		super.doPost(req, resp);
		if(email == null) return;

		String currPassword = req.getParameter("currPassw");
		String newPassword = req.getParameter("newPassw");
		String verifyNewPassword = req.getParameter("verifNewPassw");
		Integer p = currPassword.hashCode();
		String pass = p.toString();
		p = newPassword.hashCode();
		String newpass = p.toString();
		p = verifyNewPassword.hashCode();
		String vnewpass = p.toString();
		
		
		if (!newpass.equals(vnewpass)) {
			// passwords dont match
			String encodedURL = resp.encodeRedirectURL("errorchangepassword.html");
	        resp.sendRedirect(encodedURL);
		} else {
			User user = new User(pass, email);
			User fromDB = UserDAO.INSTANCE.getUserByEmail(user.getEmail());
			if (!user.isSameUser(fromDB)) {
			// curr password is invalid
				String encodedURL = resp.encodeRedirectURL("errorchangepassword.html");
				resp.sendRedirect(encodedURL);
			} else {
				fromDB.setPassword(newpass);
				ofy().save().entity(fromDB).now();
				String encodedURL = resp.encodeRedirectURL("profile.jsp");
				resp.sendRedirect(encodedURL);
			}
		}
	}
}
