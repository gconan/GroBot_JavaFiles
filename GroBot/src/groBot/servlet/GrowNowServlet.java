package groBot.servlet;

import groBot.dao.UserDAO;
import groBot.entity.Schedule;
import groBot.entity.User;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Updates a user and his/her GroBot's current schedule with the schedule selected by the user.
 * @author conangammel
 *
 */
public class GrowNowServlet extends SecureServlet {
	
	/**
	 * Updates the User's current schedule to the one selected by the user. Increments the schedule's popularity by one. 
	 * Updates the user's current GroBot's schedule to the schedule selected.
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		super.doPost(req, resp);
		if(email == null) return;
		User user = UserDAO.INSTANCE.getUserByEmail(email);
		Long id = Long.parseLong(req.getParameter("schedule"));
		Schedule sched = UserDAO.INSTANCE.getSchedule(id);
		sched.upPopularity();
		user.getGroBot().runSchedule(sched);
		user.orderScheduleList(id);
		//TODO figure out how to maintain an ordered list for the schedules
		//user.update
		
		UserDAO.INSTANCE.addGroBot(user.getGroBot());
		UserDAO.INSTANCE.addUser(user);
		UserDAO.INSTANCE.addSchedule(sched);
		
		String encodedURL = resp.encodeRedirectURL("status.jsp");
		resp.sendRedirect(encodedURL);
		
	}

}
