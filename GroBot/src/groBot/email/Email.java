package groBot.email;

import groBot.entity.User;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
	

	 /* rename link to correct app ID*/
	public void emailVerification(User user) throws MessagingException {
		String body = "Please, click on link below to complete your registration.\n\nhttp://the-grobot.appspot.com/registration?access=" + user.getAccess_code();
		String subject = "Verify Registration";
		try {
			send(user.getEmail(), body, subject);
		} catch (MessagingException m) {
			throw new MessagingException();
		}
	}
	
	public void forgotPassword(User user, String pass) throws MessagingException {
		String body = "Below is your password.\n\nPassword: " + pass;
		String subject = "Reset Password";
		try {
			send(user.getEmail(), body, subject);
		} catch (MessagingException m) {
			throw new MessagingException();
		}
	}
	

	/*change reply email*/
	public void send(String email, String body, String subject) throws MessagingException {
		MimeMessage outMessage = new MimeMessage(Session.getDefaultInstance(new Properties(), null));
		outMessage.setFrom(new InternetAddress("donotreply@the-grobot.appspotmail.com"));
		outMessage.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(email));
		outMessage.setSubject(subject);
		outMessage.setText(body);
		Transport.send(outMessage);
	}
}
