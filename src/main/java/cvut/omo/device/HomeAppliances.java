package cvut.omo.device;

import cvut.omo.device.consumption_structure.ConsumptionData;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
public abstract class HomeAppliances implements HomeDevice{


    protected ConsumptionData consumptionData;
    protected double workingHours;


    public HomeAppliances(){
        consumptionData = identifyDeviceConsumption();
    }


    //TODO state;

    public void turnOn(){
    }
    public void turnOff(){
    }
    public void pause(){
    }
    public void stop(){
    }

    protected abstract ConsumptionData identifyDeviceConsumption();

    //TODO visitor;
    public abstract void accept();

    //TODO getDocumentation;

}
