package cvut.omo.entity.device;

import cvut.omo.entity.device.notifier.EventListener;

import javax.mail.MessagingException;
import java.io.IOException;

/**
 * Class represents fire sensor device.
 */
public class FireSensor extends Sensor {

    public String message = "Everyone needs to get out of the house - the house is on fire!";

    public FireSensor(double lifeTime) {
        super(lifeTime);
    }


    @Override
    public void alert() throws MessagingException, IOException {
        for (EventListener listener : listenerList) {
            listener.update(message, this);
        }
    }

}
