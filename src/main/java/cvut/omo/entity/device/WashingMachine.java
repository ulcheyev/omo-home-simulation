package cvut.omo.entity.device;

import cvut.omo.app_utils.Constants;
import cvut.omo.data_collections.consumption.ConsumptionCollection;

/**
 * Class represents washing machine device.
 */
public class WashingMachine extends HomeAppliances {

    public WashingMachine(double lifeTime) {
        super(lifeTime);
    }

    @Override
    protected void identify() {
        this.currentConsumption.put(SourceType.ENERGY, Constants.DEVICE_OFF_STATE_ELECTRICITY);
        this.currentConsumption.put(SourceType.WATER, Constants.DEVICE_OFF_STATE_WATER);
        ConsumptionCollection.getInstance().put(this);
    }

    @Override
    public void enable() {
        setCurrentConsumption(SourceType.ENERGY, Constants.WASHING_MACHINE_IDDLE_STATE_ELECTRICITY_CONSUMPTION);
        setCurrentConsumption(SourceType.WATER, Constants.WASHING_MACHINE_IDDLE_STATE_WATER_CONSUMPTION);

    }

    @Override
    public void goIntoIddleMode() {
        setCurrentConsumption(SourceType.ENERGY, Constants.WASHING_MACHINE_IDDLE_STATE_ELECTRICITY_CONSUMPTION);
        setCurrentConsumption(SourceType.WATER, Constants.WASHING_MACHINE_IDDLE_STATE_WATER_CONSUMPTION);
    }

    @Override
    public void run() {
        setCurrentConsumption(SourceType.ENERGY, Constants.WASHING_MACHINE_RUN_STATE_ELECTRICITY_CONSUMPTION);
        setCurrentConsumption(SourceType.WATER, Constants.WASHING_MACHINE_RUN_STATE_WATER_CONSUMPTION);
    }


}
