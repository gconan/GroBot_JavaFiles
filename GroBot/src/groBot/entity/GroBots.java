package groBot.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

/**
 * Object representation of the GroBot
 * This object contains the macAddress of the GroBot and allows the user to name the GroBot
 * This Class also interacts with the hardware via THE INTERNET <que thunder>
 * @author conangammel
 *
 */
@Entity
public class GroBots {
	
	/**An ID for unique storage in Objectify. HashCode of MAC address*/
	@Id
	private Long id;
	
	/**The unique MAC address of each microcontroller. If brought to market, this would be in the instruction manual for users.*/
	private String macAddress;
	
	/**User's name for the GroBot*/
	@Index
	private String name;
	
	/**The schedule that the GroBot is currently running. Information from this schedule will be used to communicate schedules info to the microcontroller*/
	@Index
	private Schedule currentSchedule;
	
	/**Email address of the user who owns the GroBot*/
	@Index
	private String ownerEmail;
	
	/**String representation of the time remaining in the light cycle, or the time until the lights turn on next. This information comes from the microcontroller.*/
	private String lightTimeRemaining;
	
	/**String representation of the time remaining in the water cycle, or the time until the water turns on next. This information comes from the microcontroller.*/
	private String waterTimeRemaining;
	
	/**Boolean to tell the "status" page whether the auxiliary port is on or off. This information comes from the microcontroller.*/
	private Boolean auxOn;
	
	/**Boolean to tell the "status" page whether the air port is on or off. This information comes from the microcontroller.*/
	private Boolean airOn;
	
	/**Boolean to tell the "status" page whether the lights are on or off. This information comes from the microcontroller.*/
	private Boolean lightOn;
	
	/**Boolean to tell the "status" page whether the water port is on or off. This information comes from the microcontroller.*/
	private Boolean waterOn;
	
	/**
	 * Default Constructor. Needed for Objectify. Each user gets this default GroBot until he/she adds a real GroBot with a MAC address.
	 */
	public GroBots(){
		this.name = "****DefaultBot****";
		this.id = (long) this.name.hashCode();
		this.lightOn = false;
		this.waterOn = false;
		this.auxOn = false;
		this.airOn = false;
		this.lightTimeRemaining = "0";
		this.waterTimeRemaining = "0";
	}
	
	/**
	 * Constructor that takes a name, MAC address, and the owner's email address.
	 * Initializes the GroBot with a default schedule (avoids null pointer exception on the website). This schedule
	 * can be changed by the user by running a new schedule.
	 * @param name User given name to the GroBot
	 * @param add MAC address of the GroBot
	 * @param owner email address of the User who owns this GroBot
	 */
	public GroBots(String name,String add, String owner){
		this.name = name;
		this.macAddress = add.trim();
		this.id = (long) this.macAddress.hashCode();
		this.ownerEmail = owner;
		this.lightOn = false;
		this.waterOn = false;
		this.lightTimeRemaining = "0";
		this.waterTimeRemaining = "0";
		this.auxOn = false;
		this.airOn = false;
		
		this.currentSchedule = new Schedule("Null Schedule, Not running");
		
	}
	
	/**
	 * Changes the currentSchedule to the parameter and sets the auxiliary and air ports accordingly.
	 * @param Sched schedule to run on the GroBot
	 */
	public void runSchedule(Schedule sched){
		this.currentSchedule = sched;
		//an http request will automatically use the "currentSchedule" as data for the GroBot to read.
		this.auxOn = Boolean.parseBoolean(sched.getAux());
		this.airOn = Boolean.parseBoolean(sched.getAir());
	}
	
	/**
	 * Returns the user-given name of the GroBot.
	 * @return Name given by the user
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Returns the unique ID of the GroBot.
	 * @return Objectify ID
	 */
	public long getId(){
		return this.id;
	}
	
	/**
	 * Returns whether the water is on according to the last update from the microcontroller.
	 * @return Returns whether the water is on according to the last update from the microcontroller.
	 */
	public Boolean isWaterOn(){
		return this.waterOn;
	}
	
