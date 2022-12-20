package cvut.omo.device;

import cvut.omo.app_utils.Constants;
import cvut.omo.data_collections.consumption.ConsumptionCollection;
import cvut.omo.entity.person.Person;

import static cvut.omo.app_utils.Constants.DEVICE_OFF_STATE;

public abstract class Sensor extends HomeAppliances {

    public Sensor(double lifeTime) {super(lifeTime);}

    @Override
    protected void identify() {
        this.sourceTypes.add(SourceType.ENERGY);
        ConsumptionCollection.getInstance().put(this);
    }

    public  void turnOn(){
        setCurrentConsumption(SourceType.ENERGY, Constants.SENSOR_ELECTRICITY_IDDLE_STATE_CONSUMPTION);
    };


    public void goIntoPauseMode(){
        setCurrentConsumption(SourceType.ENERGY, Constants.SENSOR_ELECTRICITY_IDDLE_STATE_CONSUMPTION);

    };

    public  void run(){
        setCurrentConsumption(SourceType.ENERGY, Constants.SENSOR_ELECTRICITY_RUN_STATE_CONSUMPTION);
    };

    public abstract void alert();
}
