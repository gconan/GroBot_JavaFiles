package groBot.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

/**
 * This class represents the light bulb sockets and their timings
 * HardCoded - number of sockets, final int that can be changed if more or less sockets
 * There are on and off times for the lights that we will send to the Microcontroller to switch power
 * The Boolean array is for which light sockets are on/off
 * @author conangammel
 *
 */
@Entity
public class Lights {
	
	/**time to turn on 0645 = 6:45am*/
	private int onTime;
	
	/**time to turn on 13:45 = 13*60min + 45 = 1:45pm*/
	private int offTime;
	
	/**on/off for each set of three lights*/
	private int lightPins;
	
	@Id
	private Long id;
	
	@Index
	private long sched_id;
	
	public Lights(){
		this.offTime = 0;
		this.onTime = 0;
		this.lightPins = 0;		
	}
	
	protected Lights(int onT, int offT, Boolean pin1, Boolean pin2){
		this.onTime = onT;
		this.offTime = offT;
		this.lightPins = convertBooleantoInt(pin1, pin2);
		this.id = (long) this.offTime*this.onTime*31;
	}

	private int convertBooleantoInt(Boolean pin1, Boolean pin2) {
		if(!pin1 && !pin2){
			return 0;
		}else if(!pin1 && pin2){
			return 1;
		}else if(pin1 && !pin2){
			return 2;
		}else if(pin1 && pin2){
			return 3;
		}
		return 0;
	}
	
	public int getLightPins(){
		return this.lightPins;
	}
	
	public long getId(){
		return this.id;
	}
	
	public int getOnTime(){
		return this.onTime;
	}
	
	public void setID(){
		this.id = (this.sched_id*31)^3;
	}
	
	public int getOffTime(){
		return this.offTime;
	}
	
	public void setSchedule(long id_of_schedule){
		this.sched_id = id_of_schedule;
	}

}
