package cvut.omo.device;
import cvut.omo.device.notifier.EventListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FireSensor extends Sensor  {

    List<EventListener> listenerList = new ArrayList<>();
    public String message = "Everyone needs to get out of the house - the house is on fire!";

    public FireSensor(double lifeTime) {super(lifeTime);}

    @Override
    public void addListener(EventListener listener) {
        listenerList.add(listener);
    }

    @Override
    public void removeListener(EventListener listener) {
        listenerList.remove(listener);
    }

    @Override
    public void alert() throws IOException {
        for (EventListener listener :listenerList){
            listener.update(message, this);
        }
    }

}
