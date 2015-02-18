/**
 * emails daily updates to users
 * @author Ken
 */

package groBot;

import groBot.dao.UserDAO;
import groBot.entity.User;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class CronServlet extends HttpServlet {
	private static final Logger _logger = Logger.getLogger(CronServlet.class.getName());
	Properties props = new Properties();
	Session session = Session.getDefaultInstance(props, null);
	MimeMessage outMessage = new MimeMessage(session);
    
	String temp;
	int count;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		/*try {
			_logger.info("Cron Job has been executed");
			
			List<User> user = UserDAO.INSTANCE.getAllSubscribedUsers();	//user list
			for(User u: user){	//iterate through the user list
				temp = "";	//reset message
				count = 0;	//reset count

				List<Item> item = UserDAO.INSTANCE.getAllItems();	//recreated every time so that it can be tailored to each user
				count = item.size();
				for(int i = 1; i<6; i++){
					temp = temp+"Name: "+item.get(count-i).getName()+"\n"; 
			        temp = temp+"Price: "+item.get(count-i).getPrice()+"\n";
			        temp = temp+"Description: "+item.get(count-i).getDescription()+"\n";
			        temp = temp+"Owner: "+item.get(count-i).getOwner()+"\n";			        
			        temp = temp+"\n";
				}			    
			    
				//temp = temp+"<input type=\"hidden\" name=\"email\" value=\""+u.getEmail()+"\"/> If you would like to unsubcribe to the Daily Updates click <a href=\"www.longhornbazar.appspot.com/unsubscribe\">here</a>";				
				outMessage.setFrom(new InternetAddress("admin@michaellonghornbazaartest.appspotmail.com"));
				outMessage.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(u.getEmail()));	//user email
				outMessage.setSubject("Daily Update");
			    outMessage.setText(temp);	
			    //outMessage.setText(temp, "UTF-8", "html");	
			    Transport.send(outMessage);
			}
		}
		catch (Exception ex) {
		}*/
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
			doGet(req, resp);			
	}
}