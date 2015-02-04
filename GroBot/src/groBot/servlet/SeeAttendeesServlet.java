package groBot.servlet;

import groBot.dao.UserDAO;
import groBot.entity.Meeting;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * @author conangammel
 * get list of people attending the meeting and redirect to the view page
 * that shows the list to the user who organizes the meeting
 */
public class SeeAttendeesServlet extends SecureServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		super.doPost(req, resp);
		if(email==null){
			return;
		}
		
		String idString = req.getParameter("seeWho");
		Long id = Long.parseLong(idString);
		Meeting meet = UserDAO.INSTANCE.getMeetingById(id);
		
		HttpSession ses = req.getSession(true);
		ses.setAttribute("VIPList", meet.getVIPList());
		ses.setAttribute("meeting", meet);
		String encodedURL = resp.encodeRedirectURL("viewActivity.jsp");	//perhpas remove if we combine html and jsp
        resp.sendRedirect(encodedURL);
		
	}

}
