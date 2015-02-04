package groBot.servlet;

import groBot.dao.UserDAO;
import groBot.entity.User;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * usubscribe user
 * @author conangammel
 *
 */
public class UnsubscribeServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		System.out.println("hi");
		String sub = req.getParameter("emails");
		HttpSession session = req.getSession();
		String email = (String) session.getAttribute("email");
		User fromDB = UserDAO.INSTANCE.getUserByEmail(email);
		boolean subbed = fromDB.getSubscribed();
		if(subbed && sub.equals("yes")){
			System.out.println("already subbed");
			String encodedURL = resp.encodeRedirectURL("profile.jsp");
			resp.sendRedirect(encodedURL);
		} else if (!subbed && sub.equals("no")){
			System.out.println("already unsubbed");
			String encodedURL = resp.encodeRedirectURL("goodbye.html");
			resp.sendRedirect(encodedURL);
		} else if(!subbed && sub.equals("yes")){
			boolean success = UserDAO.INSTANCE.subscribeUser(email);
			if(success){
				System.out.println("success");
				String encodedURL = resp.encodeRedirectURL("profile.jsp");
				resp.sendRedirect(encodedURL);
			}else{
				String encodedURL = resp.encodeRedirectURL("error.html");
				resp.sendRedirect(encodedURL);
				System.out.println("no");
			}
		} else if(subbed && sub.equals("no")){
			boolean success = UserDAO.INSTANCE.unsubscribeUser(email);
			if(success){
				System.out.println("success");
				String encodedURL = resp.encodeRedirectURL("goodbye.html");
				resp.sendRedirect(encodedURL);
			}else{
				String encodedURL = resp.encodeRedirectURL("error.html");
				resp.sendRedirect(encodedURL);
				System.out.println("no");
			}
		}
		
	}

}
