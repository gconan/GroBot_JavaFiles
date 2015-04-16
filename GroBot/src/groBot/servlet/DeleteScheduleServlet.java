package groBot.servlet;

import groBot.dao.UserDAO;
import groBot.entity.Schedule;
import groBot.entity.User;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteScheduleServlet extends SecureServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		super.doPost(req, resp);
		if(email == null) return;
		
		//get schedule id
		String idstring = req.getParameter("schedID");
		if(idstring==null){
			resp.sendRedirect("/ScheduleError.html");
			return;
		}
		Long id = Long.parseLong(idstring);
		User user = UserDAO.INSTANCE.getUserByEmail(email);
		
		user.removeSchedule(id);
		UserDAO.INSTANCE.addUser(user);
		UserDAO.INSTANCE.removeSchedule(id);
		
		String encodedURL = resp.encodeRedirectURL("/profile.jsp");
		resp.sendRedirect(encodedURL);	
	}

}
