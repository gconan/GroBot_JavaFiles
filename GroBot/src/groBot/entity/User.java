package groBot.entity;

import groBot.dao.UserDAO;

import java.io.Serializable;
import java.util.ArrayList;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

/**
 * A user is anybody accessing UTBazaar website. 
 * User's must register with the website before they can log-in/ view the website's contents
 * @author conangammel
 */
@Entity
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Index 
	private String accessCode;
	
	private String password;
	
	@Index
	private String firstName;
	
	private String lastName;
	
	@Index
	private GroBots currentBot;
	
	@Index
	private String currentBotName;
	
	private ArrayList<GroBots> myBots;
	
	@Id 
	private String email;
	
	@Index
	private ArrayList<Long> customSchedules;
	
	private boolean registrationStatus;
	
	/**
	 * constructs a user object and his/her access code through HashCode
	 * @author conangammel
	 * @param password
	 * @param email
	 */
	public User(String fn, String ln, String password, String email) {
		this.registrationStatus = false;
		this.password = password;
		this.firstName = fn;
		this.lastName = ln;
		this.email = email;
		long hash = Math.abs(email.hashCode() * (long) Math.pow(31,5));	
		StringBuilder code = new StringBuilder();	                         
		while (hash > 0) {
			code.append((char)('a' + hash%27));
			hash/= 27;
		}
		this.accessCode = code.toString();
		
		this.myBots = new ArrayList<GroBots>();
		this.currentBot = new GroBots();
		this.currentBotName = currentBot.getName();
		this.customSchedules = new ArrayList<Long>();
		addDefaultSchedules();
	}
	
	private void addDefaultSchedules() {
		Schedule cayenne = new Schedule("Cayenne Peppers");
		Schedule tomatoes = new Schedule("Tomatoes");
		Schedule habanero = new Schedule("Habanero Peppers");
		
		cayenne.newLights(600, 1800, true, true);
		cayenne.newWaterSchedule(15, 6);
		cayenne.setAir(true);
		cayenne.setAux(false);
		cayenne.setWaterID();
		cayenne.setLightID();
		cayenne.setID((long)(this.email+"Cayenne Peppers").hashCode());
		
		tomatoes.newLights(700, 1600, true, false);
		tomatoes.newWaterSchedule(15, 1);
		tomatoes.setAir(true);
		tomatoes.setAux(false);
		tomatoes.setWaterID();
		tomatoes.setLightID();
		tomatoes.setID((long)(this.email+"Tomatoes").hashCode());
		
		habanero.newLights(600, 1800, true, true);
		habanero.newWaterSchedule(15, 6);
		habanero.setAir(true);
		habanero.setAux(false);
		habanero.setWaterID();
		habanero.setLightID();
		habanero.setID((long)(this.email+"Habanero Peppers").hashCode());
		
		this.customSchedules.add(cayenne.id());
		this.customSchedules.add(tomatoes.id());
		this.customSchedules.add(habanero.id());
		
		UserDAO.INSTANCE.addSchedule(cayenne);
		UserDAO.INSTANCE.addSchedule(tomatoes);
		UserDAO.INSTANCE.addSchedule(habanero);
		
		this.currentBot.runSchedule(cayenne);
		
//		this.addCustomSchedule(cayenne);
//		this.addCustomSchedule(tomatoes);
//		this.addCustomSchedule(habanero);
	}

	/**
	 * we have to have a default constructor for the sake of objectify
	 * @author conangammel
	 */
	@SuppressWarnings("unused")
	private User() {
		
	}
	
	/**
	 * returns the user's access code
	 * @author conangammel
	 * @return accessCode
	 */
	public String getAccess_code() {
		return accessCode;
	}
	
	/**
	 * returns the user's password
	 * @author conangammel
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public GroBots getGroBot(){
		return this.currentBot;
	}
	
	public ArrayList<GroBots> getAllBots(){
		if(this.myBots==null){
			return new ArrayList<GroBots>();
		}
		return this.myBots;
	}
	
	public String getFirstName(){
		return this.firstName;
	}
	
	public String getLastName(){
		return this.lastName;
	}
	
	public void addGroBot(GroBots bot){
		if(this.myBots==null)this.myBots = new ArrayList<GroBots>();
		this.myBots.add(bot);
		this.currentBot = bot;
		this.currentBotName = bot.getName();
	}
	
	
	/**
	 * return registration status of the user
	 * @author conangammel
	 * @return boolean
	 */
	public boolean getStatus() {
		return registrationStatus;
	}
	
	/**
	 * Set user's status to true (they are allowed to log-in)
	 * @author conangammel
	 */
	public void activate() {
		this.registrationStatus = true;
	}
	
	/**
	 * updates user object with given password
	 * @author conangammel
	 * @param String
	 */
    public void setPassword(String newPassword) {
    	this.password = newPassword;
    }
	
    /**
	 * generates random password and updates user object with new password
	 * @author conangammel
	 */
    public String resetPassword() {
    	StringBuilder newPassw = new StringBuilder("");
    	int passLen = 10;
    	for(int i=0; i<passLen; i++) {
    		int j = ((int)(Math.random()*100))%26;
    		newPassw.append((char)(j + 'a'));
    	}
    	String newP = newPassw.toString();
    	Integer p = newP.hashCode();
		String pass = p.toString();
    	this.password = pass;
    	return newP;
    }
    
    public String getBotName(){
    	return this.currentBot.getName();
    }

	
	public boolean isSameUser(User user) {
		if(user.email.equals(this.email) && user.password.equals(this.password)) {
			return true;
		}
		return false;
	}
	
	public ArrayList<Long> getSchedules(){
		if(this.customSchedules==null){
			return new ArrayList<Long>();
		}
		return this.customSchedules;
	}
	
	public void addCustomSchedule(Schedule s){
		if(this.customSchedules == null) this.customSchedules = new ArrayList<Long>();
		int index=0;
		for(int i=0; i<this.customSchedules.size(); i++){
			int thisPop = Schedule.get(this.customSchedules.get(i)).getPopularity();
			int thatPop = s.getPopularity();
			if(thisPop<thatPop){
				index = i;
				break;
			}
		}
		
		this.customSchedules.add(index, s.id());
	}
	
	public String getCurrentBotName(){
		return this.currentBotName;
	}

	public void removeSchedule(Long id) {
		this.customSchedules.remove(id);
		
		if(this.currentBot.getCurrentSchedule().getId()==id){
			this.currentBot.runSchedule(UserDAO.INSTANCE.getSchedule(this.customSchedules.get(0)));
		}
	}
}
