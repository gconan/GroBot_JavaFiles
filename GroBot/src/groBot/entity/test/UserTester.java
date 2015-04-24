package groBot.entity.test;

import static org.junit.Assert.*;
import groBot.dao.RealDAO;
import groBot.entity.GroBots;
import groBot.entity.Schedule;
import groBot.entity.User;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserTester {

	private String firstName = "test";
	private String lastName = "tester";
	private String password = "password";
	private String email = "test@test.com";
	private String accessCode;
	private Schedule cayenne;
	private Schedule tomatoes;
	private Schedule habanero;
	
	//MOCKS
		private Mockery context = new JUnit4Mockery() {{
	        setImposteriser(ClassImposteriser.INSTANCE);
	    }};
		final RealDAO dao = context.mock(RealDAO.class);
	
	
//	@BeforeClass
//	public void setUpSchedules(){
//		cayenne = new Schedule("Cayenne Peppers");
//		tomatoes = new Schedule("Tomatoes");
//		habanero = new Schedule("Habanero Peppers");
		
//		cayenne.newLights(600, 1800, true, true);
//		cayenne.newWaterSchedule(15, 6);
//		cayenne.setAir(true);
//		cayenne.setAux(false);
//		cayenne.setWaterID();
//		cayenne.setLightID();
//		cayenne.setID((long)(this.email+"Cayenne Peppers").hashCode());
//		
//		tomatoes.newLights(700, 1600, true, false);
//		tomatoes.newWaterSchedule(15, 1);
//		tomatoes.setAir(true);
//		tomatoes.setAux(false);
//		tomatoes.setWaterID();
//		tomatoes.setLightID();
//		tomatoes.setID((long)(this.email+"Tomatoes").hashCode());
//		
//		habanero.newLights(600, 1800, true, true);
//		habanero.newWaterSchedule(15, 6);
//		habanero.setAir(true);
//		habanero.setAux(false);
//		habanero.setWaterID();
//		habanero.setLightID();
//		habanero.setID((long)(this.email+"Habanero Peppers").hashCode());
//	}
//*************************************CONSTRUCTOR TESTS************************
//	@Test
//	public void constructorTest1() {
//		cayenne = new Schedule("Cayenne Peppers");
//		tomatoes = new Schedule("Tomatoes");
//		habanero = new Schedule("Habanero Peppers");
//		
//		cayenne.newLights(600, 1800, true, true);
//		cayenne.newWaterSchedule(15, 6);
//		cayenne.setAir(true);
//		cayenne.setAux(false);
//		cayenne.setWaterID();
//		cayenne.setLightID();
//		cayenne.setID((long)(this.email+"Cayenne Peppers").hashCode());
//		
//		tomatoes.newLights(700, 1600, true, false);
//		tomatoes.newWaterSchedule(15, 1);
//		tomatoes.setAir(true);
//		tomatoes.setAux(false);
//		tomatoes.setWaterID();
//		tomatoes.setLightID();
//		tomatoes.setID((long)(this.email+"Tomatoes").hashCode());
//		
//		habanero.newLights(600, 1800, true, true);
//		habanero.newWaterSchedule(15, 6);
//		habanero.setAir(true);
//		habanero.setAux(false);
//		habanero.setWaterID();
//		habanero.setLightID();
//		habanero.setID((long)(this.email+"Habanero Peppers").hashCode());
//		
//		context.checking(new Expectations() {{
//	        oneOf (dao).addSchedule(cayenne);
//	        	will(returnValue(void.class));
//	        oneOf (dao).addSchedule(tomatoes);
//	        	will(returnValue(void.class));
//	        oneOf (dao).addSchedule(habanero);
//	        	will(returnValue(void.class));
//	    }});
//		User test = new User(firstName, lastName, password, email);
//		assertTrue(test.getGroBot().getCurrentSchedule().equals(cayenne));
//	}
		
		
		@Test
		public void test1(){
			User test = new User();
			
			assertTrue(test!=null);
		}
		
		@Test
		public void test2(){
			User test = new User();
			test.setPassword(password);
			assertTrue(test.getPassword().equals(password));
		}
		
		@Test
		public void test3(){
			User test = new User();
			test.setPassword(password);
			assertTrue(test.getAllBots() != null);
		}
		
		@Test
		public void test4(){
			User test = new User();
			test.setPassword(password);
			test.addGroBot(new GroBots("testBot","100", email));
			assertTrue(test.getCurrentBotName().equals("testBot"));
		}
		
		@Test
		public void test5(){
			User test = new User();
			long hash = Math.abs(email.hashCode() * (long) Math.pow(31,5));	
			StringBuilder code = new StringBuilder();	                         
			while (hash > 0) {
				code.append((char)('a' + hash%27));
				hash/= 27;
			}
			this.accessCode = code.toString();
			assertTrue(test.getFirstName().equals(firstName));
			assertTrue(test.getLastName().equals(lastName));
			assertTrue(test.getEmail().equals(email));
			assertTrue(test.getAccess_code().equals(accessCode));
			assertTrue(test.getAllBots() !=null);
			assertTrue(test.getSchedules() !=null);
			assertTrue(test.getCurrentBotName().equals((new GroBots()).getName()));
			assertTrue(test.getGroBot().getName().equals((new GroBots()).getName()));
			assertFalse(test.getStatus());
			test.activate();
			assertTrue(test.getStatus());
			test.setCurrentBot(new GroBots("name", "MAC", email));
			assertTrue(test.getBotName().equals("name"));
			assertTrue(test.resetPassword() != null);
		}
		
		@Test
		public void testequivalence(){
			User test1 = new User();
			User test2 = new User();
			assertTrue(test1.isSameUser(test2));
			
			test1.setPassword("newPassword");
			assertFalse(test1.isSameUser(test2));
		}
		
		@Test
		public void testaddSched(){
			User test = new User();
			this.cayenne = new Schedule("cayenne");
			this.habanero = new Schedule("habanero");
			test.addCustomSchedule(cayenne);
			assertTrue(test.getSchedules().size()==1);
		}

}
