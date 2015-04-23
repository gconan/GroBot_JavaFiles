package groBot.email;

import groBot.entity.User;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Email Class that sends the verification email, reset password email, and any generic email.
 * @author conangammel
 *
 */
public class Email {
	

	/**
	 * Sends the user an email with a unique link to verify that he/she is human.
	 * @param user User to send email to
	 * @throws MessagingException
	 */
	public void emailVerification(User user) throws MessagingException {
		String body = "Please, click on link below to complete your registration.\n\nhttp://the-grobot.appspot.com/registration?access=" + user.getAccess_code();
		String subject = "Verify Registration";
		try {
			send(user.getEmail(), body, subject);
		} catch (MessagingException m) {
			throw new MessagingException();
		}
	}
	
	/**
	 * Sends the user an email with a temporary password.
	 * @param user User to send email to
	 * @param pass new password that will allow the user to log in after a password reset
	 * @throws MessagingException
	 */
	public void forgotPassword(User user, String pass) throws MessagingException {
		String body = "Below is your password.\n\nPassword: " + pass;
		String subject = "Reset Password";
		try {
			send(user.getEmail(), body, subject);
		} catch (MessagingException m) {
			throw new MessagingException();
		}
	}
	

	/**
	 * Sends an email.
	 * @param emailAddress email address to send an email to
	 * @param body body of the email
	 * @param subject subject of the email
	 * @throws MessagingException
	 */
	public void send(String emailAddress, String body, String subject) throws MessagingException {
		MimeMessage outMessage = new MimeMessage(Session.getDefaultInstance(new Properties(), null));
		outMessage.setFrom(new InternetAddress("donotreply@the-grobot.appspotmail.com"));
		outMessage.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(emailAddress));
		outMessage.setSubject(subject);
		outMessage.setText(body);
		Transport.send(outMessage);
	}
}
