package cvut.omo.device;

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
    public void accept() {}
}
