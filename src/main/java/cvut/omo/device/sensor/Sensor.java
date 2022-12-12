package cvut.omo.device.sensor;

import cvut.omo.device.HomeAppliances;
import cvut.omo.device.device_data_structure.ConsumptionData;
import cvut.omo.device.device_data_structure.SourceType;

public abstract class Sensor extends HomeAppliances {

    public Sensor(double lifeTime) {super(lifeTime);}

    @Override
    protected void identifyConsumptionData() {
        this.consumptionDataList.add(new ConsumptionData(this, SourceType.ENERGY));
    }
    public abstract void alert();
}
