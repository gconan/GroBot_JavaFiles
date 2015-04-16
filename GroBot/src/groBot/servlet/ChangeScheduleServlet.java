package groBot.servlet;

import groBot.dao.UserDAO;
import groBot.entity.Schedule;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeScheduleServlet extends SecureServlet {
	
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
		Schedule sched = UserDAO.INSTANCE.getSchedule(id);
		
		
		String name = req.getParameter("scheduleName");
		if(name==null){
			name = "New Schedule "+req.getParameter("size");
		}
				
				
	//lights
				//on time
				String lightsOn = req.getParameter("lightsOn");
				int lightsOnTime = Integer.parseInt(lightsOn);
				
				//off time
				String lightsOFF = req.getParameter("lightsOff");
				int lightsOffTime = Integer.parseInt(lightsOFF);
				
				//light sets
				String set1 = req.getParameter("set1");
				String set2 = req.getParameter("set2");
				boolean s1;
				boolean s2;
				if(set1==null){
					s1=false;
				}else{
					s1=true;
				}
				
				if(set2==null){
					s2=false;
				}else{
					s2=true;
				}
				
	//AIR
				//duration
				String waterLength = req.getParameter("waterLength");
				int wlength = Integer.parseInt(waterLength);
				
				//how often duration is executed
				String waterPeriod = req.getParameter("waterPeriod");
				int wperiod = Integer.parseInt(waterPeriod);
				
	//DC ports
				String aux = req.getParameter("Aux");
				String air = req.getParameter("Air");
				boolean auxb;
				boolean airb;
				
				//aux
				if(aux.equalsIgnoreCase("On")){
					auxb=true;
				}else{
					auxb=false;
				}
				//air
				if(air.equalsIgnoreCase("On")){
					airb=true;
				}else{
					airb=false;
				}
				
				sched.setName(name);
				sched.setLights(lightsOnTime, lightsOffTime, s1, s2);
				sched.setWaterSchedule(wlength, wperiod);
				sched.setAir(airb);
				sched.setAux(auxb);

				UserDAO.INSTANCE.addSchedule(sched);
				
				String encodedURL = resp.encodeRedirectURL("/profile.jsp");
				resp.sendRedirect(encodedURL);	
	}

}
