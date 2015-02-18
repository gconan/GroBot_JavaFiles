package groBot.entity;

public class Schedule {
	
	private String name;
	
	private Boolean auxPort;
	
	private Boolean airStone;
	
	private Lights lightSchedule;
	
	private Water waterSchedule;
	
	
	public Schedule(){
		this.name = "";
		this.auxPort = false;
		this.airStone = false;
		this.lightSchedule = new Lights();
		this.waterSchedule = new Water();
	}
	
	public Schedule(String name, Boolean aux, Boolean air){
		this.name = name;
		this.auxPort = aux;
		this.airStone = air;
	}
	
	public void setLights(Lights l){
		this.lightSchedule = l;
	}
	
	public void setWater(Water w){
		this.waterSchedule = w;
	}

}
