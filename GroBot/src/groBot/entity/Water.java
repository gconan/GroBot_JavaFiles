package groBot.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;


@Entity
public class Water{
	
	/**
	 * Length of time the water pump runs.
	 */
	private int duration;
	
	/**
	 * Length of time between watering cycles
	 */
	private int period;
	
	/**Objectify ID*/
	@Id
	private Long id;
	
	/**ID of the schedule this water schedule belongs to.*/
	@Index
	private long sched_id;
	
	/**
	 * Default constructor for Objectify.
	 */
	public Water(){
		this.duration = 0;
		this.period = 0;
		this.id = (long) this.hashCode();
	}
	
	/**
	 * Constructor that initializes the duration and period.
	 * @param dur
	 * @param per
	 */
	public Water(int dur, int per){
		this.duration = dur;
		this.period = per;
		this.id = (long) this.hashCode();
	}
	
	/**
	 * Returns the integer value of the period.
	 * @return int - period
	 */
	public int getWaterPeriod(){
		return this.period;
	}
	
	/**
	 * Returns the integer value of the duration.
	 * @return int - duration
	 */
	public int getWaterDuration(){
		return this.duration;
	}
	
	/**
	 * Returns the Objectify ID
	 * @return long - ID
	 */
	public long getId(){
		return this.id;
	}
	
	/**
	 * Sets the duration.
	 * @param duration
	 */
	public void setDuration(int duration){
		this.duration = duration;
	}
	
	/**
	 * Sets the period.
	 * @param period
	 */
	public void setPeriod(int period){
		this.period = period;
	}
	
	/**
	 * Sets the Objectify ID of this water schedule.
	 */
	public void setID(){
		this.id = (this.sched_id*31)^5;
	}
	
	/**
	 * Sets the ID of the schedule this water schedule is associated with.
	 * @param id_of_schedule
	 */
	public void setSchedule(long id_of_schedule){
		this.sched_id = id_of_schedule;
	}
}
