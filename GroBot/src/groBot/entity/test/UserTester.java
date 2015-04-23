package groBot.entity.test;

import static org.junit.Assert.*;

import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;
import groBot.dao.UserDAO;
import groBot.entity.Schedule;
import groBot.entity.User;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(PowerMockRunner.class)
@PrepareForTest({UserDAO.class})
public class UserTester {

	private String firstName = "test";
	private String lastName = "tester";
	private String password = "password";
	private String email = "test@test.com";
	private Schedule cayenne;
	private Schedule tomatoes;
	private Schedule habanero;
	
	
	@BeforeClass
	public void setUpSchedules(){
		cayenne = new Schedule("Cayenne Peppers");
		tomatoes = new Schedule("Tomatoes");
		habanero = new Schedule("Habanero Peppers");
		
		cayenne.newLights(600, 1800, true, true);
		cayenne.newWaterSchedule(15, 6);
		cayenne.setAir(true);
		cayenne.setAux(false);
		cayenne.setWaterID();
		cayenne.setLightID();
		cayenne.setID((long)(this.email+"Cayenne Peppers").hashCode());
		
		tomatoes.newLights(700, 1600, true, false);
		tomatoes.newWaterSchedule(15, 1);
		tomatoes.setAir(true);
		tomatoes.setAux(false);
		tomatoes.setWaterID();
		tomatoes.setLightID();
		tomatoes.setID((long)(this.email+"Tomatoes").hashCode());
		
		habanero.newLights(600, 1800, true, true);
		habanero.newWaterSchedule(15, 6);
		habanero.setAir(true);
		habanero.setAux(false);
		habanero.setWaterID();
		habanero.setLightID();
		habanero.setID((long)(this.email+"Habanero Peppers").hashCode());
	}
//*************************************CONSTRUCTOR TESTS************************
	@Test
	public void constructorTest1() {
		mockStatic(UserDAO.class);
		User test = new User(firstName, lastName, password, email);
		assertTrue(true);
		
	}

}
