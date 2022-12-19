package cvut.omo.device;

import cvut.omo.data_collections.consumption.ConsumptionCollection;

public class Computer extends HomeAppliances {

    public Computer(double lifeTime) {super(lifeTime);}

    public String getDeviceName(){
        return device;
    }

    @Override
    protected void identify() {
        this.sourceTypes.add(SourceType.ENERGY);
        ConsumptionCollection.getInstance().put(this);
        device = "Computer";

    }

    @Override
    public void accept() {}
}
