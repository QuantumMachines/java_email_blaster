import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.internet.MimeBodyPart;


import java.util.Properties;


import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.activation.*;

// there isnt any interface or abstract class here because static methods cant be overriden

public class SendEmailTLS{

    public static int sendMail(String username , String password  , String to  , String areaMsg , String subject ) {

        //final String username = "itsocmultimediauniversity@gmail.com";
        //final String password = "iTsOc123";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(to)
            );
            message.setSubject(subject);
            message.setText(areaMsg);

            Transport.send(message);

            System.out.println("Done");

            return 0; // successfully sent mail , open  a JDialog

        } catch (MessagingException e) {
            e.printStackTrace();
            return 1; // error in sending mail

        }
    }


    public static int sendMailAttachment(String username , String password  , String to  , String areaMsg , String subject , String filename ) {

        //final String username = "itsocmultimediauniversity@gmail.com";
        //final String password = "iTsOc123";

        Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(to)
            );
            message.setSubject(subject);
            
            // body of email
            BodyPart messageBodyPart1 = new MimeBodyPart(); // upcasting to bodypart
            messageBodyPart1.setText(areaMsg); 


            // attachment of email
            MimeBodyPart messageBodyPart2 = new MimeBodyPart();

            DataSource source = new FileDataSource(filename);

            messageBodyPart2.setDataHandler(new DataHandler(source));
            messageBodyPart2.setFileName(filename);

            // combine them to one body

            Multipart multipart = new MimeMultipart();  
            multipart.addBodyPart(messageBodyPart1);  
            multipart.addBodyPart(messageBodyPart2); 

            // setting it into the message 

            message.setContent(multipart);   
        

            Transport.send(message);

            System.out.println("Done");

            return 0; // successfully sent mail , open  a JDialog

        } catch (MessagingException e) {
            e.printStackTrace();
            return 1; // error in sending mail

        }
    }

    public static void main(String[] args ){

    }

}

