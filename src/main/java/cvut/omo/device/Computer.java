package cvut.omo.device;

import cvut.omo.app_utils.Constants;
import cvut.omo.device.consumption_structure.ConsumptionData;
import cvut.omo.device.consumption_structure.SourceType;

public class Computer extends HomeAppliances {

    public Computer(){
        super();
    }

    @Override
    protected ConsumptionData identifyDeviceConsumption() {
        return new ConsumptionData(SourceType.ENERGY, Constants.COMPUTER_ELECTRICITY_CONSUMPTION);
    }

    @Override
    public void accept() {

    }
}
