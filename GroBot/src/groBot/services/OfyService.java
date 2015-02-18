package groBot.services;

import groBot.client.GroBot;
import groBot.entity.GroBots;
import groBot.entity.User;

import java.util.ArrayList;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;
 
/**
 * Objectify service wrapper so we can statically register our persistence classes
 * @author conangammel
 *
 */
public class OfyService {
 
	static {
		ObjectifyService.begin();
		 factory().register(User.class);
		 factory().register(GroBots.class);
		 factory().begin();
	 }
 
	public static Objectify ofy() {
		
	 return ObjectifyService.ofy();
	 }
	 
	public static ObjectifyFactory factory() {
	 return ObjectifyService.factory();
	 }
	
	public static ArrayList<User> convertUserQuerytoArrayList(Query<User> list){
		ArrayList<User> returnThis = new ArrayList<User>();
		for(User u: list){
			returnThis.add(u);
		}
		return returnThis;
	}
	
	public static ArrayList<GroBots> convertMeetingQuerytoArrayList(Query<GroBots> list){
		ArrayList<GroBots> returnThis = new ArrayList<GroBots>();
		for(GroBots u: list){
			returnThis.add(u);
		}
		return returnThis;
	}
}