package cvut.omo.device;

import cvut.omo.data_collections.consumption.ConsumptionCollection;

public class Fridge extends HomeAppliances{

    public Fridge(double lifeTime) {super(lifeTime);}


    @Override
    public void accept() {

    }

    @Override
    protected void identify() {
        this.sourceTypes.add(SourceType.ENERGY);
        ConsumptionCollection.getInstance().put(this);
    }
}
