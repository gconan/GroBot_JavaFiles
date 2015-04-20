package groBot.entity;

import groBot.dao.UserDAO;

import java.io.Serializable;
import java.util.ArrayList;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

/**
 * A user is anybody accessing GroBot website. 
 * User's must register with the website before they can log-in/ view the website's contents
 * @author conangammel
 */
@Entity
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**Unique access code sent embedded in a link sent to the user's email address to verify his/her authenticity.*/
	@Index 
	private String accessCode;
	
	/**The user's login password.*/
	private String password;
	
	/**User's first name*/
	@Index
	private String firstName;
	
	/**User's last name*/
	private String lastName;
	
	/**The GroBot the user is currently connected to.*/
	@Index
	private GroBots currentBot;
	
	/**The GroBot's name that the user is currently connected to.*/
	@Index
	private String currentBotName;
	
	/**List of User's GroBots*/
	private ArrayList<GroBots> myBots;
	
	/**User's email address. Used as the Objectify ID since no two users should have the same email address.*/
	@Id 
	private String email;
	
	/**List of IDs linked to the User's custom schedules stored in Objectify.*/
	@Index
	private ArrayList<Long> customSchedules;
	
	/**Boolean that proves that a user has confirmed his/her registration through email confirmation.*/
	private boolean registrationStatus;
	
	/**
	 * Constructs a user object and his/her access code through HashCode.
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
	
	/**
	 * As per our requirements, each user gets default schedules for certain plants.
	 */
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
	}

	/**
	 * Default constructor for Objectify.
	 * @author conangammel
	 */
	@SuppressWarnings("unused")
	private User() {
		
	}
	
	/**
	 * Returns the user's access code.
	 * @author conangammel
	 * @return accessCode
	 */
	public String getAccess_code() {
		return accessCode;
	}
	
	/**
	 * Returns the user's password.
	 * @author conangammel
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Returns the user's email address.
	 * @return email address
	 */
	public String getEmail() {
		return this.email;
	}
	
	/**
	 * Returns the current GroBot.
	 * @return current GroBot
	 */
	public GroBots getGroBot(){
		return this.currentBot;
	}
	
	/**
	 * Returns the full list of all of the user's GroBots.
	 * @return list of all of the user's GroBots
	 */
	public ArrayList<GroBots> getAllBots(){
		if(this.myBots==null){
			return new ArrayList<GroBots>();
		}
		return this.myBots;
	}
	
	/**
	 * Returns the user's first name.
	 * @return firstName
	 */
	public String getFirstName(){
		return this.firstName;
	}
	
	/**
	 * Returns the user's last name.
	 * @return lastName
	 */
	public String getLastName(){
		return this.lastName;
	}
	
	/**
	 * Adds a new GroBot to the user's list of GroBots.
	 * @param bot
	 */
	public void addGroBot(GroBots bot){
		if(this.myBots==null)this.myBots = new ArrayList<GroBots>();
		this.myBots.add(bot);
		this.currentBot = bot;
		this.currentBotName = bot.getName();
	}
	
	
	/**
	 * Return registration status of the user.
	 * @author conangammel
	 * @return boolean
	 */
	public boolean getStatus() {
		return registrationStatus;
	}
	
	/**
	 * Sets the user's current GroBot to the GorBot parameter.
	 * @param grobot
	 */
	public void setCurrentBot(GroBots grobot){
		this.currentBot = grobot;
		this.currentBotName = grobot.getName();
	}
	
	/**
	 * Set user's status to true (they are allowed to log-in)
	 * @author conangammel
	 */
	public void activate() {
		this.registrationStatus = true;
	}
	
	/**
	 * Updates user object with given password
	 * @author conangammel
	 * @param String
	 */
    public void setPassword(String newPassword) {
    	this.password = newPassword;
    }
	
    /**
	 * Generates random password and updates user object with new password.
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
    
    /**
     * Returns the current GroBot's name
     * @return name of current GroBot
     */
    public String getBotName(){
    	return this.currentBot.getName();
    }

	/**
	 * Compares two users to determine if they are the same.
	 * @param user to compare with "this"
	 * @return true || false
	 */
	public boolean isSameUser(User user) {
		if(user.email.equals(this.email) && user.password.equals(this.password)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Returns a list of IDs that link to the user's custom schedules stored in Objectify.
	 * @return list of custom schedule IDs
	 */
	public ArrayList<Long> getSchedules(){
		if(this.customSchedules==null){
			return new ArrayList<Long>();
		}
		return this.customSchedules;
	}
	
	/**
	 * Add a schedule to the user's list.
	 * @param schedule
	 */
	public void addCustomSchedule(Schedule schedule){
		if(this.customSchedules == null) this.customSchedules = new ArrayList<Long>();
		int index=0;
		for(int i=0; i<this.customSchedules.size(); i++){
			int thisPop = Schedule.get(this.customSchedules.get(i)).getPopularity();
			int thatPop = schedule.getPopularity();
			if(thisPop<thatPop){
				index = i;
				break;
			}
		}
		
		this.customSchedules.add(index, schedule.id());
	}
	
	/**
	 * Return the name of the GroBot the user is connected to.
	 * @return name of currently connected GroBot
	 */
	public String getCurrentBotName(){
		return this.currentBotName;
	}

	/**
	 * Delete a schedule given the ID. If the GroBot is running this schedule, then change it to the schedule at the head of the list.
	 * @param id
	 */
	public void removeSchedule(Long id) {
		this.customSchedules.remove(id);
		
		if(this.currentBot.getCurrentSchedule().getId()==id){
			this.currentBot.runSchedule(UserDAO.INSTANCE.getSchedule(this.customSchedules.get(0)));
			UserDAO.INSTANCE.addGroBot(this.currentBot);//save grobots with updated schedule
		}
		for(GroBots g: this.myBots){
			if(g.getCurrentSchedule().getId()==id){
				g.runSchedule(UserDAO.INSTANCE.getSchedule(this.customSchedules.get(0)));
				UserDAO.INSTANCE.addGroBot(g);//save grobots with updated schedule
			}
		}
	}
}
