package cvut.omo.device.factory;

import cvut.omo.device.*;
import cvut.omo.device.Sensor;

public interface HomeDeviceFactory {

     Sensor createFireSensor();
     Sensor createWaterLeakSensor();
     HomeDevice createComputer();
     HomeDevice createCircuitBreaker();
     HomeDevice createOven();
     HomeDevice createTV();
     HomeDevice createWashingMachine();
     HomeDevice createFridge();
}
