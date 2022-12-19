package cvut.omo.device;

import cvut.omo.data_collections.consumption.ConsumptionCollection;

public abstract class Sensor extends HomeAppliances {

    public Sensor(double lifeTime) {super(lifeTime);}

    public String getDeviceName(){
        homeAppliances.device = "Sensor";
        return device;
    }

    @Override
    protected void identify() {
        this.sourceTypes.add(SourceType.ENERGY);
        ConsumptionCollection.getInstance().put(this);
        device = getDeviceName();
    }
    public abstract void alert();
}
