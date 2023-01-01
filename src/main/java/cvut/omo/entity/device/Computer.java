package cvut.omo.entity.device;

import cvut.omo.app_utils.Constants;
import cvut.omo.data_collections.consumption.ConsumptionCollection;

/**
 * Class represents computer device.
 */
public class Computer extends HomeAppliances {

    public Computer(double lifeTime) {super(lifeTime);}

    @Override
    protected void identify() {
        this.currentConsumption.put(SourceType.ENERGY, Constants.DEVICE_OFF_STATE_ELECTRICITY);
        ConsumptionCollection.getInstance().put(this);
    }

    @Override
    public void enable() {
        this.setCurrentConsumption(SourceType.ENERGY, Constants.COMPUTER_IDDLE_STATE_ELECTRICITY_CONSUMPTION);
    }

    @Override
    public void goIntoIddleMode() {
        this.setCurrentConsumption(SourceType.ENERGY, Constants.COMPUTER_IDDLE_STATE_ELECTRICITY_CONSUMPTION);
    }

    @Override
    public void run() {
        this.setCurrentConsumption(SourceType.ENERGY, Constants.COMPUTER_RUN_STATE_ELECTRICITY_CONSUMPTION);
    }

}
