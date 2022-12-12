package cvut.omo.device;

import cvut.omo.device.device_data_structure.ConsumptionData;
import cvut.omo.device.device_data_structure.SourceType;

public class Oven extends HomeAppliances{

    public Oven(double lifeTime) {super(lifeTime);}

    @Override
    protected void identifyConsumptionData() {
        this.consumptionDataList.add(new ConsumptionData(this, SourceType.ENERGY));
    }

    @Override
    public void accept() {}
}
