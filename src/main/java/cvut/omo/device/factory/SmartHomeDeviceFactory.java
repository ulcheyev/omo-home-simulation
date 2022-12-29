package cvut.omo.device.factory;

import cvut.omo.app_utils.Constants;
import cvut.omo.device.*;
import cvut.omo.device.FireSensor;
import cvut.omo.device.Sensor;
import cvut.omo.device.WaterLeakSensor;

/**
 *
 */
public class SmartHomeDeviceFactory implements HomeDeviceFactory {

    public static final SmartHomeDeviceFactory INSTANCE = new SmartHomeDeviceFactory();
    private SmartHomeDeviceFactory(){}

    /**
     * @return
     */
    public Sensor createFireSensor(){return new FireSensor(Constants.SENSOR_LIFE_TIME);}

    /**
     * @return
     */
    public Sensor createWaterLeakSensor(){return new WaterLeakSensor(Constants.SENSOR_LIFE_TIME);}

    /**
     * @return
     */
    public HomeDevice createComputer(){return new Computer(Constants.COMPUTER_LIFE_TIME);}

    /**
     * @return
     */
    public HomeDevice createCircuitBreaker(){return new CircuitBreaker(Constants.CIRCUIT_BREAKER_LIFE_TIME);}

    /**
     * @return
     */
    public HomeDevice createOven(){return new Oven(Constants.OVEN_LIFE_TIME);}

    /**
     * @return
     */
    public HomeDevice createTV(){return new TV(Constants.TV_LIFE_TIME);}

    /**
     * @return
     */
    public HomeDevice createWashingMachine(){return new WashingMachine(Constants.WASHING_MACHINE_LIFE_TIME);}

    /**
     * @return
     */
    public HomeDevice createFridge(){return new Fridge(Constants.FRIDGE_LIFE_TIME);}
}
