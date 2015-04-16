package groBot.servlet;

import groBot.dao.UserDAO;
import groBot.entity.GroBots;
import groBot.entity.User;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddGroBotServlet extends SecureServlet {
	
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
