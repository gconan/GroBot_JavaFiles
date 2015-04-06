package groBot.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;


@Entity
public class Water{
	
	/**
	 * "15 minutes" - run the water for 15 minutes every <period>
	 */
	private int duration;
	
	/**
	 * "every hour" - run the water for <duration> every hour
	 */
	private int period;
	
	@Id
	private Long id;
	
	@Index
	private long sched_id;
	
	
	public Water(){
		this.duration = 0;
		this.period = 0;
		this.id = (long) this.hashCode();
	}
	
	public Water(int dur, int per){
		this.duration = dur;
		this.period = per;
		this.id = (long) this.hashCode();
	}
	
	public int getWaterPeriod(){
		return this.period;
	}
	
	public int getWaterDuration(){
		return this.duration;
	}
	
	public long getId(){
		return this.id;
	}
	
	public void setDuration(int duration){
		this.duration = duration;
	}
	
	public void setPeriod(int period){
		this.period = period;
	}
	
	public void setID(){
		this.id = (this.sched_id*31)^5;
	}
	
	public void setSchedule(long id_of_schedule){
		this.sched_id = id_of_schedule;
	}
}
