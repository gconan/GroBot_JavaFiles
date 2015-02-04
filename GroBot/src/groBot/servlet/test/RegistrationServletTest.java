package groBot.servlet.test;

import static org.junit.Assert.*;
import groBot.servlet.RegistrationServlet;
import groBot.servlet.SecureServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.jmock.Mockery;
import org.jmock.Expectations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.urlfetch.HTTPRequest;

public class RegistrationServletTest extends RegistrationServlet{

	Mockery context = new Mockery();
	final HttpServletRequest req = context.mock(HttpServletRequest.class);
	final HttpServletResponse resp = context.mock(HttpServletResponse.class);
	final HttpSession ses = context.mock(HttpSession.class);
	
	
	public WebDriver driver;
    public String baseUrl = "http://localhost:8888/";
 
    /**
     * @author Michael
     */
    @Before
    public void init() {
    	driver = new FirefoxDriver();
    	driver.get(baseUrl);
    }
    
    /**
     * @author Michael
     */
    @After
    public void endSession() {
    	driver.close();
    	driver.quit();
    }
	
    /**
     * @author Michael
     */
    /*@Test
    public void test1(){
    	String expectedTitle = "Longhorn Bazaar";
    	String actualTitle = "";
    	actualTitle = driver.getTitle();
    	assertEquals(actualTitle, expectedTitle);
    }*/
   
    /**
     * @author Michael
     */
	/*@Test
	public void testCorrectRegistration(){
		driver.findElement(By.linkText("Need to Register?")).click();
		driver.findElement(By.name("email")).sendKeys("test1@utexas.edu");
		driver.findElement(By.name("password")).sendKeys("q");
		driver.findElement(By.name("verifyPass")).sendKeys("q");
		driver.findElement(By.name("submit")).click();
		String expectedTitle = "Longhorn Bazaar Registered";
    	String actualTitle = "";
    	actualTitle = driver.getTitle();
    	assertEquals(actualTitle, expectedTitle);
	}
    */
    
    /**
     * @author Michael
     */
    /*
	@Test
	public void testPasswordsMatch(){
		driver.findElement(By.linkText("Need to Register?")).click();
		driver.findElement(By.name("email")).sendKeys("test2@utexas.edu");
		driver.findElement(By.name("password")).sendKeys("password");
		driver.findElement(By.name("verifyPass")).sendKeys("q");
		driver.findElement(By.name("submit")).click();
		String expectedTitle = "Longhorn Bazaar Registration";
    	String actualTitle = "";
    	actualTitle = driver.getTitle();
    	assertEquals(actualTitle, expectedTitle);
	}*/
	
	/**
     * @author Michael
     */
    /*
	@Test
	public void testEmailDomain(){
		driver.findElement(By.linkText("Need to Register?")).click();
		driver.findElement(By.name("email")).sendKeys("test3@gmail.com");
		driver.findElement(By.name("password")).sendKeys("password");
		driver.findElement(By.name("verifyPass")).sendKeys("password");
		driver.findElement(By.name("submit")).click();
		String expectedTitle = "Longhorn Bazaar Registration";
    	String actualTitle = "";
    	actualTitle = driver.getTitle();
    	assertEquals(actualTitle, expectedTitle);
	}*/
	
    /**
     * @author Michael
     */
	@Test
	public void testEmailInUse(){
		driver.findElement(By.linkText("Need to Register?")).click();
		driver.findElement(By.name("email")).sendKeys("test4@gmail.com");
		driver.findElement(By.name("password")).sendKeys("password");
		driver.findElement(By.name("verifyPass")).sendKeys("password");
		driver.findElement(By.name("submit")).click();
		driver.navigate().to("http://localhost:8888/index.html");
		driver.navigate().refresh();
		driver.findElement(By.linkText("Need to Register?")).click();
		driver.findElement(By.name("email")).sendKeys("test4@gmail.com");
		driver.findElement(By.name("password")).sendKeys("password");
		driver.findElement(By.name("verifyPass")).sendKeys("password");
		driver.findElement(By.name("submit")).click();
		String expectedTitle = "Longhorn Bazaar Email In Use";
    	String actualTitle = "";
    	actualTitle = driver.getTitle();
    	assertEquals(actualTitle, expectedTitle);
	}


}
