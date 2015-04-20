package groBot.servlet;

import groBot.dao.UserDAO;
import groBot.entity.User;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Logs a user into the website. Sets cookies, so that they may exit the website and return without logging back in.
 * @author conangammel
 *
 */
public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Verifies a user's email address and password, sets cookie information, current GroBot name, then redirects the user to
	 * the welcome page.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
        String email = request.getParameter("username").toLowerCase();
        String password = request.getParameter("password");
        Integer p = password.hashCode();
		String pass = p.toString();
        
        User fromDB = UserDAO.INSTANCE.getUserByEmail(email);

        if(fromDB != null && fromDB.getPassword().equals(pass)){ //TODO check that both are supposed to be the hash codes
        	if(!fromDB.getStatus()) {
        		RequestDispatcher rd = getServletContext().getRequestDispatcher("/errorverifyregistration.html");
                rd.include(request, response);
        	} else {
        		HttpSession session = request.getSession();
        		session.setAttribute("GroBotEmail", fromDB.getEmail());
        		String first = fromDB.getFirstName();
        		first = first.toUpperCase().charAt(0)+first.substring(1);
        		session.setAttribute("name", first);
        		String last = fromDB.getLastName();
        		last = last.toUpperCase().charAt(0)+last.substring(1);
        		session.setAttribute("last", last);
        		session.setAttribute("botName", fromDB.getBotName());
        		session.setMaxInactiveInterval(30*60);
        		Cookie loginCookie = new Cookie("GroBotEmailCookie",fromDB.getEmail());
        		response.addCookie(loginCookie);
        		String encodedURL = response.encodeRedirectURL("welcome.jsp");
        		response.sendRedirect(encodedURL);
        	}
        }else{
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/indexerror.html");	//TODO update html
            rd.include(request, response);
        }
    }
}
