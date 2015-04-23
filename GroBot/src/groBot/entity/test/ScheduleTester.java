package groBot.entity.test;

import static org.junit.Assert.*;
import groBot.dao.UserDAO;
import groBot.entity.Lights;
import groBot.entity.Schedule;
import groBot.entity.Water;
import static org.easymock.EasyMock.createStrictMock;

import org.easymock.EasyMock;
import org.junit.Test;

public class ScheduleTester {
	
	//MOCKS
		//final UserDAO dao = createStrictMock(UserDAO.class);

	@Test
	public void testDefaultContstructor() {
		Schedule test = new Schedule();	
		assertNotNull(test);
	}
	
	@Test
	public void testConstructor1(){
		Schedule test = new Schedule("testSchedule");
		assertTrue(test.getName().equals("testSchedule"));
	}
	
	@Test
	public void testConstructor2(){
		Schedule test = new Schedule("testSchedule");
		String testVal = test.getValue().toUpperCase();
		assertTrue(test.getValue().equals(testVal));
	}
	
	@Test
	public void testConstructor3(){
		Schedule test = new Schedule("testSchedule");
		assertTrue(test.getPopularity()==0);
	}
	
	@Test
	public void testConstructor4(){
		Schedule test = new Schedule("testSchedule");
		assertTrue(test.getAux().equals("false"));
	}
	
	@Test
	public void testConstructor5(){
		Schedule test = new Schedule("testSchedule");
		assertTrue(test.getAir().equals("false"));
	}
	
	@Test
	public void testConstructor6(){
		Schedule test = new Schedule("testSchedule");
		long testHash = ("testSchedule").hashCode()*31;
		assertTrue(testHash==test.getId());
	}
	
//*****************************************WATER TESTS*************************************
	@Test
	public void testWaterConstructor(){
		Schedule test = new Schedule("testSchedule");
		test.newWaterSchedule(900, 1000);
		assertTrue(test.getWaterId()!=0);
	}
	
	@Test(expected=NullPointerException.class)
	public void testWaterConstructor2(){
		Schedule test = new Schedule("testSchedule");
		assertNull(test.getWaterId());
	}
	
	@Test
	public void testWaterSet(){
		Water water = new Water(30,300);
		long waterID = water.getId();
		Schedule test = new Schedule("testSchedule");
		test.setWater(water);
		long scheduleWaterID = test.getWaterId();
		assertTrue(waterID==scheduleWaterID);
	}
	
	@Test
	public void testWaterID(){
		Water uselessWater = new Water(30,300);
		Schedule test = new Schedule("testSchedule");
		uselessWater.setSchedule(test.getId());
		uselessWater.setID();
		Long waterID = uselessWater.getId();
		
		test.setWater(uselessWater);
		test.setWaterID();
		long schedulesWaterID = test.getWaterId();
		
		assertTrue(waterID==schedulesWaterID);
	}
	
	@Test
	public void testSchedulesDataTransferofWaterInformation1(){
		Schedule test = new Schedule("testSchedule");
		test.newWaterSchedule(900, 1000);
		assertTrue(test.getWaterDuration().equals("900"));
	}
	
	@Test
	public void testSchedulesDataTransferofWaterInformation2(){
		Schedule test = new Schedule("testSchedule");
		test.newWaterSchedule(900, 1000);
		assertTrue(test.getWaterPeriod().equals("1000"));
	}
	
	@Test
	public void testSetWaterInfo1(){
		Schedule test = new Schedule("testSchedule");
		test.newWaterSchedule(30, 400);
		
		test.setWaterSchedule(60, 800);
		
		assertTrue(test.getWaterDuration().equals("60"));
	}
	
	@Test
	public void testSetWaterInfo2(){
		Schedule test = new Schedule("testSchedule");
		test.newWaterSchedule(30, 400);
		
		test.setWaterSchedule(60, 800);
		
		assertTrue(test.getWaterPeriod().equals("800"));
	}
	
	
//**************************************LIGHT TESTS*************************************
	@Test
	public void testLightConstructor1(){
		Schedule test = new Schedule("testSchedule");
		test.newLights(100, 200, true, true);
		assertTrue(test.getLightId()!=0);
	}
	
