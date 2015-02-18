package groBot.servlet;

import java.io.IOException;

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
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
        
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
        	for(Cookie cookie : cookies){
        		if(cookie.getName().equals("GroBotEmail")){
        			cookie.setValue(null);
        		}
        		cookie.setMaxAge(0);
        		response.addCookie(cookie);
        	}
        }
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        response.sendRedirect("index.html");	//TODO update html
    }
 
}
