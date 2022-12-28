package cvut.omo.device;

import cvut.omo.app_utils.Constants;
import cvut.omo.data_collections.consumption.ConsumptionCollection;
import cvut.omo.home_structure.home_builder.Home;


public class CircuitBreaker extends HomeAppliances{

    public HomeAppliances homeAppliances;

    public CircuitBreaker(double lifeTime) {super(lifeTime);}

    @Override
    protected void identify() {
        this.currentConsumption.put(SourceType.NOT_CONSUME, Constants.DEVICE_DOES_NOT_CONSUME);
        ConsumptionCollection.getInstance().put(this);
    }

    @Override
    public void enable(){
        Home.INSTANCE.setPowerEnable(true);
    }

    @Override
    public void disable(){
        Home.INSTANCE.setPowerEnable(false);
    }

    @Override
    public void goIntoPauseMode(){}

    @Override
    public void run(){}

}
