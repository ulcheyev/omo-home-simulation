package cvut.omo.device.notifier;
import cvut.omo.device.Sensor;

import java.io.IOException;

public interface EventListener {

    void update(String message, Sensor sensor) throws IOException;
}
