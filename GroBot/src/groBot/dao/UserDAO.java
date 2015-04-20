package groBot.dao;

import static groBot.services.OfyService.ofy;
import groBot.entity.GroBots;
import groBot.entity.Lights;
import groBot.entity.Schedule;
import groBot.entity.User;
import groBot.entity.Water;

/**
 * UserDAO is the "middleMan" between code and the DataStore. 
 * All of the INSTANCE methods pull or save Entities to and from Google's DataStore.
 * @author conangammel
 *
 */
public enum UserDAO {  
	INSTANCE;
	
	
	/**
	 * Returns the GroBot the user is currently connected to.
	 * @param email
	 * @return currently connected GroBot
	 */
	public GroBots getUserBots(String email){
		User user= getUserByEmail(email);
		return user.getGroBot();
	}
	
	
	
	/**
	 * Adds user to database. Acts as an overwrite as well (saves updated users).
	 * @author conangammel
	 * @param User
	 * @return User
	 */
    public User addUser(User user) {  
    	synchronized(this) { 
    		ofy().save().entity(user).now();
    	}
    	return user;
    }
    
    /**
	 * Deletes the user from the DataStore.
	 * @author conangammel
	 * @param User
	 * @return User
	 */
    public User removeUser(User user) {
    	ofy().delete().entities(user);
    	return user;
    }
 
    
    /**
	 * Activates the user so he/she can login.
	 * @author conangammel
	 * @param user
	 * @return User
	 */
    public User activateUser(User user) {	
    	User fromDB = ofy().load().type(User.class).id(user.getEmail()).now();
    	fromDB.activate();
    	ofy().save().entity(fromDB).now();
    	return fromDB;
    }
    
    /**
     * Updates user's password in the DataStore.
     * @param user
     * @param newPassword
     */
    public void setPassword(User user, String newPassword) {
    	User fromDB = ofy().load().type(User.class).id(user.getEmail()).now();
    	fromDB.setPassword(newPassword);
    	ofy().save().entity(fromDB).now();
    }
    
    /**
	 * Generates random password and updates user object in database with new password.
	 * @param user
	 */
    public void resetPassword(User user) {
    	User fromDB = ofy().load().type(User.class).id(user.getEmail()).now();
    	fromDB.resetPassword();
    	ofy().save().entity(fromDB).now();
    }
     
    /**
	 * Returns the User associated with the given email address.
	 * @author conangammel
	 * @param email
	 * @return User
	 */
    public User getUserByEmail(String email){
    	User fromDB = ofy().load().type(User.class).id(email).now();
    	return fromDB;
    }
    
    /**
	 * Get user from DataStore using his/her accessCode
	 * @author conangammel
	 * @param User
	 * @return User
	 */
    public User getUserByAccessCode(String accessCode) {
    	User fromDB = ofy().load().type(User.class).filter("accessCode", accessCode).first().now();
    	return fromDB;
    }
    
    /**
     * Returns the name of the GroBot the user is connected to.
     * @param email - address of user
     * @return String - name of the GroBot the user is connected to
     */
    public String getBotNameByOwner(String email){
    	User fromDB = ofy().load().type(User.class).id(email).now();
    	return fromDB.getCurrentBotName();
    }

	/**
	 * Deletes the user's Grobots, the user's schedules (which includes lights and water since those are stored too)
	 * and finally the user is deleted. NOT REVERSIBLE
	 * @author conangammel
	 * @param email - address of user to be deleted
	 */
	public void deleteAccount(String email){
		User fromDB = ofy().load().type(User.class).id(email).now();
		
		//delete GroBots
		for(GroBots g: fromDB.getAllBots()){
			ofy().delete().type(GroBots.class).id(g.getId());
		}
		
		//delete schedule and components
		for(Long id: fromDB.getSchedules()){
			Schedule s = ofy().load().type(Schedule.class).id(id).now();
			ofy().delete().type(Water.class).id(s.getWaterId());
			ofy().delete().type(Lights.class).id(s.getLightId());
			ofy().delete().type(Schedule.class).id(id);
		}
		
		//delete the user
		ofy().delete().type(User.class).id(email);
		
		//database cleaned up from all traces of this user
	}


	/**
	 * Returns the schedule associated with the ID parameter passed.
	 * @param id
	 * @return Schedule
	 */
	public Schedule getSchedule(Long id) {
		return ofy().load().type(Schedule.class).id(id).now();
	}

	/**
	 * Add a Schedule to the DataStore. Also saves changes to existing Schedules.
	 * @param sched
	 */
	public void addSchedule(Schedule sched) {
		synchronized(this){
			ofy().save().entity(sched).now();
		}
	}

	/**
	 * Add a GroBot to the DataStore. Also saves changes to existing GroBots.
	 * @param bot
	 */
	public void addGroBot(GroBots bot) {
		ofy().save().entity(bot).now();
	}

	/**
	 * Returns a GroBot given the id.
	 * @param id
	 * @return GroBot
	 */
	public GroBots getGroBot(Long id) {
		return ofy().load().type(GroBots.class).id(id).now();
	}

	/**
	 * Deletes the schedule associated with the id.
	 * @param id
	 */
	public void removeSchedule(Long id) {
		ofy().delete().type(Schedule.class).id(id);
	}
}
