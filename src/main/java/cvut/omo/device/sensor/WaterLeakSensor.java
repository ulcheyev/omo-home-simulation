package cvut.omo.device.sensor;

public class WaterLeakSensor extends Sensor {

    public WaterLeakSensor(double lifeTime) {
        super(lifeTime);
    }

    @Override
    public void alert() {}

    @Override
    public void accept() {}
}