package cvut.omo.device;

import cvut.omo.app_utils.Constants;

public enum SourceType {
    WATER,
    ENERGY,
    NOT_CONSUME;

    SourceType(){
       this.currentConsumption = Constants.DEVICE_OFF_STATE;
    }

    private double currentConsumption;

    public double getCurrentConsumption() {
        return currentConsumption;
    }

    public void setCurrentConsumption(double currentConsumption) {
        this.currentConsumption = currentConsumption;
    }
}
