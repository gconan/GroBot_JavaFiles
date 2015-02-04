package groBot.entity;

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
	
	private String imageKey;
	
	@Id 
	private String email;
	
	private boolean status;
	
	private ArrayList<Long> itemsForSale = new ArrayList<Long>();
	
	private ArrayList<Long> hostedMeetings = new ArrayList<Long>();
	
	@Index
	private boolean subscribed;
	
	/**
	 * constructs a user object and his/her access code through HashCode
	 * @author conangammel
	 * @param password
	 * @param email
	 */
	public User(String password, String email) {
		this.status = false;
		this.password = password;
		this.email = email;
		long hash = Math.abs(email.hashCode() * (long) Math.pow(31,5));	
		StringBuilder code = new StringBuilder();	                         
		while (hash > 0) {
			code.append((char)('a' + hash%27));
			hash/= 27;
		}
		this.accessCode = code.toString();
		this.subscribed = true;
		this.imageKey = "ut.jpg";	//default until they upload a new one
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
	
	/**
	 * return registration status of the user
	 * @author namazgurbanov
	 * @return boolean
	 */
	public boolean getStatus() {
		return status;
	}
	
	public boolean getSubscribed(){
		return this.subscribed;
	}

	/**
	 * Set user's status to true (they are allowed to log-in)
	 * @author conangammel
	 */
	public void activate() {
		this.status = true;
	}
	
	/**
	 * updates user object with given password
	 * @author namazgurbanov
	 * @param String
	 */
    public void setPassword(String newPassword) {
    	this.password = newPassword;
    }
	
    /**
	 * generates random password and updates user object with new password
	 * @author namazgurbanov
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

	public void addItem(Long postingID) {
		this.itemsForSale.add(postingID);
	}
	
	public void addMeeting(long meeting) {
		this.hostedMeetings.add(meeting);
	}
	
	public boolean isSameUser(User user) {
		if(user.email.equals(this.email) && user.password.equals(this.password)) {
			return true;
		}
		return false;
	}

	/**
	 * after an item is sold, the item must be removed from the user's list of items
	 * we are counting on the compiler's optimization and the built in list functions
	 * for time complexity, but at worst we are looking at O(n) ~ O(1) for most Users
	 * @author conangammel
	 * @param item
	 */
	public void removeItem(Item item) {
		this.itemsForSale.remove(item.getId());
	}
	
	public void setSub(boolean subbed){
		this.subscribed=subbed;
	}

	public void addImageKey(String key) {
		this.imageKey = key;
		
	}
	
	public String getImageKey(){
		return this.imageKey;
	}

	public ArrayList<Long> getItems() {
		return this.itemsForSale;
	}

	public ArrayList<Long> getMeetings() {
		return this.hostedMeetings;
	}
}
