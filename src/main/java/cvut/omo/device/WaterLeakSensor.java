package cvut.omo.device;

import cvut.omo.device.Sensor;

public class WaterLeakSensor extends Sensor {

    public WaterLeakSensor(double lifeTime) {
        super(lifeTime);
    }

    public String getDeviceName(){
        device = "WaterLeakSensor";
        return device;
    }

    @Override
    public void alert() {}

    @Override
    public void accept() {}
}