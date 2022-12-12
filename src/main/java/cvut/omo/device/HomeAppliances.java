package cvut.omo.device;

import cvut.omo.app_utils.Constants;
import cvut.omo.device.device_data_structure.ConsumptionData;
import cvut.omo.device.device_data_structure.UsageData;
import cvut.omo.home_structure.Room;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@ToString
public abstract class HomeAppliances implements HomeDevice{

    protected List<ConsumptionData> consumptionDataList;
    protected List<UsageData> usageDataList;

    protected double currentConsumption;

    protected double lifeTimeInYear;
    protected double workingHours;

    protected Room room;

    protected Documentation documentation;

    //TODO
    protected HomeDeviceState homeDeviceState;

    public HomeAppliances(double lifeTime){
        consumptionDataList = new ArrayList<>();
        usageDataList = new ArrayList<>();
        currentConsumption = Constants.DEVICE_OFF_STATE;
        this.lifeTimeInYear = lifeTime;
    }

    //TODO state. Change current consumption depends on state. Washing machine has water consumption also.
    public void turnOn(){}
    public void turnOff(){}
    public void pause(){}
    public void stop(){}


    public List<ConsumptionData> getConsumptionData(){return this.consumptionDataList;}
    public void changeCurrentConsumption(double currentConsumption){this.currentConsumption = currentConsumption;}

    //TODO update working hours and check life time
    public void update(){this.workingHours++;}


    //TODO getDocumentation;
    public Documentation getDocumentation(){this.documentation = new Documentation();return documentation;}

    //TODO visitor;
    public abstract void accept();


    protected abstract void identifyConsumptionData();



}
