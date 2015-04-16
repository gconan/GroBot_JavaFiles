package groBot.dao;

import static groBot.services.OfyService.ofy;
import groBot.entity.GroBots;
import groBot.entity.Lights;
import groBot.entity.Schedule;
import groBot.entity.User;
import groBot.entity.Water;

public enum UserDAO {  
	INSTANCE;
	
	
	/**
	 * GET USERS GROBOTS?
	 * get items that belong to a specific user
	 * @param email
	 * @return List<Item>
	 */
	public GroBots getUserBots(String email){
		User user= getUserByEmail(email);
		return user.getGroBot();
	}
	
	
	
	/**
	 * adds user to database
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
	 * removes user from database
	 * @author conangammel
	 * @param User
	 * @return User
	 */
    public User removeUser(User user) {
    	ofy().delete().entities(user);
    	return user;
    }
 
    
    /**
	 * activates the status of user in database
	 * @author conangammel
	 * @param User
	 * @return User
	 */
    public User activateUser(User user) {	
    	User fromDB = ofy().load().type(User.class).id(user.getEmail()).now();
    	fromDB.activate();
    	ofy().save().entity(fromDB).now();
    	return fromDB;
    }
    
    /**
	 * updates user object in database with given password
	 * @param String
	 */
    public void setPassword(User user, String newPassword) {
    	User fromDB = ofy().load().type(User.class).id(user.getEmail()).now();
    	fromDB.setPassword(newPassword);
    	ofy().save().entity(fromDB).now();
    }
    
    /**
	 * generates random password and updates user object in database with new password
	 * @param User
	 */
    public void resetPassword(User user) {
    	User fromDB = ofy().load().type(User.class).id(user.getEmail()).now();
    	fromDB.resetPassword();
    	ofy().save().entity(fromDB).now();
    }
     
    /**
	 * get user from database by email
	 * @author conangammel
	 * @param User
	 * @return User
	 */
    public User getUserByEmail(String email){
    	User fromDB = ofy().load().type(User.class).id(email).now();
    	return fromDB;
    }
    
    /**
	 * get user from database by accessCode
	 * @author conangammel
	 * @param User
	 * @return User
	 */
    public User getUserByAccessCode(String accessCode) {
    	User fromDB = ofy().load().type(User.class).filter("accessCode", accessCode).first().now();
    	return fromDB;
    }
    
    public String getBotNameByOwner(String email){
    	User fromDB = ofy().load().type(User.class).id(email).now();
    	return fromDB.getCurrentBotName();
    }

	/**
	 * Deletes the user's Grobots, the user's schedules (which includes lights and water since those are stored too)
	 * and finally the user is deleted. NOT REVERSIBLE
	 * @author conangammel
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



	public Schedule getSchedule(Long long1) {//TODO, make work
		return ofy().load().type(Schedule.class).id(long1).now();
	}



	public void addSchedule(Schedule sched) {
		synchronized(this){
			ofy().save().entity(sched).now();
		}
		
	}



	public void addGroBot(GroBots bot) {
		ofy().save().entity(bot).now();
	}



	public GroBots getGroBot(int hashCode) {
		return ofy().load().type(GroBots.class).id(hashCode).now();
	}



	public void removeSchedule(Long id) {
		ofy().delete().type(Schedule.class).id(id);
	}
}
