package cvut.omo.device;

import cvut.omo.app_utils.Constants;
import cvut.omo.data_collections.consumption.ConsumptionCollection;
import cvut.omo.entity.person.Person;

public class TV extends HomeAppliances {

    public TV(double lifeTime) {super(lifeTime);}

    @Override
    protected void identify() {
        this.sourceTypes.add(SourceType.ENERGY);
        ConsumptionCollection.getInstance().put(this);
    }

    @Override
    public Documentation getDocumentation(){
        if(this.homeDeviceState instanceof BrokenState && this.documentation == null){
            this.documentation = new Documentation();
            this.documentation.setInstructionsForTheDevice(InstructionType.CALL_GRANDFATHER);
            this.documentation.setWarrantyCard("TV warranty card that does not guarantee");
        }
        return documentation;
    }

    @Override
    public void turnOn() {
        setCurrentConsumption(SourceType.ENERGY, Constants.TV_ELECTRICITY_IDDLE_STATE_CONSUMPTION);
    }

    @Override
    public void goIntoPauseMode() {
        setCurrentConsumption(SourceType.ENERGY, Constants.TV_ELECTRICITY_IDDLE_STATE_CONSUMPTION);
    }

    @Override
    public void run() {
        setCurrentConsumption(SourceType.ENERGY, Constants.TV_ELECTRICITY_RUN_STATE_CONSUMPTION);
    }


    @Override
    public void accept() {}
}
