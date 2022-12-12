package cvut.omo.device;

import cvut.omo.device.device_data_structure.ConsumptionData;
import cvut.omo.device.device_data_structure.SourceType;

public class CircuitBreaker extends HomeAppliances{

    public CircuitBreaker(double lifeTime) {super(lifeTime);}

    @Override
    protected void identifyConsumptionData() {
        this.consumptionDataList.add(new ConsumptionData(this, SourceType.NOT_CONSUME));
    }

    @Override
    public void accept() {}

}
