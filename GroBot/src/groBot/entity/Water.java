package groBot.entity;

public class Water {
	
	private int duration;
	
	/**
	 * "every hour" - run the water for <duration> every hour
	 * "every 2 hours"...
	 */
	private int period;
	
	
	public Water(){
		this.duration = 0;
		this.period = 0;
	}
	
	public Water(int dur, int per){
		this.duration = dur;
		this.period = per;
	}
	

}
