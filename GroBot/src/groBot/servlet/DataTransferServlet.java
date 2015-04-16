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

public class DataTransferServlet extends HttpServlet {
	
public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
//Read all "current" data from GroBot
		String mac = req.getParameter("mac");
		String light = req.getParameter("light");
		String water = req.getParameter("water");
		String aux = req.getParameter("aux");
		String air = req.getParameter("air");
		String lightTime = req.getParameter("ltime");
		String waterTime = req.getParameter("wtime");

//update info into GroBot so that if the user goes to the "current status" page
//the information will be updated
		GroBots fromDB = UserDAO.INSTANCE.getGroBot(mac.hashCode());
	
		//update info
		fromDB.setWaterTimeRemaining(waterTime);
		fromDB.setWaterOn(water);
		fromDB.setLightTimeRemaining(lightTime);
		fromDB.setLightOn(light);
		fromDB.setAuxOn(aux);
		fromDB.setAirOn(air);
		
		UserDAO.INSTANCE.addGroBot(fromDB);
		
//get current schedule and update information for the grobot to read
		Schedule current = fromDB.getCurrentSchedule();
		HttpSession session = req.getSession();
		if(current==null){
			//TODO change attributes
			//set attributes
			session.setAttribute("lightOn", ""+light);
			session.setAttribute("lightOff", ""+lightTime);
			session.setAttribute("lightPins", ""+69);
			session.setAttribute("waterDur", ""+water);
			session.setAttribute("waterPer", ""+waterTime);
			session.setAttribute("aux", ""+aux);
			session.setAttribute("air", ""+air);
		}else{
			//set attributes
			session.setAttribute("lightOn", ""+current.getLightOn());
			session.setAttribute("lightOff", ""+current.getLightOff());
			session.setAttribute("lightPins", ""+current.getLightPins());
			session.setAttribute("waterDur", ""+current.getWaterDuration());
			session.setAttribute("waterPer", ""+current.getWaterPeriod());
			session.setAttribute("aux", ""+current.getAux());
			session.setAttribute("air", ""+current.getAir());
		}
		
		
		String encodedURL = resp.encodeRedirectURL("/data.jsp");
		resp.sendRedirect(encodedURL);	
	}
}
