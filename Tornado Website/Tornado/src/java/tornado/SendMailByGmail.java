import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

class Mailer{
	public static void send(String from,String password,String to,String sub,String msg){
		//get properties object
		Properties props=new Properties();
		
		props.put("mail.smtp.host", "smtp.gmail.com");    
                props.put("mail.smtp.socketFactory.port", "465");    
                props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");    
                props.put("mail.smtp.auth", "true");    
                props.put("mail.smtp.port", "465");   

		
		//get Session 
		
		 Session session = Session.getDefaultInstance(props,    
           new javax.mail.Authenticator() {    
           @Override
           protected PasswordAuthentication getPasswordAuthentication() {    
           return new PasswordAuthentication(from,password);  
           }    
          });   
		
		
		//compose message
		
		try{
			MimeMessage message=new MimeMessage(session);
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
			message.setSubject(sub);
			message.setText(msg);
			

			//send message
			Transport.send(message);
			System.out.println("messsage sent Successful");
		
		}catch(MessagingException e){
			e.printStackTrace();
		}
	}
}

class SendMailByGmail{
	public static void main(String args[]){
		Mailer.send("dulam.pradeep1218@gmail.com","D.prabhu1317@gmail.com","dulam.pradeep1218@gmail.com","hai pradeep","hello this is message of 17/1/2020");
	}
}