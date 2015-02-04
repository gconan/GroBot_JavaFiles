package groBot.servlet;

import groBot.entity.Meeting;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.googlecode.objectify.cmd.Query;

import static groBot.services.OfyService.ofy;

/**
 * Call this servlet when the user clicks "Search"
 * This populates an arrayList with all Meetings from the DB that contains
 * the keywords matching the user's search string
 * @author conangammel
 */
public class MeetingSearchServlet extends SecureServlet{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * populates arrayList and redirects to JSP
	 * @author conangammel
	 * @throws IOException
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		super.doPost(req, resp);
		if(email==null)return;
		
		//load all Meetings from DB and create a result arrayList
		String search = req.getParameter("search");
		Query<Meeting> allMeetings = ofy().load().type(Meeting.class);
		ArrayList<Meeting> searchResult = new ArrayList<Meeting>();
		
		//populate arrayList with Meetings matching "search" keywords with an Meeting's keywords
		for(Meeting meet: allMeetings){
			for(String keyword: meet.getKeywords()){
				if(search.contains(keyword) || search.contains(meet.getName())){
					searchResult.add(meet);
					break;
				}
			}
		}
		
		//"sending" arrayList to jsp
		HttpSession ses = req.getSession(true);
		ses.setAttribute("meetings", searchResult);
		String encodedURL = resp.encodeRedirectURL("MeetingSearch.jsp");
        resp.sendRedirect(encodedURL);
	}

}
