package groBot.servlet;

import groBot.dao.UserDAO;
import groBot.entity.Schedule;
import groBot.entity.User;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditScheduleServlet extends SecureServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		super.doPost(req, resp);
		if(email == null) return;
		User user = UserDAO.INSTANCE.getUserByEmail(email);
		Long id = Long.parseLong(req.getParameter("schedule"));
		Schedule current = UserDAO.INSTANCE.getSchedule(id);
		
		//get current schedule and set the information for the correct
				HttpSession session = req.getSession();
				
				//set attributes
				session.setAttribute("size", ""+user.getSchedules().size());
				session.setAttribute("schedID", ""+current.getId());
				session.setAttribute("schedName", current.getName());
				session.setAttribute("lightOn", ""+current.getLightOn());
				session.setAttribute("lightOff", ""+current.getLightOff());
				session.setAttribute("lightPins", ""+current.getLightPins());
				session.setAttribute("waterDur", ""+current.getWaterDuration());
				session.setAttribute("waterPer", ""+current.getWaterPeriod());
				session.setAttribute("aux", ""+current.getAux());
				session.setAttribute("air", ""+current.getAir());
				
				String encodedURL = resp.encodeRedirectURL("/EditSchedule.jsp");
				resp.sendRedirect(encodedURL);	
	}

}
