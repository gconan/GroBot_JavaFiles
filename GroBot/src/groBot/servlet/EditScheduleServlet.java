package groBot.servlet;

import groBot.dao.UserDAO;
import groBot.entity.Schedule;
import groBot.entity.User;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Sets up the HTML session for editing the schedule selected by the user.
 * @author conangammel
 *
 */
public class EditScheduleServlet extends SecureServlet {
	
	/**
	 * Reads the schedule ID from the HTML form then loads it from the DataStore, then sets HTMLSession data to match the previously
	 * stored schedule parameters before redirecting to the JSP page that initializes the schedule information with the set session data.
	 */
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
