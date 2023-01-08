package cvut.omo.entity.device;

import cvut.omo.entity.device.notifier.EventListener;

import javax.mail.MessagingException;
import java.io.IOException;


/**
 * Class represents water leak sensor device.
 */
public class WaterLeakSensor extends Sensor {

    public String message = "Water leak, be careful!";

    public WaterLeakSensor(double lifeTime) {
        super(lifeTime);
    }

    @Override
    public void alert() throws MessagingException, IOException {
        for (EventListener listner : listenerList) {
            listner.update(message, this);
        }
    }

}