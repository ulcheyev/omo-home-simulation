package cvut.omo.device;
import cvut.omo.device.notifier.EventListener;

import javax.mail.MessagingException;
import java.io.IOException;


public class WaterLeakSensor extends Sensor {

    public String message = "Water leak, be careful!";

    public WaterLeakSensor(double lifeTime) {
        super(lifeTime);
    }

    @Override
    public void addListener(EventListener listner) {
        listenerList.add(listner);
    }
    @Override
    public void removeListener(EventListener listner) {
        listenerList.remove(listner);
    }

    @Override
    public void alert() throws IOException, MessagingException {
        for (EventListener listner :listenerList){
            listner.update(message, this);
        }
    }

}