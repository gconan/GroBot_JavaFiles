package groBot.servlet;

import java.io.IOException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * @author conangammel
 *
 */
public abstract class SecureServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	protected String email = null;

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		if(session.getAttribute("email") == null){
			//TODO add something to tell the user something went wrong
		    resp.sendRedirect("/index.html");
		}
		else {
			email = (String) session.getAttribute("email");

			Cookie[] cookies = req.getCookies();
			if(cookies != null){
				for(Cookie cookie : cookies){
			    	if(cookie.getName().equals("email")) email = cookie.getValue();
				}
			}
		}
	}
}
