package in.praveenlab.email.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Value(value = "${mail.smtp.host}")
	private String smtpHost;

	@Value(value = "${mail.smtp.port}")
	private String smtpPort;
	
	@Value(value = "${mail.smtp.username}")
	private String smtpUsername;
	
	@Value(value = "${mail.smtp.password}")
	private String smtpPassword;
	
	@Value(value = "${mail.smtp.fromEmail}")
	private String fromEmail;
	
	public boolean sendEmail(String to, String subject, String message) {
		String from = fromEmail;
		//Get the system properties
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", smtpHost);
		properties.put("mail.smtp.port", smtpPort);
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		//Get the session object
		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(smtpUsername, smtpPassword);
			}

		});

		//Compose the message [text, media]
		MimeMessage mime = new MimeMessage(session);
		try {
			session.setDebug(true);
			//from email
			mime.setFrom(from);

			//Recipient
			mime.addRecipient(Message.RecipientType.TO, new InternetAddress(to)); 
			mime.setSubject(subject);
			mime.setText(message);

			Transport.send(mime);

			return true;

		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}


}
