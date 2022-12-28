package cvut.omo.device;

import cvut.omo.app_utils.Constants;
import cvut.omo.data_collections.consumption.ConsumptionCollection;
import cvut.omo.entity.person.Person;

public class Fridge extends HomeAppliances{

    public Fridge(double lifeTime) {super(lifeTime);}

    @Override
    public void enable() {
        this.setCurrentConsumption(SourceType.ENERGY, Constants.FRIDGE_IDDLE_STATE_ELECTRICITY_CONSUMPTION);
    }

    @Override
    public void goIntoPauseMode() {
        this.setCurrentConsumption(SourceType.ENERGY, Constants.FRIDGE_IDDLE_STATE_ELECTRICITY_CONSUMPTION);
    }

    @Override
    public void run() {
        this.setCurrentConsumption(SourceType.ENERGY, Constants.FRIDGE_RUN_ELECTRICITY_CONSUMPTION);
    }

    @Override
    protected void identify() {
        this.currentConsumption.put(SourceType.ENERGY, Constants.DEVICE_DISCONNECTED_STATE);
        ConsumptionCollection.getInstance().put(this);
    }

}
