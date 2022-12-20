package cvut.omo.device;

import cvut.omo.app_utils.Constants;
import cvut.omo.data_collections.consumption.ConsumptionCollection;
import cvut.omo.entity.person.Person;

public class Fridge extends HomeAppliances{

    public Fridge(double lifeTime) {super(lifeTime);}

    @Override
    public Documentation getDocumentation(){
        if(this.homeDeviceState instanceof BrokenState && this.documentation == null){
            this.documentation = new Documentation();
            this.documentation.setInstructionsForTheDevice(InstructionType.CALL_GRANDFATHER);
            this.documentation.setWarrantyCard("Fridge warranty card that does not guarantee");
        }
        return documentation;
    }

    @Override
    public void turnOn() {
        setCurrentConsumption(SourceType.ENERGY, Constants.FRIDGE_IDDLE_STATE_ELECTRICITY_CONSUMPTION);
    }

    @Override
    public void goIntoPauseMode() {
        setCurrentConsumption(SourceType.ENERGY, Constants.FRIDGE_IDDLE_STATE_ELECTRICITY_CONSUMPTION);
    }

    @Override
    public void run() {
        setCurrentConsumption(SourceType.ENERGY, Constants.FRIDGE_RUN_ELECTRICITY_CONSUMPTION);
    }

    @Override
    protected void identify() {
        this.sourceTypes.add(SourceType.ENERGY);
        ConsumptionCollection.getInstance().put(this);
    }


    @Override
    public void accept() {}
}
