package cvut.omo.device.notifier;
import cvut.omo.device.Sensor;
import lombok.Setter;

import cvut.omo.device.Sensor;
import lombok.Setter;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 */
public class EmailListener implements EventListener{

    @Setter
    private static String email;
    /**
     * @param message
     * @param sensor
     * @throws IOException
     */


    @Override
    public void update(String message, Sensor sensor) throws IOException, MessagingException {
//        final Properties properties = new Properties();
//        properties.load(new FileInputStream("mail.properties"));
//        Session mailSession = Session.getDefaultInstance(properties);
//        MimeMessage msg = new MimeMessage(mailSession);
//        msg.setFrom(new InternetAddress("marinaloki123"));
//        msg.addRecipient(Message.RecipientType.TO, new InternetAddress("mrg.lupenko@gmail.com"));
//        final String text =
//                "Hello,\n we notify you that it worked in the house: "
//                        + sensor.getClass().getSimpleName() + message + "\nrespectfully, your Smart Home";
//        msg.setSubject("This is a notification");
//        msg.setText(text);
//
//        Transport tr = mailSession.getTransport();
//        tr.connect("marinaloki123@gmail.com", "otiqrytddmprpkmq");
//        tr.sendMessage(msg, msg.getAllRecipients());
//        tr.close();
    }

}
