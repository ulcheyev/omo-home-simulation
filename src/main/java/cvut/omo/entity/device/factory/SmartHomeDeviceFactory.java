package cvut.omo.entity.device.factory;

import cvut.omo.app_utils.Constants;
import cvut.omo.entity.device.*;

/**
 * Class is used to create device in {@link cvut.omo.home_structure.home_builder.Home}.
 */
public class SmartHomeDeviceFactory implements HomeDeviceFactory {

    /**
     * Instance of SmartHomeDeviceFactory.
     */
    public static final SmartHomeDeviceFactory INSTANCE = new SmartHomeDeviceFactory();

    private SmartHomeDeviceFactory(){}

    public Sensor createFireSensor(){return new FireSensor(Constants.SENSOR_LIFE_TIME);}
    public Sensor createWaterLeakSensor(){return new WaterLeakSensor(Constants.SENSOR_LIFE_TIME);}
    public HomeDevice createComputer(){return new Computer(Constants.COMPUTER_LIFE_TIME);}
    public HomeDevice createCircuitBreaker(){return new CircuitBreaker(Constants.CIRCUIT_BREAKER_LIFE_TIME);}
    public HomeDevice createOven(){return new Oven(Constants.OVEN_LIFE_TIME);}
    public HomeDevice createTV(){return new TV(Constants.TV_LIFE_TIME);}
    public HomeDevice createWashingMachine(){return new WashingMachine(Constants.WASHING_MACHINE_LIFE_TIME);}
    public HomeDevice createFridge(){return new Fridge(Constants.FRIDGE_LIFE_TIME);}
}
