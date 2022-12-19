package cvut.omo.device;

import cvut.omo.data_collections.consumption.ConsumptionCollection;

public class TV extends HomeAppliances {

    public TV(double lifeTime) {super(lifeTime);}

    public String getDeviceName(){
        return device;
    }

    @Override
    protected void identify() {
        this.sourceTypes.add(SourceType.ENERGY);
        ConsumptionCollection.getInstance().put(this);
        device = "TV";
    }

    @Override
    public void accept() {}
}