	/**
	 * Returns whether the lights are on according to the last update from the microcontroller.
	 * @return Returns whether the lights are on according to the last update from the microcontroller.
	 */
	public Boolean areLightsOn(){
		return this.lightOn;
	}
	
	/**
	 * Returns whether the air is on according to the last update from the microcontroller.
	 * @return Returns whether the air is on according to the last update from the microcontroller.
	 */
	public Boolean isAirOn(){
		return this.airOn;
	}
	
	/**
	 * Returns whether the auxiliary port is on according to the last update from the microcontroller.
	 * @return Returns whether the auxiliary port is on according to the last update from the microcontroller.
	 */
	public Boolean isAuxOn(){
		return this.auxOn;
	}
	
	/**
	 * Returns the current schedule the GroBot is running.
	 * @return The schedule running on the GroBot
	 */
	public Schedule getCurrentSchedule(){
		return this.currentSchedule;
	}

	/**
	 * Returns a String representation as of the time remaining on the light cycle according to the last update from the microcontroller.
	 * @return Returns a String representation as of the time remaining on the light cycle according to the last update from the microcontroller.
	 */
	public String getLightTimeRemaining() {
		return lightTimeRemaining;
	}

	/**
	 * Sets the time remaining on the light cycle. 
	 * @param lightTimeRemaining information comes from the microcontroller
	 */
	public void setLightTimeRemaining(String lightTimeRemaining) {
		this.lightTimeRemaining = lightTimeRemaining;
	}

	/**
	 * Returns whether the auxiliary port is on according to the last update from the microcontroller.
	 * @return Returns whether the auxiliary port is on according to the last update from the microcontroller.
	 */
	public String getWaterTimeRemaining() {
		return waterTimeRemaining;
	}

	/**
	 * Sets the time remaining on the water cycle. 
	 * @param waterTimeRemaining information comes from the microcontroller
	 */
	public void setWaterTimeRemaining(String waterTimeRemaining) {
		this.waterTimeRemaining = waterTimeRemaining;
	}

	/**
	 * Returns a String representation as to whether the auxiliary port is on according to the last update from the microcontroller.
	 * @return Returns whether the auxiliary port is on according to the last update from the microcontroller.
	 */
	public String getAuxOn() {
		return ""+auxOn;
	}

	/**
	 * Sets whether the auxiliary port is on or off. 
	 * @param auxOn information comes from the microcontroller
	 */
	public void setAuxOn(String auxOn) {
		this.auxOn = Boolean.parseBoolean(auxOn);
	}

	/**
	 * Returns a String representation as to whether the air port is on according to the last update from the microcontroller.
	 * @return Returns whether the air port is on according to the last update from the microcontroller.
	 */
	public String getAirOn() {
		return ""+airOn;
	}

	/**
	 * Sets whether the air port is on or off. 
	 * @param airOn information comes from the microcontroller
	 */
	public void setAirOn(String airOn) {
		this.airOn = Boolean.parseBoolean(airOn);
	}

	/**
	 * Returns a String representation as to whether the lights are on according to the last update from the microcontroller.
	 * @return Returns whether the lights are on according to the last update from the microcontroller.
	 */
	public String getLightOn() {
		return ""+lightOn;
	}

	/**
	 * Sets whether the lights are on or off. 
	 * @param lightOn information comes from the microcontroller
	 */
	public void setLightOn(String lightOn) {
		this.lightOn = Boolean.parseBoolean(lightOn);
	}

	/**
	 * Returns a String representation as to whether the water pump is on according to the last update from the microcontroller.
	 * @return Returns whether the water pump is on according to the last update from the microcontroller.
	 */
	public String getWaterOn() {
		return ""+waterOn;
	}

	/**
	 * Sets whether the water pump is on or off. 
	 * @param waterOn information comes from the microcontroller
	 */
	public void setWaterOn(String waterOn) {
		this.waterOn = Boolean.parseBoolean(waterOn);
	}
	
	public String getOwner(){
		return this.ownerEmail;
	}
}
