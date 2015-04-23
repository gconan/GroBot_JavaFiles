package groBot.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

/**
 * This class represents the light bulb sockets and their timings
 * There are on and off times for the lights that we will send to the Microcontroller to switch power
 * Different sets of lights are determined by the int lightPins (see member documentation)
 * @author conangammel
 *
 */
@Entity
public class Lights {
	
	/**Time the lights turn on. In military time*/
	private int onTime;
	
	/**Time the lights turn off. In military time*/
	private int offTime;
	
	/**Numerical representation of the two light ports.
	 * 00 maps to both off.
	 * 01 maps to pin1 off and pin2 on.
	 * 02 maps to pin1 on and pin2 off.
	 * 03 maps to both pins on.*/
	private int lightPins;
	
	/**Objectify ID*/
	@Id
	private Long id;
	
	/**ID of the Schedule that the lights belong to
	 * @see Schedule
	 */
	@Index
	private long sched_id;
	
	/**Default constructor for Objectify (required for storing).*/
	public Lights(){
		this.offTime = 0;
		this.onTime = 0;
		this.lightPins = 0;		
	}
	
	/**
	 * Constructor used by this package only. This is used by the addNewScheduleServlet.
	 * @param onT on time of the lights
	 * @param offT off time of the lights
	 * @param pin1 pin1 boolean true=on false=off
	 * @param pin2 pin2 boolean true=on false=off
	 */
	public Lights(int onT, int offT, Boolean pin1, Boolean pin2){
		this.onTime = onT;
		this.offTime = offT;
		this.lightPins = convertBooleantoInt(pin1, pin2);
		this.id = (long) this.offTime*this.onTime*31;
	}

	/**
	 * This method takes in the light pin booleans and converts it to an integer.
	 * @param pin1 pin1 boolean true=on false=off
	 * @param pin2 pin2 boolean true=on false=off
	 * @return Integer - 00 for both off.
	 * 01 for pin1 off and pin2 on.
	 * 02 for pin1 on and pin2 off.
	 * 03 for both pins on.
	 */
	private int convertBooleantoInt(Boolean pin1, Boolean pin2) {
		if(!pin1 && !pin2){
			return 0;
		}else if(!pin1 && pin2){
			return 1;
		}else if(pin1 && !pin2){
			return 2;
		}else{
			return 3;
		}
	}
	
	/**
	 * Returns the integer representing the light pins.
	 * @return Which pins are on according to: 00 for both off.
	 * 01 for pin1 off and pin2 on.
	 * 02 for pin1 on and pin2 off.
	 * 03 for both pins on.
	 */
	public int getLightPins(){
		return this.lightPins;
	}
	
	/**
	 * Return the Objectify ID.
	 * @return long - Objectofy ID
	 */
	public long getId(){
		return this.id;
		
	}
	
	/**
	 * Return the time the lights should turn on.
	 * @return int - the time the lights should turn on (in military time)
	 */
	public int getOnTime(){
		return this.onTime;
	}
	
	/**
	 * Set the Objectify ID.
	 */
	public void setID(){
		this.id = (this.sched_id*31)^3;
	}
	
	/**
	 * Return the time the lights should turn off.
	 * @return int - the time the lights should turn off (in military time)
	 */
	public int getOffTime(){
		return this.offTime;
	}
	
	/**
	 * Set the ID of the schedule this lightSchedule is associated with.
	 * @param id_of_schedule id of schedule that this Lights belongs to
	 */
	public void setSchedule(long id_of_schedule){
		this.sched_id = id_of_schedule;
	}

	/**
	 * Set the light on time.
	 * @param lightsOnTime in military time
	 */
	public void setLOn(int lightsOnTime) {
		this.onTime = lightsOnTime;
		
	}

	/**
	 * Set the light off time.
	 * @param lightsOffTime in military time
	 */
	public void setLOff(int lightsOffTime) {
		this.offTime = lightsOffTime;
		
	}

	/**
	 * Set which light pins are turned on or off.
	 * @param set1 light set one
	 * @param set2 light set two
	 */
	public void setPins(boolean set1, boolean set2) {
		this.lightPins = this.convertBooleantoInt(set1, set2);
	}
}
