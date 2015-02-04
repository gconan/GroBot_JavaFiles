package groBot.servlet;

import groBot.dao.UserDAO;
import groBot.entity.Meeting;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author conangammel
 *
 */
public class JoinMeetingServlet extends SecureServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		super.doPost(req, resp);
		if(email == null){
			return;
		}
		
		String idString = req.getParameter("attend");
		Long id = Long.parseLong(idString);
		Meeting meet = UserDAO.INSTANCE.getMeetingById(id);
		meet.addVIPtoList(email);
		UserDAO.INSTANCE.saveMeeting(meet);
		
		String encodedURL = resp.encodeRedirectURL("activities.jsp");
		resp.sendRedirect(encodedURL);
		
	}

}
