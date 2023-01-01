package cvut.omo.entity.device.notifier;
import cvut.omo.entity.device.Sensor;
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

    private static int counter = 0;
    @Setter
    private static boolean wantEMail = false;


    /**
     * Sends email to user
     * @param message message to send
     * @param sensor sensor, which alarm went off
     */
    @Override
    public void update(String message, Sensor sensor) {
        if(wantEMail == true){
//            final Properties properties = new Properties();
//            properties.load(new FileInputStream("mail.properties"));
//            Session mailSession = Session.getDefaultInstance(properties);
//            MimeMessage msg = new MimeMessage(mailSession);
//            msg.setFrom(new InternetAddress("marinaloki123"));
//            msg.addRecipient(Message.RecipientType.TO, new InternetAddress("mrg.lupenko@gmail.com"));
//            final String text =
//                    "Hello,\n we notify you that it worked in the house: "
//                            + sensor.getClass().getSimpleName() + message + "\nrespectfully, your Smart Home";
//            msg.setSubject("This is a notification");
//            msg.setText(text);
//
//            Transport tr = mailSession.getTransport();
//            tr.connect("marinaloki123@gmail.com", "otiqrytddmprpkmq");
//            tr.sendMessage(msg, msg.getAllRecipients());
//            tr.close();

            System.out.println(counter++ + "XEXE");
        }
        else{
            return;
        }
    }


}
