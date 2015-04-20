package groBot.services;

import groBot.client.GroBot;
import groBot.entity.GroBots;
import groBot.entity.Lights;
import groBot.entity.Schedule;
import groBot.entity.User;
import groBot.entity.Water;

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
		 factory().register(Water.class);
		 factory().register(Lights.class);
		 factory().register(Schedule.class);
		 factory().begin();
	 }
 
	/**
	 * Singleton return.
	 * @return
	 */
	public static Objectify ofy() {
		return ObjectifyService.ofy();
	}
	 
	/**
	 * Singleton return.
	 * @return
	 */
	public static ObjectifyFactory factory() {
		return ObjectifyService.factory();
	}
}