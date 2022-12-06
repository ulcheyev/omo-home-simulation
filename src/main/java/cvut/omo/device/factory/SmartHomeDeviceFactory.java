package cvut.omo.device.factory;

import cvut.omo.device.HomeDevice;
import cvut.omo.device.sensor.FireSensor;
import cvut.omo.device.sensor.AbstractSensor;
import cvut.omo.device.sensor.Sensor;
import cvut.omo.device.sensor.WaterLeakSensor;

public class SmartHomeDeviceFactory implements AbstractHomeDeviceFactory{


    public Sensor createFireSensor(){return new FireSensor();}
    public Sensor createWaterLeakSensor(){return new WaterLeakSensor();}
//    public HomeDevice
}
