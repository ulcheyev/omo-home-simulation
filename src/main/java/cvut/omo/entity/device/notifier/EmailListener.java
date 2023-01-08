package cvut.omo.entity.device.notifier;

import cvut.omo.app_utils.FileManager;
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
 * Class is used to send email notification.
 */
public class EmailListener implements EventListener {


    @Setter
    private static String email;

    @Setter
    private static boolean wantEMail = false;


    /**
     * Sends email to user.
     *
     * @param message message to send
     * @param sensor  sensor, which alarm went off
     */
    @Override
    public void update(String message, Sensor sensor) throws IOException, MessagingException {
        if (wantEMail == true) {
            final Properties properties = new Properties();
            properties.load(new FileInputStream("mail.properties"));
            Session mailSession = Session.getDefaultInstance(properties);
            MimeMessage msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress("marinaloki123"));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            final String text = "Hello,\n we notify you that it worked in the house: " + sensor.getClass().getSimpleName() + message + "\nrespectfully, your Smart Home";
            msg.setSubject("This is a notification");
            msg.setText(text);

            Transport tr = mailSession.getTransport();
            tr.connect("marinaloki123@gmail.com", "otiqrytddmprpkmq");
            tr.sendMessage(msg, msg.getAllRecipients());
            tr.close();
        } else {
            return;
        }
    }


}
