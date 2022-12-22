package cvut.omo.device;
import cvut.omo.app_utils.Constants;
import cvut.omo.data_collections.consumption.ConsumptionCollection;
public class WashingMachine extends HomeAppliances{

    public WashingMachine(double lifeTime) {super(lifeTime);}

    @Override
    protected void identify() {
        this.currentConsumption.put(SourceType.ENERGY, Constants.DEVICE_OFF_STATE);
        this.currentConsumption.put(SourceType.WATER, Constants.DEVICE_OFF_STATE);
        ConsumptionCollection.getInstance().put(this);
    }

    @Override
    public void turnOn() {
        setCurrentConsumption(SourceType.ENERGY, Constants.WASHING_MACHINE_IDDLE_STATE_ELECTRICITY_CONSUMPTION);
        setCurrentConsumption(SourceType.WATER, Constants.WASHING_MACHINE_IDDLE_STATE_WATER_CONSUMPTION);

    }

    @Override
    public void goIntoPauseMode() {
        setCurrentConsumption(SourceType.ENERGY, Constants.WASHING_MACHINE_IDDLE_STATE_ELECTRICITY_CONSUMPTION);
        setCurrentConsumption(SourceType.WATER, Constants.WASHING_MACHINE_IDDLE_STATE_WATER_CONSUMPTION);
    }

    @Override
    public void run() {
        setCurrentConsumption(SourceType.ENERGY, Constants.WASHING_MACHINE_RUN_STATE_ELECTRICITY_CONSUMPTION);
        setCurrentConsumption(SourceType.WATER, Constants.WASHING_MACHINE_RUN_STATE_WATER_CONSUMPTION);
    }


//    @Override
//    public void accept() {}
}
