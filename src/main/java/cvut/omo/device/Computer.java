package cvut.omo.device;

import cvut.omo.app_utils.Constants;
import cvut.omo.data_collections.consumption.ConsumptionCollection;
import cvut.omo.entity.person.Person;

public class Computer extends HomeAppliances {

    public Computer(double lifeTime) {super(lifeTime);}

    @Override
    protected void identify() {
        this.currentConsumption.put(SourceType.ENERGY, Constants.DEVICE_OFF_STATE);
        ConsumptionCollection.getInstance().put(this);
    }

    @Override
    public void turnOn() {
        this.setCurrentConsumption(SourceType.ENERGY, Constants.COMPUTER_IDDLE_STATE_ELECTRICITY_CONSUMPTION);
    }

    @Override
    public void goIntoPauseMode() {
        this.setCurrentConsumption(SourceType.ENERGY, Constants.COMPUTER_IDDLE_STATE_ELECTRICITY_CONSUMPTION);
    }

    @Override
    public void run() {
        this.setCurrentConsumption(SourceType.ENERGY, Constants.COMPUTER_RUN_STATE_ELECTRICITY_CONSUMPTION);
    }

    @Override
    public void accept() {}
}
