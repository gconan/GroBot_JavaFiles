package groBot.servlet;

import groBot.dao.UserDAO;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Deletes a user's accoutn and associated DataStore members.
 * @author conangammel
 *
 */
public class DeleteAccountServlet extends SecureServlet {

	/**
	 * Deletes the cookie from the user's browser and the user's GroBots, Schedules, and the user from the DataStore.
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		super.doPost(req, resp);
		if(email==null)return;
		UserDAO.INSTANCE.deleteAccount(email);
		Cookie[] cookies = req.getCookies();
		if(cookies != null){
        	for(Cookie cookie : cookies){
        		if(cookie.getName().equals("email")){
        			cookie.setValue(null);
        		}
        		cookie.setMaxAge(0);
        		resp.addCookie(cookie);
        	}
        }
		resp.sendRedirect("index.html");
	}
}
