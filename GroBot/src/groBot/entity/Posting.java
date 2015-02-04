package groBot.entity;

import groBot.util.Keyword;
import groBot.util.TextParser;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import static groBot.services.OfyService.ofy;

/**
 * figured there were enough commonalities between meeting 
 * and item, that there should be a super class
 * *****NEED TO TEST WITH DATASTORE******* - working
 * @author conangammel
 *
 */
public abstract class Posting implements Serializable {
	
	/**better than nothing for now since the other implementation isnt working
	 * @author conangammel
	 * */
	private final String[] listOfCrapWords = {"a", "the", "to", "this", "is", "in", "I", "like","that","can", 
												"all", "there","are","for", "had", "got", "one", "and", "its", 
												"with", "following"};
	
	/**unique hash code for each item, saves space in DataStore, easier look-up*/
	@Id
	protected Long ID;
	
	/**name of the item or meeting*/
	@Index
	protected String name;
	
	/**keywords identifying search terms*/
	@Index
	protected ArrayList<String> keywords;
	
	/**paragraph description*/
	protected String description;
	
	/**
	 * there might be an API for finding keywords in body of text, Namaz?
	 * -----Namaz's keyword implementation didnt work properly, 
	 * -----Conan implemented this simple keyword generator
	 * -----not the best, but it works for this purpose
	 * @author conangammel
	 * @return arraylist of string keywords
	 */
	public ArrayList<String> populateKeywordList(){
		ArrayList<String> results = new ArrayList<String>();
		
		//get keywords from the title
		Scanner scan = new Scanner(this.name);
		try{
			while(scan.hasNext()){
				String word = scan.next();
				results.add(word.toLowerCase()); //all words in the title are important
			}
		}catch(Exception e){
			//scanner issue, leave the keywords alone from here, probably the user's fault
		}finally{
			scan.close();
		}
		
		//Get Keywords from the description
		scan = new Scanner(this.description);
		try{
			while(scan.hasNext()){
				String word = scan.next();
				if(isKeyword(word)){
					results.add(word.toLowerCase());
				}
			}
		}catch(Exception e){
			//scanner issue, leave the keywords alone from here, probably the user's fault
		}finally{
			scan.close();
		}
		return results;
	}
	
	/**
	 * checks the running list of crap words in a description and returns false if the word 
	 * given is one of the crap and returns true if the word is a keyword
	 * O(1)
	 * @author conangammel
	 * @param word
	 * @return
	 */
	private boolean isKeyword(String word) {
		for(String s: listOfCrapWords){
			if(s.equals(word) || word.length()<=2){
				return false;
			}
		}
		return true;
	}

	public Posting loadPostingFromDB(Long id){
		return ofy().load().type(Posting.class).id(id).now();
	}
	
	public String getIdString(){
		return this.ID.toString();
	}
	
	public Long getIdLong(){
		return this.ID;
	}
	

}
