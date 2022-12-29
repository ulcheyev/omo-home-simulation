package cvut.omo.device;

import cvut.omo.app_utils.Constants;
import cvut.omo.data_collections.consumption.ConsumptionCollection;
import cvut.omo.event.EventGenerator;
import cvut.omo.event.event_type.HomeEvent;
import cvut.omo.home_structure.home_builder.Home;


public class CircuitBreaker extends HomeAppliances{

    public CircuitBreaker(double lifeTime) {
        super(lifeTime);
        Home.INSTANCE.setCircuitBreaker(this);
    }

    @Override
    protected void identify() {
        this.currentConsumption.put(SourceType.NOT_CONSUME, Constants.DEVICE_DOES_NOT_CONSUME);
        ConsumptionCollection.getInstance().put(this);
    }

    @Override
    public void disable(){
    }

    @Override
    public void enable(){}

    @Override
    public void goIntoIddleMode(){}

    @Override
    public void run(){
    }

}
