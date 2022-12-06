package cvut.omo.device;

import cvut.omo.app_utils.Constants;
import cvut.omo.device.consumption_structure.ConsumptionData;
import cvut.omo.device.consumption_structure.SourceType;

public class TV extends HomeAppliances {

    public TV(){
        super();
    }

    @Override
    protected ConsumptionData identifyDeviceConsumption() {
        return new ConsumptionData(SourceType.ENERGY, Constants.TV_ELECTRICITY_CONSUMPTION);
    }

    @Override
    public void accept() {

    }
}
