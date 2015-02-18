package groBot.dao;

import groBot.entity.GroBots;
import groBot.entity.User;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.cmd.Query;

import static groBot.services.OfyService.ofy;

public enum UserDAO {  
	INSTANCE;
	
	
	/**
	 * GET USERS GROBOTS?
	 * get items that belong to a specific user
	 * @author Michael
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

	/**
	 * REGCONFIGURE TO DELETE GROBOT , CHANG GROBOT OWNER
	 * @author conangammel
	 */
	public void deleteAccount(String email){
		User fromDB = ofy().load().type(User.class).id(email).now();
		
		ofy().delete().type(User.class).id(email);
	}
}
