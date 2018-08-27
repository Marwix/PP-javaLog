package com.Nimex.Functions;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

import com.Nimex.Nimex_Form;

public class SMTP // [Done]
{
	static Properties MailProperties;
	static Session MailSession;
	static MimeMessage genMailMessage;
	static String text; 
	
	public void SendMail(String to, String from, String username, String password, String host, String port, String SSL)
	{	
		try
		{
			// Properties
		    MailProperties = System.getProperties();
		    MailProperties.put("mail.smtp.port", port);
		    MailProperties.put("mail.smtp.auth", true);
		    MailProperties.put("mail.smtp.starttls.enable", SSL);
			
		    // Session
		    MailSession = Session.getDefaultInstance(MailProperties);
		    genMailMessage = new MimeMessage(MailSession);
		    genMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		    genMailMessage.setFrom(from);
		    genMailMessage.setSubject("Test Mail - jLog");
		    genMailMessage.setContent("This is a test mail sent through jLog!", "text/html"); 
		    
		    // Send Mail
		    Transport Send = MailSession.getTransport("smtp");
		    Send.connect(host, username, password);
		    do
		    {
		    	Nimex_Form.setText("Please wait.");
		    	Thread.sleep(450);
		    	Nimex_Form.setText("Please wait..");
		    	Thread.sleep(450);
		    	Nimex_Form.setText("Please wait...");
		    	Thread.sleep(450);
		    } while (Send.isConnected() == false);
		    Send.sendMessage(genMailMessage, genMailMessage.getAllRecipients());
		    Send.close();
		    Nimex_Form.setText("Send Test Mail");
		    JOptionPane.showMessageDialog(null, "Sent!");
		}
		catch (Exception er)
		{
			JOptionPane.showMessageDialog(null, "Error - " + er.toString());
			Nimex_Form.setText("Send Test Mail");
		}
		
	}
}
