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
	
	@Id
	private Long id;
	
	private byte[] macAddress;
	
	private String name;
	
	@Index
	private Schedule currentSchedule;
	
	@Index
	private String ownerEmail;
	
	private String lightTimeRemaining;
	private String waterTimeRemaining;
	private String auxOn;
	private String airOn;
	private String lightOn;
	private String waterOn;
	
	/**
	 * TODO
	 */
	public GroBots(){
		this.name = "****DefaultBot****";
		this.id = (long) this.name.hashCode();
	}
	
	public GroBots(String name, byte[] add, String owner){
		this.name = name;
		this.macAddress = add;
		this.id = (long) this.macAddress.hashCode();
		this.ownerEmail = owner;
	}
	
	/**
	 * TODO
	 * @param sched
	 */
	public void runSchedule(Schedule sched){
		this.currentSchedule = sched;
		//an http request will automatically use the "currentSchedule" as data for the GroBot to read.
	}
	
	public String getName(){
		return this.name;
	}
	
	public long getId(){
		return this.id;
	}
	
	/**
	 * TODO
	 * @return
	 */
	public Boolean isWaterOn(){
		return false;
	}
	
	/**
	 * TODO
	 * @return
	 */
	public Boolean areLightsOn(){
		return false;
	}
	
	/**
	 * TODO
	 * @return
	 */
	public Boolean isAirOn(){
		return false;
	}
	
	public Schedule getCurrentSchedule(){
		return this.currentSchedule;
	}
	
	
	
	
	
	///////////////////////
	public String getLightTimeRemaining() {
		return lightTimeRemaining;
	}

	public void setLightTimeRemaining(String lightTimeRemaining) {
		this.lightTimeRemaining = lightTimeRemaining;
	}

	public String getWaterTimeRemaining() {
		return waterTimeRemaining;
	}

	public void setWaterTimeRemaining(String waterTimeRemaining) {
		this.waterTimeRemaining = waterTimeRemaining;
	}

	public String getAuxOn() {
		return auxOn;
	}

	public void setAuxOn(String auxOn) {
		this.auxOn = auxOn;
	}

	public String getAirOn() {
		return airOn;
	}

	public void setAirOn(String airOn) {
		this.airOn = airOn;
	}

	public String getLightOn() {
		return lightOn;
	}

	public void setLightOn(String lightOn) {
		this.lightOn = lightOn;
	}

	public String getWaterOn() {
		return waterOn;
	}

	public void setWaterOn(String waterOn) {
		this.waterOn = waterOn;
	}
}
