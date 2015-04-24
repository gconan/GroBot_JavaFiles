package groBot.dao;

import groBot.entity.GroBots;
import groBot.entity.Schedule;
import groBot.entity.User;

/**
 * UserDAO is the "middleMan" between code and the DataStore. 
 * All of the INSTANCE methods pull or save Entities to and from Google's DataStore.
 * @author conangammel
 *
 */
public enum UserDAO {  
	INSTANCE;
	RealDAO dao= new RealDAO();
	
	/**
	 * Returns the GroBot the user is currently connected to.
	 * @param email email address of user
	 * @return currently connected GroBot
	 */
	public GroBots getCurrentUserBot(String email){
		return dao.getCurrentUserBot(email);
	}
	
	
	
	/**
	 * Adds user to database. Acts as an overwrite as well (saves updated users).
	 * @param user save this user to the DataStore
	 */
    public void addUser(User user) {  
    	dao.addUser(user);
    }
    
    /**
	 * Deletes the user from the DataStore.
	 * @param user to be deleted
	 */
    public void removeUser(User user) {
    	dao.removeUser(user);
    }
 
    
    /**
	 * Activates the user so he/she can login.
	 * @param user to be activated
	 */
    public void activateUser(User user) {	
    	dao.activateUser(user);
    }
    
    /**
     * Updates user's password in the DataStore.
     * @param user user to change password
     * @param newPassword user's desired new password
     */
    public void setPassword(User user, String newPassword) {
    	dao.setPassword(user, newPassword);
    }
    
    /**
	 * Generates random password and updates user object in database with new password.
	 * @param user user who's password is being reset
	 */
    public void resetPassword(User user) {
    	dao.resetPassword(user);
    }
     
    /**
	 * Returns the User associated with the given email address.
	 * @param email email address of user being retrieved
	 * @return User the user corresponding to the email address provided
	 */
    public User getUserByEmail(String email){
    	return dao.getUserByEmail(email);
    }
    
    /**
	 * Get user from DataStore using his/her accessCode
	 * @param accessCode unique code given to a user
	 * @return User the user linked to the accessCode provided
	 */
    public User getUserByAccessCode(String accessCode) {
    	return dao.getUserByAccessCode(accessCode);
    }
    
    /**
     * Returns the name of the GroBot the user is connected to.
     * @param email address of user
     * @return String name of the GroBot the user is connected to
     */
    public String getBotNameByOwner(String email){
    	return dao.getBotNameByOwner(email);
    }

	/**
	 * Deletes the user's Grobots, the user's schedules (which includes lights and water since those are stored too)
	 * and finally the user is deleted. NOT REVERSIBLE
	 * @param email address of user to be deleted
	 */
	public void deleteAccount(String email){
		dao.deleteAccount(email);
	}


	/**
	 * Returns the schedule associated with the ID parameter passed.
	 * @param id Objectify ID of the schedule to return
	 * @return Schedule schedule linked to the Objectify ID
	 */
	public Schedule getSchedule(Long id) {
		return dao.getSchedule(id);
	}

	/**
	 * Add a Schedule to the DataStore. Also saves changes to existing Schedules.
	 * @param sched schedule to add or overwrite to the DataStore
	 */
	public void addSchedule(Schedule sched) {
		dao.addSchedule(sched);
	}

	/**
	 * Add a GroBot to the DataStore. Also saves changes to existing GroBots.
	 * @param bot GroBot to add or overwrite to the DataStore
	 */
	public void addGroBot(GroBots bot) {
		dao.addGroBot(bot);
	}

	/**
	 * Returns a GroBot given the id.
	 * @param id Objectify ID of GroBot
	 * @return GroBot GroBot linked to the Objectify ID provided
	 */
	public GroBots getGroBot(Long id) {
		return dao.getGroBot(id);
	}

	/**
	 * Deletes the schedule associated with the id.
	 * @param id Objectify ID of Schedule to be removed
	 */
	public void removeSchedule(Long id) {
		dao.removeSchedule(id);
	}
}
