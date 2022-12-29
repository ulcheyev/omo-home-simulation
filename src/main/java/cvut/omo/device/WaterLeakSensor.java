package cvut.omo.device;
import cvut.omo.device.notifier.EventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class WaterLeakSensor extends Sensor {

    List<EventListener> listenerList = new ArrayList<>();
    public String message = "Water leak, be careful!";

    /**
     * @param lifeTime
     */
    public WaterLeakSensor(double lifeTime) {
        super(lifeTime);
    }

    /**
     * @param listner
     */
    @Override
    public void addListener(EventListener listner) {
        listenerList.add(listner);
    }

    /**
     * @param listner
     */
    @Override
    public void removeListener(EventListener listner) {
        listenerList.remove(listner);
    }

    /**
     * @throws IOException
     */
    @Override
    public void alert() throws IOException {
        for (EventListener listner :listenerList){
            listner.update(message, this);
        }
    }

}