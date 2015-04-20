package groBot.servlet;

import groBot.dao.UserDAO;
import groBot.entity.GroBots;
import groBot.entity.User;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Reads the MAC address and name of the GroBot from the HTML form, then creates a GroBot and saves it to the DataStore.
 * @author conangammel
 *
 */
public class AddGroBotServlet extends SecureServlet {
	
	/**
	 * Takes the input from the HTML form and creates a GroBot.
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {	
		String mac = req.getParameter("mac");
		String name = req.getParameter("nameofbot");
		super.doPost(req, resp);
		if(email == null) return;
		User fromDB = UserDAO.INSTANCE.getUserByEmail(email);
		
		if(mac == null){
			resp.sendRedirect("/error.html");
		}
		if(name==null){
			name = fromDB.getFirstName() +"'s GroBot";
		}
		
		GroBots thisBot = new GroBots(name, mac, email);
		fromDB.addGroBot(thisBot);//sets the current bot to this one
		UserDAO.INSTANCE.addGroBot(thisBot);
		UserDAO.INSTANCE.addUser(fromDB);
		
		
		resp.sendRedirect("/welcome.jsp");
		
	}

}
