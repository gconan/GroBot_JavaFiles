package groBot.dao;

import groBot.entity.Item;
import groBot.entity.Meeting;
import groBot.entity.Posting;
import groBot.entity.User;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.cmd.Query;

import static groBot.services.OfyService.ofy;

public enum UserDAO {  
	INSTANCE;
	
	/**
	 * MARK FOR DESTRUCTION
	 * get the whole list of users
	 * @author conangammel
	 * @return List<User>
	 */
	public List<User> getAllUsers(){
		List<User> list = new ArrayList<User>();
		Query<User> userlist = ofy().load().type(User.class);
		for(User users: userlist){
			list.add(users);
		}
		return list;
	}
	
	/**
	 * MARK FOR DESTRUCTION
	 * get the whole list of users
	 * @author conangammel
	 * @return List<User>
	 */
	public List<Item> getAllItems(){
		List<Item> list = new ArrayList<Item>();
		Query<Item> itemList = ofy().load().type(Item.class);
		for(Item item: itemList){
			list.add(item);
		}
		return list;
	}
	
	/**
	 * MARK FOR DESTRUCTION
	 * get whole list of meetings
	 * @author Michael
	 * @return List<Meeting>
	 */
	public List<Meeting> getAllMeetings(){
		List<Meeting> list = new ArrayList<Meeting>();
		Query<Meeting> meetingList = ofy().load().type(Meeting.class);
		for(Meeting meeting: meetingList){
			list.add(meeting);
		}
		return list;
	}
	
	/**
	 * GET USERS GROBOTS?
	 * get items that belong to a specific user
	 * @author Michael
	 * @param email
	 * @return List<Item>
	 */
	public List<Item> getUserItems(String email){
		List<Item> list = new ArrayList<Item>();
		Query<Item> itemList = ofy().load().type(Item.class).filter("owner", email);
		for(Item item: itemList){
			list.add(item);
		}
		return list;
	}
	
	/**
	 * MARK FOR DESTRUCTION
	 * @author conangammel
	 */
	public List<User> getAllSubscribedUsers(){
		List<User> list = new ArrayList<User>();
		Query<User> itemList = ofy().load().type(User.class).filter("subscribed", true);
		for(User user: itemList){
			list.add(user);
		}
		return list;
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
	 * MARK FOR DESTRUCTION
     * @author conangammel
     * @param item
     */
	public void addItemToDB(Item item) {
		ofy().save().entity(item).now();
		item.addItemToOwner();
	}
	
	
	
	/**
	 * MARK FOR DESTRUCTION
	 * save new meeting to DB
	 * @author conangammel
	 * @param meet
	 */
	public void addMeetingToDB(Meeting meet) {
		ofy().save().entity(meet).now();
		meet.addMeetingToOwner();
	}
	
	/**
	 * MARK FOR DESTRUCTION
	 * @author conangammel
	 * save existing meeting to DB
	 */
	public void saveMeeting(Meeting meet){
		ofy().save().entity(meet).now();
	}
	
	/**
	 * MARK FOR DESTRUCTION
	 * @author conangammel
	 */
	public Meeting getMeetingById(Long id){
		return ofy().load().type(Meeting.class).id(id).now();
	}
	/**
	 * REGCONFIGURE TO DELETE GROBOT , CHANG GROBOT OWNER
	 * @author conangammel
	 */
	public void deleteAccount(String email){
		User fromDB = ofy().load().type(User.class).id(email).now();
		ArrayList<Long> items = fromDB.getItems();
		ArrayList<Long> meetings = fromDB.getMeetings();
		
		for(Long id: items){
			ofy().delete().type(Posting.class).id(id);
		}
		for(Long id: meetings){
			ofy().delete().type(Posting.class).id(id);
		}
		ofy().delete().type(User.class).id(email);
	}
	/**
	 * MARK FOR DESTRUCTION
	 * @author conangammel
	 * @param email
	 * @return
	 */
	public boolean unsubscribeUser(String email) {
		try{
			User user = ofy().load().type(User.class).id(email).now();
			user.setSub(false);
			ofy().save().entity(user).now();
			return true;
		}catch(Exception e){
			//user does not exist
			return false;
		}
		
	}
	
	/**
	 * MARK FOR DESTRUCTION
	 * @author conangammel
	 * @param email
	 * @return
	 */
	public boolean subscribeUser(String email) {
		try{
			User user = ofy().load().type(User.class).id(email).now();
			user.setSub(true);
			ofy().save().entity(user).now();
			return true;
		}catch(Exception e){
			//user does not exist
			return false;
		}
		
	}
}
