package cvut.omo.device;

import cvut.omo.device.Sensor;

public class WaterLeakSensor extends Sensor {

    public WaterLeakSensor(double lifeTime) {
        super(lifeTime);
    }

    @Override
    public void alert() {}

}