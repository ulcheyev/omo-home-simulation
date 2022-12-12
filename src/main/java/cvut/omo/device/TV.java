package cvut.omo.device;

import cvut.omo.device.device_data_structure.ConsumptionData;
import cvut.omo.device.device_data_structure.SourceType;

public class TV extends HomeAppliances {

    public TV(double lifeTime) {super(lifeTime);}

    @Override
    protected void identifyConsumptionData() {
        this.consumptionDataList.add(new ConsumptionData(this, SourceType.ENERGY));
    }

    @Override
    public void accept() {}
}
