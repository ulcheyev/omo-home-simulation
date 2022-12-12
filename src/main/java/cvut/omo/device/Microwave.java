package cvut.omo.device;

import cvut.omo.device.device_data_structure.ConsumptionData;
import cvut.omo.device.device_data_structure.SourceType;

public class Microwave extends HomeAppliances{

    public Microwave(double lifeTime) {super(lifeTime);}


    @Override
    public void accept() {

    }

    @Override
    protected void identifyConsumptionData() {
        this.consumptionDataList.add(new ConsumptionData(this, SourceType.ENERGY));
    }
}
