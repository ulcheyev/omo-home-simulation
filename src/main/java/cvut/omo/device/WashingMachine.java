package cvut.omo.device;

import cvut.omo.app_utils.Constants;
import cvut.omo.data_collections.consumption.ConsumptionCollection;

public class WashingMachine extends HomeAppliances{

    public WashingMachine(double lifeTime) {super(lifeTime);}

    @Override
    protected void identify() {
        this.sourceTypes.add(SourceType.ENERGY);
        this.sourceTypes.add(SourceType.WATER);
        ConsumptionCollection.getInstance().put(this);
    }

    @Override
    public Documentation getDocumentation(){
        if(this.homeDeviceState instanceof BrokenState && this.documentation == null){
            this.documentation = new Documentation();
            this.documentation.setInstructionsForTheDevice(InstructionType.CALL_GRANDFATHER);
            this.documentation.setWarrantyCard("Washing Machine warranty card that does not guarantee");
        }
        return documentation;
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


    @Override
    public void accept() {}
}
