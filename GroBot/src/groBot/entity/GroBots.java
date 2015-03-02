package groBot.entity;

import java.util.ArrayList;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

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
	private long id;
	
	private byte[] macAddress;
	
	private String name;
	
	/**
	 * TODO
	 */
	public GroBots(){
		this.id = 123456;	//default for BS adding to objectify
		this.name = "****DefaultBot****";
	}
	
	public GroBots(String name, byte[] add){
		this.name = name;
		this.macAddress = add;
		this.id = this.macAddress.hashCode();
	}
	
	/**
	 * TODO
	 * @param sched
	 */
	public void runSchedule(Schedule sched){
		
	}
	
	public String getName(){
		return this.name;
	}
	
	/**
	 * TODO
	 */
	public void turnLightsOn(){
		
	}
	
	/**
	 * TODO
	 */
	public void turnLightsOff(){
		
	}
	
	/**
	 * TODO
	 */
	public void turnWaterOn(){
		
	}
	
	/**
	 * TODO
	 */
	public void turnWaterOff(){
		
	}
	
	/**
	 * TODO
	 */
	public void turnAirOn(){
		
	}
	
	/**
	 * TODO
	 */
	public void turnAirOff(){
		
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
}
