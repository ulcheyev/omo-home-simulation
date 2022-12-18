package cvut.omo.device;

import cvut.omo.data_collections.consumption.ConsumptionCollection;

public abstract class Sensor extends HomeAppliances {

    public Sensor(double lifeTime) {super(lifeTime);}

    @Override
    protected void identify() {
        this.sourceTypes.add(SourceType.ENERGY);
        ConsumptionCollection.getInstance().put(this);
    }
    public abstract void alert();
}
