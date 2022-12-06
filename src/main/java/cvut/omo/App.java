package cvut.omo;
import cvut.omo.device.factory.SmartHomeDeviceFactory;
import cvut.omo.device.sensor.Sensor;

public class App {


    public static void main(String[] args) {
        SmartHomeDeviceFactory smartHomeDeviceFactory = new SmartHomeDeviceFactory();
        Sensor fireSensor = smartHomeDeviceFactory.createFireSensor();
    }
}
