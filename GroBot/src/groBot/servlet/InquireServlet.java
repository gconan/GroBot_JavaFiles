package groBot.servlet;

import groBot.email.Email;
import groBot.entity.Item;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static groBot.services.OfyService.ofy;

/**
 * called when a user inquires about an item
 * @author conangammel
 *
 */
public class InquireServlet extends SecureServlet{
	
	/**
	 * @author conangammel
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		HttpSession session = req.getSession();
		String inquirersEmail = (String) session.getAttribute("email");
		if(inquirersEmail == null){
			//TODO add something to tell the user something went wrong
			String encodedURL = resp.encodeRedirectURL("index.html");
		    resp.sendRedirect(encodedURL);
		}
		String id = req.getParameter("itemID");
		Long numId = Long.parseLong(id);
		Item item = ofy().load().type(Item.class).id(numId).now();
		String itemOwner = item.getOwner();
		String inquiryMessage = "I am interested in your " + item.getName() + ". Please contact me at the given email soon!";
		Email inquireMail = new Email();
		String body = "Hi "+itemOwner.substring(0, itemOwner.length()-11)+", you have a new inquiry for your item: "+item.getName()+
				"!\n"+"Here is a message from "+inquirersEmail+" :\n"+
				"\t\""+inquiryMessage+"\"\n"+
				"\nResponding to the inquiry increases your chances at selling your item";
		try{
			inquireMail.send(itemOwner, body, "You have a new inquiry!");
		}catch(Exception e){
			String encodedURL = resp.encodeRedirectURL("/error.html");
			resp.sendRedirect(encodedURL);
		}
		String encodedURL = resp.encodeRedirectURL("/buy.jsp");
		resp.sendRedirect(encodedURL);
	}

}
