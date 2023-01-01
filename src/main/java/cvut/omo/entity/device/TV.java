package cvut.omo.entity.device;

import cvut.omo.app_utils.Constants;
import cvut.omo.data_collections.consumption.ConsumptionCollection;

/**
 * Class represents TV device.
 */
public class TV extends HomeAppliances {

    public TV(double lifeTime) {super(lifeTime);}

    @Override
    protected void identify() {
        this.currentConsumption.put(SourceType.ENERGY, Constants.DEVICE_OFF_STATE_ELECTRICITY);
        ConsumptionCollection.getInstance().put(this);
    }

    @Override
    public void enable() {
        this.setCurrentConsumption(SourceType.ENERGY, Constants.TV_ELECTRICITY_IDDLE_STATE_CONSUMPTION);
    }

    @Override
    public void goIntoIddleMode() {
        this.setCurrentConsumption(SourceType.ENERGY, Constants.TV_ELECTRICITY_IDDLE_STATE_CONSUMPTION);
    }


    @Override
    public void run() {
        this.setCurrentConsumption(SourceType.ENERGY, Constants.TV_ELECTRICITY_RUN_STATE_CONSUMPTION);
    }

}
