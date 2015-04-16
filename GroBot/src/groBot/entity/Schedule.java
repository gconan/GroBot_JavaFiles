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
	
	@Index
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
	
	public String getLightOn() {
		return ""+this.lightSchedule.getOnTime();
	}

	public String getLightOff() {
		return ""+this.lightSchedule.getOffTime();
	}
	
	public long getId(){
		return this.scheduleID;
	}

	public String getLightPins() {
		return ""+this.lightSchedule.getLightPins();
	}

	public String getWaterDuration() {
		return ""+this.waterSchedule.getWaterDuration();
	}

	public String getWaterPeriod() {
		return ""+this.waterSchedule.getWaterPeriod();
	}

	public String getAux() {
		return ""+this.auxPort;
	}

	public String getAir() {
		return ""+this.airStone;
	}

	public void setLights(int lightsOnTime, int lightsOffTime, boolean s1, boolean s2) {
		this.lightSchedule.setLOn(lightsOnTime);
		this.lightSchedule.setLOff(lightsOffTime);
		this.lightSchedule.setPins(s1,s2);
		
	}

	public void setWaterSchedule(int wlength, int wperiod) {
		this.waterSchedule.setDuration(wlength);
		this.waterSchedule.setPeriod(wperiod);
		
	}

	public void setID(long hashCode) {
		this.scheduleID = hashCode;
		
	}
}
