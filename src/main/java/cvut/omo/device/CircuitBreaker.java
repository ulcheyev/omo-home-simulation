package cvut.omo.device;

import cvut.omo.data_collections.consumption.ConsumptionCollection;

public class CircuitBreaker extends HomeAppliances{

    public CircuitBreaker(double lifeTime) {super(lifeTime);}

    @Override
    protected void identify() {
        this.sourceTypes.add(SourceType.NOT_CONSUME);
        ConsumptionCollection.getInstance().put(this);
    }

    @Override
    public void accept() {}

}
