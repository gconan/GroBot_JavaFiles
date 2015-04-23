package groBot.entity;

import groBot.dao.UserDAO;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

/**
 * Object representation of a growing schedule.
 * The data stored in a Schedule object drives the automation of the GroBot timings.
 * @author conangammel
 *
 */
@Entity
public class Schedule {
	
	/**User given name for the schedule (ie. Chile Peppers)*/
	private String name;
	
	/**Upper case version of name so that the options from HTML are all the same for comparing*/
	private String value;
	
	/**True if the auxiliary port is on, false if it is off. No timings associated with the port, just on/off.*/
	private Boolean auxPort;
	
	/**True if the air port is on, false if it is off. No timings associated with the port, just on/off.*/
	private Boolean airStone;
	
	/**Light schedule that contains the pins and on/off times.*/
	@Index
	private Lights lightSchedule;
	
	/**Water schedule that contains the frequency and duration of watering times.*/
	@Index
	private Water waterSchedule;
	
	/**How often a user grows with this schedule. Orders a list for displaying the most popular schedules first on the website.*/
	@Index
	private int popularity;
	
	/**Objectify ID*/
	@Id
	private Long scheduleID;
	
	/**
	 * Default schedule for Objectify.
	 */
	public Schedule(){
//		this.name = "";
//		this.value = "";
//		this.auxPort = false;
//		this.airStone = false;
//		this.lightSchedule = new Lights();
//		this.waterSchedule = new Water();
//		this.popularity = 0;
//		this.scheduleID = (31);
//		this.waterSchedule.setSchedule(this.scheduleID);
//		this.lightSchedule.setSchedule(this.scheduleID);
	}
	
	/**
	 * Constructor: only sets the name. Expects that the remainder of the data members will be set using the set methods.
	 * @param name User given name for the schedule (ie. Chile Peppers)
	 */
	public Schedule(String name){
		this.name = name;
		this.value = this.name.toUpperCase();
		this.popularity = 0;
		this.auxPort = false;
		this.airStone = false;
		this.scheduleID = (long)(this.name.hashCode()*31);
	}
	
	/**
	 * Set LightSchedule.
	 * @param lights Lights to set this Schedule with
	 */
	public void setLights(Lights lights){
		this.lightSchedule = lights;
	}
	
	/**
	 * Set WaterSchedule
	 * @param water Water to set this Schedule with
	 */
	public void setWater(Water water){
		this.waterSchedule = water;
	}
	
	/**
	 * Set the name of the schedule given by the user. This will only be used when the changes the name when editing the schedule.
	 * @param name name of the Schedule given by the user
	 */
	public void setName(String name){
		this.name = name;
		this.value = name.toUpperCase();
	}
	
	/**
	 * Returns a String representation of the Objectify ID.
	 * @return String - ""+id
	 */
	public String getValue(){
		return ""+this.id();
	}
	
	/**
	 * Set the auxiliary port.
	 * @param aux true=on false=off
	 */
	public void setAux(boolean aux){
		this.auxPort = aux;
	}
	
	/**
	 * Set the air port.
	 * @param air true=on false=off
	 */
	public void setAir(boolean air){
		this.airStone = air;
	}
	
	/**
	 * Return the name of the Schedule.
	 * @return the name of the schedule
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Returns the popularity.
	 * @return int - popularity
	 */
	public int getPopularity(){
		return this.popularity;
	}
	
	/**
	 * Increment the popularity. Used when a user grows with this schedule.
	 */
	public void upPopularity(){
		this.popularity+=1;
	}
	
	/**
	 * Return the schedule's Objectify ID
	 * @return the Objectify ID of this Schedule
	 */
	public long id(){
		return this.scheduleID;
	}
	
	/**
	 * Creates a new water schedule using the parameters and then sets this Schedule's water schedule to the new one.
	 * @param duration length of time the water is on
	 * @param period how often the water turns on
	 */
	public void newWaterSchedule(int duration, int period){
		this.waterSchedule = new Water();
		this.waterSchedule.setDuration(duration);
		this.waterSchedule.setPeriod(period);
		this.waterSchedule.setSchedule(this.scheduleID);
	}
	
