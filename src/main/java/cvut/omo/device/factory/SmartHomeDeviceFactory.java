package cvut.omo.device.factory;

import cvut.omo.app_utils.Constants;
import cvut.omo.device.*;
import cvut.omo.device.sensor.FireSensor;
import cvut.omo.device.sensor.Sensor;
import cvut.omo.device.sensor.WaterLeakSensor;

public class SmartHomeDeviceFactory implements HomeDeviceFactory {

    public static final SmartHomeDeviceFactory INSTANCE = new SmartHomeDeviceFactory();
    private SmartHomeDeviceFactory(){}

    public Sensor createFireSensor(){return new FireSensor(Constants.SENSOR_LIFE_TIME);}
    public Sensor createWaterLeakSensor(){return new WaterLeakSensor(Constants.SENSOR_LIFE_TIME);}
    public HomeDevice createComputer(){return new Computer(Constants.COMPUTER_LIFE_TIME);}
    public HomeDevice createCircuitBreaker(){return new CircuitBreaker(Constants.CIRCUIT_BREAKER_LIFE_TIME);}
    public HomeDevice createOven(){return new Oven(Constants.OVEN_LIFE_TIME);}
    public HomeDevice createTV(){return new TV(Constants.TV_LIFE_TIME);}
    public HomeDevice createWashingMachine(){return new WashingMachine(Constants.WASHING_MACHINE_LIFE_TIME);}
    public HomeDevice createMicrowave(){return new Microwave(Constants.MICROWAVE_LIFE_TIME);}
}
