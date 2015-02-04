/**
 * @author Ken
 */
package groBot;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class EmailHandlerServlet  extends HttpServlet {
	public static final Logger _log = Logger.getLogger(EmailHandlerServlet.class.getName());

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		try {
			MimeMessage message = new MimeMessage(session, req.getInputStream());
			Address[] fromAddresses = message.getFrom();
			String studentID  = message.getSubject();

			//Send out Email
			MimeMessage outMessage = new MimeMessage(session);
			outMessage.setFrom(new InternetAddress("admin@longhornbazaarblog.appspotmail.com"));
			outMessage.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(fromAddresses[0].toString()));
			outMessage.setSubject("Subject");
			outMessage.setText("Text");
			Transport.send(outMessage);
		}
		catch (MessagingException e) { 
			_log.info("ERROR: Could not send out Email Results response : " + e.getMessage());
		}
	}
}