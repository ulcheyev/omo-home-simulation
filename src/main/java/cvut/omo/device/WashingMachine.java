package cvut.omo.device;

import cvut.omo.data_collections.consumption.ConsumptionCollection;

public class WashingMachine extends HomeAppliances{

    public WashingMachine(double lifeTime) {super(lifeTime);}

    public String getDeviceName(){
        return device;
    }

    @Override
    protected void identify() {
        this.sourceTypes.add(SourceType.ENERGY);
        this.sourceTypes.add(SourceType.WATER);
        ConsumptionCollection.getInstance().put(this);
        device = "WashingMachine";
    }

    @Override
    public void accept() {}
}
