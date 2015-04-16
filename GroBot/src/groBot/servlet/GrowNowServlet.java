package groBot.servlet;

import groBot.dao.UserDAO;
import groBot.entity.Schedule;
import groBot.entity.User;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GrowNowServlet extends SecureServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		super.doPost(req, resp);
		if(email == null) return;
		User user = UserDAO.INSTANCE.getUserByEmail(email);
		Long id = Long.parseLong(req.getParameter("schedule"));
		Schedule sched = UserDAO.INSTANCE.getSchedule(id);
		sched.upPopularity();
		user.getGroBot().runSchedule(sched);
		//TODO figure out how to maintain an ordered list for the schedules
		//user.update
		
		
		UserDAO.INSTANCE.addUser(user);
		UserDAO.INSTANCE.addSchedule(sched);
		
		String encodedURL = resp.encodeRedirectURL("status.jsp");
		resp.sendRedirect(encodedURL);
		
	}

}
