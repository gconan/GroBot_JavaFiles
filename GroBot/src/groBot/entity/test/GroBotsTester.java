package groBot.entity.test;

import static org.junit.Assert.*;
import groBot.entity.GroBots;
import groBot.entity.Schedule;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;

public class GroBotsTester {
	
	private GroBots test;
	private final String testName = "testName";
	private final String testAdd = "1234567890";
	private final String testEmail = "test@GroBot.com";
	
	//MOCKS
	private Mockery context = new JUnit4Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
    }};
	final Schedule sched = context.mock(Schedule.class);
	
	

//********************************DEFAULT CONSTRUCTOR TESTS****************************************
	@Test
	public void defaultconstructorTest1() {
		this.defaultInitialization();
		assertTrue(test!=null);
	}
	
	@Test
	public void defaultconstructorTest2() {
		this.defaultInitialization();
		assertTrue(test.getName().equals("****DefaultBot****"));
	}
	
	@Test
	public void defaultconstructorTest() {
		this.defaultInitialization();
		assertTrue(test.getCurrentSchedule()==null);
	}
	
//************************************CONSTRUCTOR TESTS*******************************************
	
	@Test
	public void constructorTest1(){
		this.simpleInitialization();
		assertTrue(test!=null);
	}
	
	@Test
	public void constructorTest2(){
		this.simpleInitialization();
		assertTrue(test.getName().equals(this.testName));
	}
	
	@Test
	public void constructorTest3(){
		this.simpleInitialization();
		assertTrue(test.getId()==this.testAdd.hashCode());
	}
	
	@Test
	public void constructorTest4(){
		this.simpleInitialization();
		assertTrue(test.getOwner().equals(this.testEmail));
	}
	
	@Test
	public void constructorTest5(){
		this.simpleInitialization();
		assertTrue(test.getCurrentSchedule() != null);
	}
//********************************RUN SCHEDULE TESTS****************************************

	@Test
	public void runScheduleTest1(){
		this.simpleInitialization();
		
		context.checking(new Expectations() {{
	        oneOf (sched).getAux(); 
	        	will(returnValue("true"));
	        oneOf (sched).getAir();
	        	will(returnValue("true"));
	    }});
		
		test.runSchedule(sched);
		
		assertTrue(test.getAirOn().equals("true"));
		assertTrue(test.getAuxOn().equals("true"));
	}
	
	@Test
	public void runScheduleTest2(){
		this.simpleInitialization();
		
		context.checking(new Expectations() {{
	        oneOf (sched).getAux(); 
	        	will(returnValue("true"));
	        oneOf (sched).getAir();
	        	will(returnValue("false"));
	    }});
		
		test.runSchedule(sched);
		
		assertTrue(test.getAirOn().equals("false"));
		assertTrue(test.getAuxOn().equals("true"));
	}
	
	@Test
	public void runScheduleTest3(){
		this.simpleInitialization();
		
		context.checking(new Expectations() {{
	        oneOf (sched).getAux(); 
	        	will(returnValue("false"));
	        oneOf (sched).getAir();
	        	will(returnValue("true"));
	    }});
		
		test.runSchedule(sched);
		
		assertTrue(test.getAirOn().equals("true"));
		assertTrue(test.getAuxOn().equals("false"));
	}
	
	@Test
	public void runScheduleTest4(){
		this.simpleInitialization();
		
		context.checking(new Expectations() {{
	        oneOf (sched).getAux(); 
	        	will(returnValue("false"));
	        oneOf (sched).getAir();
	        	will(returnValue("false"));
	    }});
		
		test.runSchedule(sched);
		
		assertTrue(test.getAirOn().equals("false"));
		assertTrue(test.getAuxOn().equals("false"));
	}
	
//*************************************GETTER / SETTER TESTS*******************************
	
	
	
	

	@Test
	public void testWaterOn(){
		this.simpleInitialization();
		assertFalse(test.isWaterOn());
	}
	
	@Test
	public void testLightsOn(){
		this.simpleInitialization();
		assertFalse(test.areLightsOn());
	}
	
	@Test
	public void testAirOn(){
		this.simpleInitialization();
		assertFalse(test.isAirOn());
	}
	
	@Test
	public void testAuxOn(){
		this.simpleInitialization();
		assertFalse(test.isAuxOn());
	}
	
	@Test
	public void testSetLightTimeRemaining(){
		this.simpleInitialization();
		test.setLightTimeRemaining("400");
		assertTrue(test.getLightTimeRemaining().equals("400"));
	}
	
	@Test
	public void testSetWaterTimeRemaining(){
		this.simpleInitialization();
		test.setWaterTimeRemaining("400");
		assertTrue(test.getWaterTimeRemaining().equals("400"));
	}
	
	@Test
	public void testSetAirOn(){
		this.simpleInitialization();
		test.setAuxOn("true");
		assertTrue(test.getAuxOn().equals("true"));
	}
	
	@Test
	public void testSetAuxOn(){
		this.simpleInitialization();
		test.setAirOn("true");
		assertTrue(test.getAirOn().equals("true"));
	}
	
	@Test
	public void testSetLightOn(){
		this.simpleInitialization();
		test.setLightOn("true");
		assertTrue(test.getLightOn().equals("true"));
	}
	
	@Test
	public void testSetLightOn_withBadString(){
		this.simpleInitialization();
		test.setLightOn("600");
		assertFalse(test.getLightOn().equals("600"));
	}
	
	@Test
	public void testSetWaterOn(){
		this.simpleInitialization();
		test.setWaterOn("true");
		assertTrue(test.getWaterOn().equals("true"));
	}

	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	public void defaultInitialization(){
		this.test = new GroBots();
	}
	
	public void simpleInitialization(){
		this.test = new GroBots(this.testName, this.testAdd, this.testEmail);
	}
	

}
