package groBot.servlet;

import groBot.entity.GroBots;
import groBot.dao.UserDAO;
import groBot.entity.User;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Changes the GroBot the user is currently connected to.
 * @author conangammel
 *
 */
public class ConnectToNewBotServlet extends SecureServlet {
	
	/**
	 * Sets the user's currentBot to the GroBot specified by the user in the HTML form.
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		super.doPost(req, resp);
		if(email == null) return;
		
		User user = UserDAO.INSTANCE.getUserByEmail(email);
		
		Long id = Long.parseLong(req.getParameter("botList"));
		GroBots newBot = UserDAO.INSTANCE.getGroBot(id);
		
		user.setCurrentBot(newBot);
		UserDAO.INSTANCE.addUser(user);
		
		HttpSession session = req.getSession();
		session.setAttribute("botName", newBot.getName());
		
		resp.sendRedirect("/welcome.jsp");
	}

}
