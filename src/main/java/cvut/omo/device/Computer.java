package cvut.omo.device;

import cvut.omo.device.device_data_structure.ConsumptionAndUsageCollection;
import cvut.omo.device.device_data_structure.ConsumptionData;
import cvut.omo.device.device_data_structure.SourceType;

public class Computer extends HomeAppliances {

    public Computer(double lifeTime) {super(lifeTime);}

    @Override
    protected void identifyConsumptionData() {
        this.sourceTypes.add(SourceType.ENERGY);
        this.consumptionDataList.add(new ConsumptionData(this, SourceType.ENERGY));
    }

    @Override
    public void accept() {}
}
