package cvut.omo.device;

import cvut.omo.device.device_data_structure.ConsumptionData;
import cvut.omo.device.device_data_structure.SourceType;

public class WashingMachine extends HomeAppliances{

    public WashingMachine(double lifeTime) {super(lifeTime);}

    @Override
    protected void identifyConsumptionData() {
        this.consumptionDataList.add(new ConsumptionData(this, SourceType.ENERGY));
        this.consumptionDataList.add(new ConsumptionData(this, SourceType.WATER));
    }

    @Override
    public void accept() {}
}
