package cvut.omo.device;

import cvut.omo.data_collections.consumption.ConsumptionCollection;

public class CircuitBreaker extends HomeAppliances{

    public HomeAppliances homeAppliances;

    public CircuitBreaker(double lifeTime) {super(lifeTime);}

    @Override
    protected void identify() {
        this.sourceTypes.add(SourceType.NOT_CONSUME);
        ConsumptionCollection.getInstance().put(this);
    }

    @Override
    public void turnOn(){}

    @Override
    public void turnOff(){}

    @Override
    public void goIntoPauseMode(){}

    @Override
    public void run(){}
    
    @Override
    public void accept() {}

}
