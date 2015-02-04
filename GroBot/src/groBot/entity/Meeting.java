package groBot.entity;

import groBot.services.OfyService;
import groBot.util.Keyword;
import groBot.util.TextParser;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.cmd.Query;

import static groBot.services.OfyService.ofy;

/**
 * 
 * @author Michael
 *
 */
@Entity
public class Meeting extends Posting {
	
	private static final long serialVersionUID = 1L;
	
	@Index
	private String location;
	
	@Index
	private String organizer;
	
	private ArrayList<String> attendees;
	
	private double duration;
	
	@Index
	private final long milli;
	
	private int day;
	
	private int month;
	
	private int hour;
	private int hourend;
	
	private int minute;
	private int minuteend;
	
	private int year;
	
	private String amOrPm;
	private String amOrPmEnd;
	
	private String image;
	//private Calendar time;
	
	/**
	 * *****Conan: changed all parameters to string for HttpsResponse.getParameter; 
	 * added ID and hash function;
	 * added Objectify annotations*****
	 * @author conangammel
	 * @param event
	 * @param loc
	 * @param desc
	 * @param organizer
	 * @param day
	 * @param month
	 * @param hour
	 * @param minute
	 * @param year
	 * @param dur
	 * @param amOrPm
	 */
	public Meeting(String event, 
					String location, 
					String desc, 
					String organizer, 
					String day, 
					String month, 
					String hour, 
					String minute, 
					String year, 
					String amOrPm,
					String hourend,
					String minuteend,
					String amOrPmEnd,
					String imageKey){
		
		this.name = event;
		this.location = location;
		this.description = desc;
		this.organizer = organizer;
		this.day = getIntFromString(day);
		this.month = getIntFromString(month);
		this.hour = getIntFromString(hour);
		this.minute = getIntFromString(minute);
		this.year = getIntFromString(year);
		//this.duration = getDurationFromString(dur);
		this.milli = System.currentTimeMillis();
		this.ID = Math.abs(this.milli*this.description.hashCode()*(long)Math.pow(31,  5));
		//this.time.set(this.year, this.month, this.day, this.hour, this.minute);
		this.keywords = this.populateKeywordList();
		this.amOrPm = amOrPm;
		this.hourend = getIntFromString(hourend);
		this.minuteend = getIntFromString(minuteend);
		this.amOrPmEnd = amOrPmEnd;
		this.attendees = new ArrayList<String>();
		this.attendees.add(this.organizer);
		setImage(imageKey);
	}
	
	/**
	 * @author conangammel
	 * @param num
	 * @return
	 */
	private int getIntFromString(String num){
		try {
			return Integer.parseInt(num);
		} catch (Exception e) {
			return 0;
		}
		
	}
	
	/**
	 * 
	 * @author conangammel
	 * @param dur
	 * @return
	 */
	private double getDurationFromString(String dur) {
		try {
			return Double.parseDouble(dur);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * we have to have a default constructor for the sake of objectify
	 * @author conangammel
	 */
	@SuppressWarnings("unused")
	private Meeting(){
		this.milli = System.currentTimeMillis();
	}
	
	
	
	public ArrayList<String> getKeywords(){
		return this.keywords;
	}
	
	/**
	 * deletes methods that have meeting times older than one day
	 * @author conangammel
	 */
	public void flushOldMettings(){
		Query<Meeting> list = ofy().load().type(Meeting.class).filterKey("<", (System.currentTimeMillis()-86400000));
		ArrayList<Meeting> meets = OfyService.convertMeetingQuerytoArrayList(list);
		
		for(Meeting m: meets){
			ofy().delete().entities(m).now();
		}
	}
	
	/**
	 * @author conangammel
	 * 
	 */
	@Override
	public ArrayList<String> populateKeywordList(){
		ArrayList<String> keys = super.populateKeywordList();;
		this.keywords.add(month+"/"+day+"/"+year);
		this.keywords.add(hour+":"+minute);
		
		//add location information
		String[] loc = this.location.split(" ");
		for(String s: loc){
			keys.add(s);
		}
		keys.add(this.location);
		return keys;
	}
	
	/**
	 * @author conangammel
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * @author Michael
	 */
	public void addMeetingToOwner() {
		User fromDB = ofy().load().type(User.class).id(this.organizer).now();
    	fromDB.addMeeting(this.ID);
    	ofy().save().entity(fromDB).now();
	}
	
	/**
	 * @author Michael
	 */
	public String getOrganizer(){
		return this.organizer;
	}
	
	/**
	 * @author Michael
	 */
	public String getLocation(){
		return this.location;
	}
	
	/**
	 * @author Michael
	 */
	public String getDescription(){
		return this.description;
	}
	
	/**
	 * @author Michael
	 */
	public String getDate(){
		return ""+this.day;
	}
	
	/**
	 * @author Michael
	 */
	public String getTime(){
		return ""+this.hour;
	}
	/**
	 * @author Michael
	 */
	public String getTimeEnd(){
		return ""+this.hourend;
	}
	
	/**
	 * @author conangammel
	 * Sets the image of the item
	 * @param image
	 */
	public void setImage(String image) {
		if(image==null){
			this.image = "ut.jpg";
		}else{
			this.image = image;
		}
	}
	/**
	 * @author conangammel
	 */
	public String getSize(){
		return ""+this.attendees.size();
	}
	
	
	/**
	 * @author Michael
	 */
	public int getMinutes(){
		return this.minute;
	}
	/**
	 * @author Michael
	 */
	public int getMinutesEnd(){
		return this.minuteend;
	}
	
	/**
	 * @author Michael
	 */
	public String getAmOrPm(){
		return this.amOrPm;
	}
	/**
	 * @author Michael
	 */
	public String getAmOrPmEnd(){
		return this.amOrPmEnd;
	}
	
	/**
	 * Retrieves the image of the object
	 * @author conangammel
	 * @return Blob
	 */
	public String getImageKey() {
		return image;
	}
	
	/**
	 * @author Michael
	 */
	public String getMonth(){
		return ""+this.month;
	}
	
	/**
	 * @author Michael
	 */
	public String getYear(){
		return ""+this.year;
	}
	
	/**
	 * @author Michael
	 */
	public String getDuration(){
		return ""+this.duration;
	}
	
	/**
	 * @author conangammel
	 */
	public ArrayList<String> getVIPList(){
		return this.attendees;
	}
	
	/**
	 * @author conangammel
	 */
	public void addVIPtoList(String emailOfAttendee){
		if(this.attendees.contains(emailOfAttendee)){
			//do nothing, already attending
		}else{
			this.attendees.add(emailOfAttendee);
		}
	}
	
	/**
	 * @author conangammel
	 */
	public void notGoingAnymore(String email){
		try {
			this.attendees.remove(email);
		} catch (Exception e) {
			//not going already, dont worry
		}
	}
	
}
