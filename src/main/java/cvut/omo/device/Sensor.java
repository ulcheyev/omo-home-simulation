package cvut.omo.device;

import cvut.omo.app_utils.Constants;
import cvut.omo.data_collections.consumption.ConsumptionCollection;
import cvut.omo.device.notifier.EventListener;
import java.io.IOException;


public abstract class Sensor extends HomeAppliances {

    public Sensor(double lifeTime) {super(lifeTime);}

    @Override
    protected void identify() {
        this.currentConsumption.put(SourceType.ENERGY, Constants.DEVICE_DISCONNECTED_STATE);
        ConsumptionCollection.getInstance().put(this);
    }

    public  void enable(){
        this.setCurrentConsumption(SourceType.ENERGY, Constants.SENSOR_ELECTRICITY_IDDLE_STATE_CONSUMPTION);
    };


    public void goIntoPauseMode(){
        this.setCurrentConsumption(SourceType.ENERGY, Constants.SENSOR_ELECTRICITY_IDDLE_STATE_CONSUMPTION);
    };

    public  void run(){
        this.setCurrentConsumption(SourceType.ENERGY, Constants.SENSOR_ELECTRICITY_RUN_STATE_CONSUMPTION);
    };

    public abstract void addListener(EventListener listener);
    public abstract void removeListener(EventListener listener);
    public abstract void alert() throws IOException;
}
