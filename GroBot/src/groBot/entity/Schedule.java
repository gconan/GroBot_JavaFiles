package groBot.entity;

import groBot.dao.UserDAO;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Schedule {
	
	private String name;
	
	private String value;
	
	private Boolean auxPort;
	
	private Boolean airStone;
	
	@Index
	private Lights lightSchedule;
	
	@Index
	private Water waterSchedule;
	
	private int popularity;
	
	@Id
	private Long scheduleID;
	
	
	protected Schedule(){
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
	
	public Schedule(String name){
		this.name = name;
		this.value = this.name.toUpperCase();
		this.popularity = 0;
		this.auxPort = false;
		this.airStone = false;
		this.popularity = 0;
		this.scheduleID = (long)(this.name.hashCode()*31);
	}
	
	public void setLights(Lights l){
		this.lightSchedule = l;
	}
	
	public void setWater(Water w){
		this.waterSchedule = w;
	}
	
	public void setName(String n){
		this.name = n;
		this.value = n.toUpperCase();
	}
	
	public String getValue(){
		return ""+this.id();
	}
	
	public void setAux(boolean b){
		this.auxPort = b;
	}
	
	public void setAir(boolean b){
		this.airStone = b;
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getPopularity(){
		return this.popularity;
	}
	
	public void upPopularity(){
		this.popularity+=1;
	}
	
	public long id(){
		return this.scheduleID;
	}
	
	public void newWaterSchedule(int duration, int period){
		this.waterSchedule = new Water();
		this.waterSchedule.setDuration(duration);
		this.waterSchedule.setPeriod(period);
		this.waterSchedule.setSchedule(this.scheduleID);
	}
	
	public void setWaterID(){
		this.waterSchedule.setID();
	}
	public void setLightID(){
		this.lightSchedule.setID();
	}
	public long getWaterId(){
		return this.waterSchedule.getId();
	}
	
	public long getLightId(){
		return this.lightSchedule.getId();
	}
	
	public void newLights(int lightsOnTime, int lightsOffTime, boolean s1, boolean s2){
		this.lightSchedule = new Lights(lightsOnTime, lightsOffTime, s1, s2);
		this.lightSchedule.setSchedule(this.scheduleID);

	}
	
	public static Schedule get(Long long1) {
		return UserDAO.INSTANCE.getSchedule(long1);
	}
	
//	public void updateID(){
//		long temp = this.scheduleID;
//		if(temp==0){
//			this.scheduleID = ((this.waterSchedule.getId()+this.lightSchedule.getId())*31+this.name.hashCode()*31);
//			this.waterSchedule.setSchedule(this.scheduleID);
//			this.lightSchedule.setSchedule(this.scheduleID);
//		}else{return;}
//	}

	public String getLightOn() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getLightOff() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public long getId(){
		return this.scheduleID;
	}

	public String getLightPins() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getWaterDuration() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getWaterPeriod() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getAux() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getAir() {
		// TODO Auto-generated method stub
		return null;
	}
}
