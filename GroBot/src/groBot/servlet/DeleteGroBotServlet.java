package groBot.servlet;

import groBot.entity.GroBots;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static groBot.services.OfyService.ofy;

/**
 * deletes an item from the datastore
 * @author conangammel
 *
 */
public class DeleteGroBotServlet extends SecureServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		super.doPost(req, resp);
		if(email == null) return;
		String id = req.getParameter("itemID");	//TODO update to MAC address or whichever we use
		Long numId = Long.parseLong(id);
		ofy().delete().type(GroBots.class).id(numId);
		String encodedURL = resp.encodeRedirectURL("/profile.jsp");	//TODO update jsp
		resp.sendRedirect(encodedURL);
	}

}
