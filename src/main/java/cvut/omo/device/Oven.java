package cvut.omo.device;

import cvut.omo.data_collections.consumption.ConsumptionCollection;

public class Oven extends HomeAppliances{

    public Oven(double lifeTime) {super(lifeTime);}

    @Override
    protected void identify() {
        this.sourceTypes.add(SourceType.ENERGY);
        ConsumptionCollection.getInstance().put(this);
    }

    @Override
    public void accept() {}
}
