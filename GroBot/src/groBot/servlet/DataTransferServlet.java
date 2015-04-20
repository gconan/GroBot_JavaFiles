package groBot.servlet;

import groBot.dao.UserDAO;
import groBot.entity.GroBots;
import groBot.entity.Schedule;
import groBot.entity.User;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * An HTTP request comes in from the microcontroller which calls this servlet to update the status of the GroBot.
 * @author conangammel
 *
 */
public class DataTransferServlet extends HttpServlet {
	
/**
 * Gets the data from the HTTP request made by the microcontroller, then updates the GroBot's status in the DataStore.
 */
public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
//Read all "current" data from GroBot
		String mac = req.getParameter("mac").trim();
		String aux = req.getParameter("aux");
		String air = req.getParameter("air");
		String lightPins = req.getParameter("lightPins");
		String lightOn = req.getParameter("lightOn");
		String lightTimeRemaining = req.getParameter("ltime");
		String waterOn = req.getParameter("waterOn");
		String waterTimeRemaining = req.getParameter("wtime");
		

//update info into GroBot so that if the user goes to the "current status" page
//the information will be updated
		GroBots fromDB = UserDAO.INSTANCE.getGroBot(((long)mac.hashCode()));
	
		//update info
		fromDB.setWaterTimeRemaining(waterTimeRemaining);
		fromDB.setLightTimeRemaining(lightTimeRemaining);
		fromDB.setLightOn(lightOn);
		fromDB.setWaterOn(waterOn);
		
		UserDAO.INSTANCE.addGroBot(fromDB);//save updates
		
//get current schedule and update information for the grobot to read
		Schedule current = fromDB.getCurrentSchedule();
		HttpSession session = req.getSession();
		if(current==null){
			//TODO anything?
			
		}else{
			Long id = (long) Math.abs(mac.hashCode());//TODO remove
			String bid = id.toString();//TODO remove
			//set attributes for GroBot to update microcontroller
			session.setAttribute("mac", mac);//TODO remove
			session.setAttribute("bot", bid);//TODO remove
			session.setAttribute("lightOn", current.getLightOn());
			session.setAttribute("lightOff", current.getLightOff());
			session.setAttribute("lightPins", current.getLightPins());
			session.setAttribute("waterDur", current.getWaterDuration());
			session.setAttribute("waterPer", current.getWaterPeriod());
			session.setAttribute("aux", current.getAux());
			session.setAttribute("air", current.getAir());
		}
		
		
		String encodedURL = resp.encodeRedirectURL("/data.jsp");
		resp.sendRedirect(encodedURL);	
	}
}
