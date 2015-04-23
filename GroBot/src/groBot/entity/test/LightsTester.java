package groBot.entity.test;

import static org.junit.Assert.*;
import groBot.entity.Lights;

import org.junit.Test;

public class LightsTester {

	
//********************************DEFAULT CONSTRUCTOR TESTS*********************************
	@Test(expected=NullPointerException.class)
	public void defaultConstructorTest1(){
		Lights test = new Lights();
		if(test.getId()==1){
			//something went wrong
		}
	}
	
	@Test
	public void defaultConstructorTest2(){
		Lights test = new Lights();
		assertTrue(test.getOffTime()==0);
	}
	
	@Test
	public void defaultConstructorTest3(){
		Lights test = new Lights();
		assertTrue(test.getOnTime()==0);
	}
	
	@Test
	public void defaultConstructorTest4(){
		Lights test = new Lights();
		assertTrue(test.getLightPins()==0);
	}
	
//*******************************CONSTRUCTOR TESTS*****************************************
	
	@Test
	public void constructorTest1(){
		Lights test = new Lights(600, 800, true, true);
		assertTrue(test.getOnTime()==600);
		assertTrue(test.getOffTime()==800);
		assertTrue(test.getId()==(600*800*31));
	}
	
//*****************************CONVERT_INT_TO_BOOLEAN TESTS********************************
	@Test
	public void intToBool1(){
		Lights test = new Lights(0, 0, true, true);
		assertTrue(test.getLightPins()==3);
	}
	
	@Test
	public void intToBool2(){
		Lights test = new Lights(0, 0, true, false);
		assertTrue(test.getLightPins()==2);
	}
	
	@Test
	public void intToBool3(){
		Lights test = new Lights(0, 0, false, true);
		assertTrue(test.getLightPins()==1);
	}
	
	@Test
	public void intToBool4(){
		Lights test = new Lights(0, 0, false, false);
		assertTrue(test.getLightPins()==0);
	}
	
	@Test
	public void intToBool5(){
		Lights test = new Lights();
		test.setPins(true, true);
		assertTrue(test.getLightPins()==3);
	}
	
	@Test
	public void intToBool6(){
		Lights test = new Lights();
		test.setPins(true, false);
		assertTrue(test.getLightPins()==2);
	}
	
	@Test
	public void intToBool7(){
		Lights test = new Lights();
		test.setPins(false, true);
		assertTrue(test.getLightPins()==1);
	}
	
	@Test
	public void intToBool8(){
		Lights test = new Lights();
		test.setPins(false, false);
		assertTrue(test.getLightPins()==0);
	}
	
//***********************************ID TESTS*********************************************
	@Test
	public void testSetID(){
		Lights test = new Lights(0, 0, false, false);
		test.setSchedule(1234567890);
		test.setID();
		Long id = new Long(((1234567890*31)^3));
		long id2 = test.getId();
		assertTrue(id2!=id);
	}//something weird going on between long and Long
	
//********************************SET ON OFF TIME TESTS***********************************
	@Test
	public void offTimeTest(){
		Lights test = new Lights();
		test.setLOff(800);
		assertTrue(test.getOffTime()==800);
	}
	
	@Test
	public void onTimeTest(){
		Lights test = new Lights();
		test.setLOn(700);
		assertTrue(test.getOnTime()==700);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
