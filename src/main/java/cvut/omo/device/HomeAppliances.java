package cvut.omo.device;

import cvut.omo.app_utils.Constants;
import cvut.omo.device.device_data_structure.ConsumptionAndUsageCollection;
import cvut.omo.device.device_data_structure.ConsumptionData;
import cvut.omo.device.device_data_structure.SourceType;
import cvut.omo.device.device_data_structure.UsageData;
import cvut.omo.home_structure.Room;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Setter
@Getter
@AllArgsConstructor
@ToString
public abstract class HomeAppliances implements HomeDevice{

    protected List<ConsumptionData> consumptionDataList;
    protected List<UsageData> usageDataList;
    protected Set<SourceType> sourceTypes;

    private int currConsData = 0;

    protected double lifeTimeInYear;
    protected double workingHours;

    protected Room room;

    protected Documentation documentation;

    //TODO
    protected HomeDeviceState homeDeviceState;

    public HomeAppliances(double lifeTime){
        consumptionDataList = new ArrayList<>();
        usageDataList = new ArrayList<>();
        sourceTypes = new HashSet<>();
        this.lifeTimeInYear = lifeTime;
        identifyConsumptionData();
        ConsumptionAndUsageCollection.getInstance().putHomeDevice(this);
    }

    //TODO state. Change current consumption depends on state. Washing machine has water consumption also.
    public void turnOn(){}
    public void turnOff(){}
    public void pause(){}
    public void stop(){}


    public List<ConsumptionData> getConsumptionData(){return this.consumptionDataList;}

    public void changeCurrentConsumption(SourceType sourceType, double currentConsumption){
        for(int i = currConsData; i < consumptionDataList.size(); i++){
            if(consumptionDataList.get(i).getSourceType() == sourceType){
                consumptionDataList.get(i).setDeviceCurrentConsumption(currentConsumption);
            }
        }
    }

    //TODO update working hours and check life time
    public void update(){
        this.workingHours++;
        if(workingHours % Constants.DAY_IN_HOUR == 0){

            for (SourceType sourceType : sourceTypes) {
                consumptionDataList.add(new ConsumptionData(this, sourceType));
                currConsData++;
            }
        }
        for(int i = currConsData; i < consumptionDataList.size(); i++){
            consumptionDataList.get(i).update();
        }
    }


    //TODO getDocumentation;
    public Documentation getDocumentation(){this.documentation = new Documentation();return documentation;}

    //TODO visitor;
    public abstract void accept();


    protected abstract void identifyConsumptionData();



}
