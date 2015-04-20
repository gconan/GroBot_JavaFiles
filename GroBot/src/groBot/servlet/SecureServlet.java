package groBot.servlet;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Verifies that the user accessing GroBot pages is logged in. 
 * Super class to all servlets; that way, anyone who is not logged in cannot manually call a servlet and 
 * alter data in the DataStore.
 * @author conangammel
 *
 */
public abstract class SecureServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	/**Email address contained in the cookie.*/
	protected String email = null;

	/**
	 * Verifies the GroBotEmail cookie. Saves the user's email address to the class member "email" for servlets to utilize.
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		if(session.getAttribute("GroBotEmail") == null){
			//TODO add something to tell the user something went wrong
		    resp.sendRedirect("/index.html");
		}
		else {
			email = (String) session.getAttribute("GroBotEmail");

			Cookie[] cookies = req.getCookies();
			if(cookies != null){
				for(Cookie cookie : cookies){
			    	if(cookie.getName().equals("GroBotEmailCookie")){
			    		if(email.equals(cookie.getValue())){
			    			return;
			    		}
			    	}
				}
			}else{
				String encodedURL = resp.encodeRedirectURL("index.html");
				resp.sendRedirect(encodedURL);
			}
		}
	}
	
	public String getEmail(HttpServletRequest req, HttpServletResponse resp)throws IOException{
		HttpSession session = req.getSession();
		Object email = session.getAttribute("GroBotEmail");
		
		if(email == null){
			resp.sendRedirect("/index.html");
		}
		
		return (String)email;
		
		

	}
}
