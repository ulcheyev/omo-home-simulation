package cvut.omo.entity.device.notifier;
import cvut.omo.entity.device.Sensor;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface EventListener {

    /**
     * Method notifies the user about events related to {@link Sensor} alarms
     * @param message message to send
     * @param sensor sensor, which alarm went off
     */
    void update(String message, Sensor sensor) throws IOException, MessagingException;
}
