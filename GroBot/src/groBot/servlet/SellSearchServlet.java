package groBot.servlet;

import static groBot.services.OfyService.ofy;
import groBot.entity.Item;
import groBot.entity.Meeting;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.googlecode.objectify.cmd.Query;

public class SellSearchServlet extends SecureServlet {
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
		System.out.println("search= "+search);//TODO remove
		Query<Item> allItems = ofy().load().type(Item.class).filter("owner", email);
		ArrayList<Item> searchResult = new ArrayList<Item>();
		
		//populate arrayList with Meetings matching "search" keywords with an Meeting's keywords
		for(Item item: allItems){
			System.out.println(""+item.getKeywords()==null + "      "+item.getKeywords().size());
			for(String keyword: item.getKeywords()){
				if(search.contains(keyword) || search.contains(item.getName())){
					searchResult.add(item);
					break;
				}
			}
		}
		
		//"sending" arrayList to jsp
		HttpSession ses = req.getSession(true);
		ses.setAttribute("items", searchResult);
		String encodedURL = resp.encodeRedirectURL("sellSearch.jsp");
        resp.sendRedirect(encodedURL);
	}

}

