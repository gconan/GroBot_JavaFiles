package groBot.servlet.test;

import static org.junit.Assert.*;
import groBot.entity.User;
import groBot.servlet.SecureServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.jmock.Mockery;
import org.jmock.Expectations;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.gwt.user.client.Cookies;

public class SecureServletTest extends SecureServlet{

	Mockery context = new Mockery();
	final HttpServletRequest req = context.mock(HttpServletRequest.class);
	final HttpServletResponse resp = context.mock(HttpServletResponse.class);
	final HttpSession ses = context.mock(HttpSession.class);
	
	
	@Test
	public void testSecureServletNullEmail() throws IOException{
		context.checking(new Expectations(){{
			oneOf(req).getSession();
				will(returnValue(ses));
			oneOf(ses).getAttribute("email");
				will(returnValue(null));
			oneOf(resp).sendRedirect("index.html");
		}});
		
		
		try {
			super.doPost(req,resp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSecureServletValidEmail() throws IOException{
		final Cookie cook = new Cookie("email", "test@utexas.edu");
		final Cookie[] cookArray = {cook};
		context.checking(new Expectations(){{
			oneOf(req).getSession();
				will(returnValue(ses));
				
			exactly(2).of(ses).getAttribute("email");
				will(returnValue("test@utexas.edu"));
				
			oneOf(req).getCookies();
				will(returnValue(cookArray));
		}});
		
		
		try {
			super.doPost(req,resp);
			assertTrue(super.email.equals("test@utexas.edu"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	@Test
//	public void test() {
//		User test = new User("", "");
//		ofy().save().
//	}
}
