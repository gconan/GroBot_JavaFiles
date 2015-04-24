package groBot.servlet.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Cookie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jmock.Mockery;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class ScheduleServletTester {
	
	Mockery context = new Mockery();
	final HttpServletRequest req = context.mock(HttpServletRequest.class);
	final HttpServletResponse resp = context.mock(HttpServletResponse.class);
	final HttpSession ses = context.mock(HttpSession.class);
	
	
	public WebDriver driver;
    public String baseUrl = "http://the-grobot.appspot.com/";

	@Test
	public void testNewSchedule() {
		WebDriver driver = new FirefoxDriver(); 
		
		driver.get(baseUrl);
		
		driver.findElement(By.id("username")).sendKeys("gconan66@gmail.com");
		driver.findElement(By.id("password")).sendKeys("lkjlkj");
		driver.findElement(By.id("submit")).click();
		

		driver.findElement(By.linkText("NEW SCHEDULE")).click();
		
		WebElement schedName = driver.findElement(By.id("scheduleName"));
		schedName.sendKeys("SeleniumtestSchedule");

		Select lon = new Select(driver.findElement(By.id("lightsOn")));
		lon.selectByValue("200");
		
		Select loff = new Select(driver.findElement(By.id("lightsOff")));
		loff.selectByValue("300");
		
		driver.findElement(By.id("set1")).click();
		driver.findElement(By.id("set2")).click();
		
		Select waterL = new Select(driver.findElement(By.id("waterLength")));
		waterL.selectByValue("15");
		
		Select waterP = new Select(driver.findElement(By.id("waterPeriod")));
		waterP.selectByValue("8");
		
		driver.findElement(By.id("AuxOff")).click();
		driver.findElement(By.id("AirOn")).click();
		
		driver.findElement(By.id("newSchedButton")).click();
		
		Select scheds = new Select(driver.findElement(By.id("scheduleDropDown")));
		List<WebElement> options = scheds.getOptions();
		ArrayList<String> namesofoptions = new ArrayList<String>();
		for(WebElement we: options){
			namesofoptions.add(we.getText());
		}
		driver.quit();
		assertTrue(namesofoptions.contains("SeleniumtestSchedule"));
		
		
	}

}
