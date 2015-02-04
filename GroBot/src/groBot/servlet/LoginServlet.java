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
 * 
 * @author namaz
 *
 */
public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
        String email = request.getParameter("username").toLowerCase();
        String password = request.getParameter("password");
        Integer p = password.hashCode();
		String pass = p.toString();
        
        User user = new User(pass,email.toLowerCase());
        
        User fromDB = UserDAO.INSTANCE.getUserByEmail(email);

        if(fromDB != null && user.isSameUser(fromDB)){
        	if(!fromDB.getStatus()) {
        		RequestDispatcher rd = getServletContext().getRequestDispatcher("/errorverifyregistration.html");
                rd.include(request, response);
        	} else {
        		HttpSession session = request.getSession();
        		session.setAttribute("email", fromDB.getEmail());
        		session.setMaxInactiveInterval(30*60);
        		Cookie loginCookie = new Cookie("email",fromDB.getEmail());
        		response.addCookie(loginCookie);
        		String encodedURL = response.encodeRedirectURL("/buy.jsp");
        		response.sendRedirect(encodedURL);
        	}
        }else{
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/indexerror.html");
            rd.include(request, response);
        }
    }
}
