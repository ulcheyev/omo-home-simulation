package cvut.omo.device;

import cvut.omo.device.Sensor;

public class FireSensor extends Sensor {

    public FireSensor(double lifeTime) {super(lifeTime);}

    @Override
    public void alert() {}

    @Override
    public void accept() {}
}
