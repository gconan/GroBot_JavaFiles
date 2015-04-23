package groBot.entity.test;

import static org.junit.Assert.*;
import groBot.entity.Lights;
import groBot.entity.Water;

import org.junit.Test;

public class WaterTester {

//************************************DEFAULT CONSTRUCTOR TESTS********************************
	@Test
	public void defaultConstructorTest1(){
		Water test = new Water();
		assertTrue(test.getWaterDuration()==0);
	}
	
	@Test
	public void defaultConstructorTest2(){
		Water test = new Water();
		assertTrue(test.getWaterPeriod()==0);
	}
	
	@Test
	public void defaultConstructorTest3(){
		Water test = new Water();
		assertTrue(test.getId()!=0);
	}
	
//******************************************CONSTRUCTOR TESTS*******************************	
	@Test
	public void ConstructorTest1(){
		Water test = new Water(15,400);
		assertTrue(test.getWaterDuration()==15);
	}
	
	@Test
	public void ConstructorTest2(){
		Water test = new Water(15,400);
		assertTrue(test.getWaterPeriod()==400);
	}
	
	@Test
	public void ConstructorTest3(){
		Water test = new Water(15,400);
		assertTrue(test.getId()!=0);
	}
	
//*************************************GETTERS AND SETTERS*********************************
	
	@Test
	public void getSetWaterDuration(){
		Water test = new Water();
		assert(test.getWaterDuration()==0);
		test.setDuration(900);
		assertTrue(test.getWaterDuration()==900);
	}
	
	@Test
	public void getSetWaterPeriod(){
		Water test = new Water();
		assert(test.getWaterPeriod()==0);
		test.setPeriod(900);
		assertTrue(test.getWaterPeriod()==900);
	}
	
//**************************************ID TESTS*****************************************
	@Test
	public void testSetID(){
		Water test = new Water(0, 0);
		test.setSchedule(1234567890);
		test.setID();
		Long id = new Long(((1234567890*31)^3));
		long id2 = test.getId();
		assertTrue(id2!=id);
	}//something weird going on between long and Long
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