	/**
	 * Sets the water schedule's id. This is done after the water schedule is fully initialized so that the id is not zero.
	 */
	public void setWaterID(){
		this.waterSchedule.setID();
	}
	
	/**
	 * Sets the light schedule's id. This is done after the light schedule is fully initialized so that the id is not zero.
	 */
	public void setLightID(){
		this.lightSchedule.setID();
	}
	
	/**
	 * Returns the water schedule's Objectify ID
	 * @return long - Objectify ID
	 */
	public long getWaterId(){
		return this.waterSchedule.getId();
	}
	
	/**
	 * Returns the light schedule's Objectify ID
	 * @return long - Objectify ID
	 */
	public long getLightId(){
		return this.lightSchedule.getId();
	}
	
	/**
	 * Creates a new light schedule and sets the new schedule to this object's light schedule
	 * @param lightsOnTime time the lights turn on (in military time)
	 * @param lightsOffTime time the lights turn off (in military time)
	 * @param pin1 lights on pin one on/off
	 * @param pin2 lights on pin two on/off
	 */
	public void newLights(int lightsOnTime, int lightsOffTime, boolean pin1, boolean pin2){
		this.lightSchedule = new Lights(lightsOnTime, lightsOffTime, pin1, pin2);
		this.lightSchedule.setSchedule(this.scheduleID);

	}
	
	/**
	 * Returns the Schedule stored in Objectify using the ID.
	 * @param id Objectify ID of the desired Schedule
	 * @return Schedule linked to the ID provided
	 */
	public static Schedule get(Long id) {
		return UserDAO.INSTANCE.getSchedule(id);
	}
	
	/**
	 * Returns a String representation of the lights on time.
	 * @return String - time the lights turn on
	 */
	public String getLightOn() {
		return ""+this.lightSchedule.getOnTime();
	}

	/**
	 * Returns a String representation of the lights off time.
	 * @return String - time the lights turn off
	 */
	public String getLightOff() {
		return ""+this.lightSchedule.getOffTime();
	}
	
	/**
	 * Returns the Objectify ID.
	 * @return long - Objectify ID
	 */
	public long getId(){
		return this.scheduleID;
	}

	/**
	 * Returns a String representation of the lights pins.
	 * @return String - which light pins are on
	 */
	public String getLightPins() {
		return ""+this.lightSchedule.getLightPins();
	}

	/**
	 * Returns a String representation of the water duration.
	 * @return String - length of time the water pump is on
	 */
	public String getWaterDuration() {
		return ""+this.waterSchedule.getWaterDuration();
	}

	/**
	 * Returns a String representation of the water period.
	 * @return String - how often the plants are watered
	 */
	public String getWaterPeriod() {
		return ""+this.waterSchedule.getWaterPeriod();
	}

	/**
	 * Returns a String representation whether the auxiliary port is on or off
	 * @return "true" || "false"
	 */
	public String getAux() {
		return ""+this.auxPort;
	}

	/**
	 * Returns a String representation whether the air port is on or off
	 * @return "true" || "false"
	 */
	public String getAir() {
		return ""+this.airStone;
	}

	/**
	 * Sets the light timing and pins for the light schedule. Used when a user edits his/her schedule.
	 * @param lightsOnTime time to turn the lights on
	 * @param lightsOffTime time to turn the lights off
	 * @param pin1 set 1 lights
	 * @param pin2 set 2 lights
	 */
	public void setLights(int lightsOnTime, int lightsOffTime, boolean pin1, boolean pin2) {
		this.lightSchedule.setLOn(lightsOnTime);
		this.lightSchedule.setLOff(lightsOffTime);
		this.lightSchedule.setPins(pin1,pin2);
		
	}

	/**
	 * Sets the water duration and period for the water schedule. Used when a user edits his/her schedule.
	 * @param wlength length of time to run the water pump
	 * @param wperiod the frequency of how often the water pump turns on
	 */
	public void setWaterSchedule(int wlength, int wperiod) {
		this.waterSchedule.setDuration(wlength);
		this.waterSchedule.setPeriod(wperiod);
		
	}

	/**
	 * Sets the Objectify ID of the schedule.
	 * @param hashCode ID for storing in Objectify
	 */
	public void setID(long hashCode) {
		this.scheduleID = hashCode;
		
	}
}
