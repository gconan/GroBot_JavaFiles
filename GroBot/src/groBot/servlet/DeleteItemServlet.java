package groBot.servlet;

import groBot.entity.Item;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static groBot.services.OfyService.ofy;

/**
 * deletes an item from the datastore
 * @author conangammel
 *
 */
public class DeleteItemServlet extends SecureServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		super.doPost(req, resp);
		if(email == null) return;
		String id = req.getParameter("itemID");
		Long numId = Long.parseLong(id);
		ofy().delete().type(Item.class).id(numId);
		String encodedURL = resp.encodeRedirectURL("/sell.jsp");
		resp.sendRedirect(encodedURL);
	}

}
