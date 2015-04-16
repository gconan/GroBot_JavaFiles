package groBot.servlet;

import groBot.dao.UserDAO;
import groBot.entity.Schedule;
import groBot.entity.User;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NewScheduleServlet extends SecureServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		super.doPost(req, resp);
		if(email == null) return;
		

		User user = UserDAO.INSTANCE.getUserByEmail(email);
		
		//String email = super.getEmail(req, resp);
		String name = req.getParameter("scheduleName");
		if(name==null || name.equals("")){
			int s = user.getSchedules().size();
			name = "New Schedule "+s;
		}
		Schedule sched = new Schedule(name);
		
		
	//lights
		//on
		String lightsOn = req.getParameter("lightsOn");
		int lightsOnTime = Integer.parseInt(lightsOn);
		
		//off
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
		
		sched.newLights(lightsOnTime, lightsOffTime, s1, s2);
		
		//WATER
		String waterLength = req.getParameter("waterLength");
		int wlength = Integer.parseInt(waterLength);
		
		String waterPeriod = req.getParameter("waterPeriod");
		int wperiod = Integer.parseInt(waterPeriod);
		
		sched.newWaterSchedule(wlength, wperiod);
		
		
		//AUX | AIR
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
		
		sched.setAir(airb);
		sched.setAux(auxb);
		
		sched.setWaterID();
		sched.setLightID();
		
		user.addCustomSchedule(sched);
		
		UserDAO.INSTANCE.addUser(user);
		UserDAO.INSTANCE.addSchedule(sched);
		
		String encodedURL = resp.encodeRedirectURL("welcome.jsp");
		resp.sendRedirect(encodedURL);
	}

}
