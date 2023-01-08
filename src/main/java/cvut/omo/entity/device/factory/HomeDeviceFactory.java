package cvut.omo.entity.device.factory;

import cvut.omo.entity.device.HomeDevice;
import cvut.omo.entity.device.Sensor;

/**
 * Factory for {@link HomeDevice}.
 */
public interface HomeDeviceFactory {

    /**
     * Creates {@link cvut.omo.entity.device.FireSensor}.
     *
     * @return o new object of {@link cvut.omo.entity.device.FireSensor}
     */
    Sensor createFireSensor();

    /**
     * Create {@link cvut.omo.entity.device.WaterLeakSensor}.
     *
     * @return o new object of {@link cvut.omo.entity.device.WaterLeakSensor}
     */
    Sensor createWaterLeakSensor();

    /**
     * Create {@link cvut.omo.entity.device.Computer}.
     *
     * @return o new object of {@link cvut.omo.entity.device.Computer}
     */
    HomeDevice createComputer();

    /**
     * Create {@link cvut.omo.entity.device.CircuitBreaker}.
     *
     * @return o new object of {@link cvut.omo.entity.device.CircuitBreaker}
     */
    HomeDevice createCircuitBreaker();

    /**
     * Create {@link cvut.omo.entity.device.Oven}.
     *
     * @return o new object of {@link cvut.omo.entity.device.Oven}
     */
    HomeDevice createOven();

    /**
     * Create {@link cvut.omo.entity.device.TV}.
     *
     * @return o new object of {@link cvut.omo.entity.device.TV}
     */
    HomeDevice createTV();

    /**
     * Create {@link cvut.omo.entity.device.WashingMachine}.
     *
     * @return o new object of {@link cvut.omo.entity.device.WashingMachine}
     */
    HomeDevice createWashingMachine();

    /**
     * Create {@link cvut.omo.entity.device.Fridge}.
     *
     * @return o new object of {@link cvut.omo.entity.device.Fridge}
     */
    HomeDevice createFridge();
}