	@Test(expected=NullPointerException.class)
	public void testLightConstructor2(){
		Schedule test = new Schedule("testSchedule");
		assertNull(test.getLightId());
	}
	
	@Test
	public void testLightSet(){
		Lights light = new Lights(300,600, true, true);
		long lightID = light.getId();
		Schedule test = new Schedule("testSchedule");
		test.setLights(light);
		long scheduleLightID = test.getLightId();
		assertTrue(lightID==scheduleLightID);
	}
	
	@Test
	public void testLightID(){
		Lights uselessLights = new Lights(30,300, true, true);
		Schedule test = new Schedule("testSchedule");
		uselessLights.setSchedule(test.getId());
		uselessLights.setID();
		Long lightID = uselessLights.getId();
		
		test.setLights(uselessLights);
		test.setLightID();
		long schedulesLightID = test.getLightId();
		
		assertTrue(lightID==schedulesLightID);
	}
	
	@Test
	public void testSchedulesDataTransferofLightInformation1(){
		Schedule test = new Schedule("testSchedule");
		test.newLights(900, 1000, true, true);
		assertTrue(test.getLightOn().equals("900"));
	}
	
	@Test
	public void testSchedulesDataTransferofLightInformation2(){
		Schedule test = new Schedule("testSchedule");
		test.newLights(900, 1000, true, true);
		assertTrue(test.getLightOff().equals("1000"));
	}
	
	@Test
	public void testSchedulesDataTransferofLightInformation3(){
		Schedule test = new Schedule("testSchedule");
		test.newLights(900, 1000, true, true);
		assertTrue(test.getLightPins().equals("3"));
	}
	
	@Test
	public void testSetLightInfo1(){
		Schedule test = new Schedule("testSchedule");
		test.newLights(30, 400, true, true);
		
		test.setLights(60, 800, false, false);
		
		assertTrue(test.getLightOn().equals("60"));
	}
	
	@Test
	public void testSetLightInfo2(){
		Schedule test = new Schedule("testSchedule");
		test.newLights(30, 400, true, true);
		
		test.setLights(60, 800, false, false);
		
		assertTrue(test.getLightOff().equals("800"));
	}
	
	@Test
	public void testSetLightInfo3(){
		Schedule test = new Schedule("testSchedule");
		test.newLights(30, 400, true, true);
		
		test.setLights(60, 800, false, false);
		
		assertTrue(test.getLightPins().equals("0"));
	}
	
	@Test
	public void testSetLightInfo4(){
		Schedule test = new Schedule("testSchedule");
		test.newLights(30, 400, true, true);
		
		test.setLights(60, 800, true, false);
		System.out.println(test.getLightPins());
		assertTrue(test.getLightPins().equals("2"));
	}
	
//***************************************GETTERS AND SETTERS*******************************
	@Test
	public void testSetName(){
		Schedule test = new Schedule("testSchedule");
		test.setName("Bevo");
		assertTrue(test.getName().equals("Bevo"));
	}
	
	@Test
	public void testSetAux(){
		Schedule test = new Schedule("testSchedule");
		assertFalse(Boolean.parseBoolean(test.getAux()));
		test.setAux(true);
		assertTrue(Boolean.parseBoolean(test.getAux()));
	}
	
	@Test
	public void testSetAir(){
		Schedule test = new Schedule("testSchedule");
		assertFalse(Boolean.parseBoolean(test.getAir()));
		test.setAir(true);
		assertTrue(Boolean.parseBoolean(test.getAir()));
	}
	
	@Test
	public void testPopularity1(){
		Schedule test = new Schedule("testSchedule");
		assertTrue(test.getPopularity()==0);
	}
	
	@Test
	public void testPopularity2(){
		Schedule test = new Schedule("testSchedule");
		test.upPopularity();
		assertTrue(test.getPopularity()==1);
	}
	
	
//	@Test
//	public void testUSERDAOGet(){
//		EasyMock.expect(dao.INSTANCE.getSchedule((long)0)).andReturn(new Schedule("testSchedule"));
//		Schedule test = Schedule.get((long)0);
//		assertTrue(test.getName().equals("testSchedule"));
//	}
	
	@Test
	public void testSetID(){
		Schedule test = new Schedule("testSchedule");
		test.setID((long)0);
		assertTrue(test.getId()==0);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
