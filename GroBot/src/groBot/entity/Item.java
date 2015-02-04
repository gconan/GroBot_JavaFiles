package groBot.entity;

import static groBot.services.OfyService.ofy;
import groBot.util.Keyword;
import groBot.util.TextParser;

import java.io.IOException;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.Blob;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;


/**
 * Any item for sale/trade on UTBazaar
 * @author conangammel
 *
 */
@Entity
//MUST HAVE THESE ANNOTATIONS, DO NOT EDIT THEM WITHOUT CONAN'S APPROVAL...thank you :)
public class Item extends Posting{

	private static final long serialVersionUID = 1L;

	/**Price of the item*/
	@Index
	private double price;
	
	/**String name of the owner, must use this to query the dataStore for USER*/
	@Index
	private String owner;
	
	/**Time in milliseconds when created since January 1 1970 12am*/
	private Long date;
	
	/**Picture of the item*/
	@Index
	private String image;
	
	/**
	 * Constructor
	 * @author conangammel
	 * @param name
	 * @param sPrice - string representation of double "$XX.XX" or "YY.YY"; must have dollars and cents; money sign is fine
	 * @param desc
	 * @param owner
	 */
	public Item(String name, String sPrice, String desc, String owner, String image){
		this.name = name;
		this.price = getPriceFromString(sPrice);
		this.description = desc;
		this.owner = owner;
		this.date = System.currentTimeMillis();
		this.keywords = this.populateKeywordList();
		this.setImage(image);
		this.ID = Math.abs(description.hashCode() * owner.hashCode()* (long) Math.pow(31,5));
	}
	
	/**
	 * we have to have a default constructor for the sake of objectify
	 * @author conangammel
	 */
	@SuppressWarnings("unused")
	private Item(){
		
	}

	/**
	 * add the item's id to the owner's list of sale items
	 * @author conangammel
	 */
	public void addItemToOwner() {
		User fromDB = ofy().load().type(User.class).id(this.owner).now();
    	fromDB.addItem(this.ID);
    	ofy().save().entity(fromDB).now();
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
	 * 
	 */
	@Override
	public ArrayList<String> populateKeywordList(){
		ArrayList<String> keys = super.populateKeywordList();
		return keys;
	}
	
	/**
	 * @author conangammel
	 * Retrieves the image of the object
	 * @return Blob
	 */
	public String getImageKey() {
		return image;
	}

	/**
	 * check for any errors in the string
	 * change string into price with two decimal places
	 * return the price from string
	 * @author conangammel
	 * @param price2
	 * @return
	 */
	private double getPriceFromString(String priceString) {
		if(priceString != null){
			priceString = priceString.replace("$","");
			priceString = priceString.replace(" ","");
			priceString = priceString.replaceAll("[a-zA-Z]", ""); //regex
			try {
				return Double.parseDouble(priceString);
			} catch (Exception e) {
				return 0;
			}
		}
		return 0;
	}


	
	/**
	 * removes the item from the owners list and deletes the item from the DB
	 * @author conangammel
	 */
	public void itemSold(){
		User user = ofy().load().type(User.class).id(this.getOwner()).now();
		user.removeItem(this);
		ofy().save().entities(user).now();
		ofy().delete().entities(this).now();
	}
	
	/**
	 * @author michael
	 * needs math to return string
	 * @return "MM/DD/YYYY"
	 */
	public String getDate(){
		return this.date.toString(); //TODO add math to return correctly
	}
	
	/**
	 * @author conangammel
	 * */
	public String getName() {
		return this.name;
	}
	
	/**
	 * @author conangammel
	 * */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * @author conangammel
	 * */
	public String getOwner() {
		return this.owner;
	}
	
	/**
	 * @author conangammel
	 * */
	public ArrayList<String> getKeywords(){
		return this.keywords;
	}
	
	/**
	 * @author conangammel
	 * */
	public String getPrice() {
		DecimalFormat df = new DecimalFormat("0.00"); 
		return df.format(this.price);
	}
	
	/**
	 * @author conangammel
	 * */
	public Long getId() {
		return this.ID;
	}
	
}
