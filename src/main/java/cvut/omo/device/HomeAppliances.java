package cvut.omo.device;

import cvut.omo.app_utils.Constants;
import cvut.omo.data_collections.consumption.ConsumptionCollection;
import cvut.omo.data_collections.consumption.ConsumptionData;
import cvut.omo.device.states.*;
import cvut.omo.entity.person.Person;
import cvut.omo.home_structure.Room;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

import static cvut.omo.app_utils.Constants.*;


@Setter
@Getter
@AllArgsConstructor
@ToString
public abstract class HomeAppliances implements HomeDevice{

    protected Set<SourceType> sourceTypes;

    protected SourceType sourceType;

    protected double lifeTimeInYear;
    protected double workingHours;

    protected Room room;

    protected Documentation documentation;

    public HomeDeviceState getHomeDeviceState() {
        return homeDeviceState;
    }

    public String device;

    protected Person person;


    protected ConsumptionData consumptionData;

    //TODO
    protected HomeDeviceState homeDeviceState = new OffState(this);

    public HomeAppliances(double lifeTime){
        sourceTypes = new HashSet<>();
        this.lifeTimeInYear = lifeTime;
        identify();

    }

    protected HomeAppliances homeAppliances;
    protected Computer computer;

    //TODO state. Change current consumption depends on state. Washing machine has water consumption also.
    public void turnOn(){
        homeDeviceState.switchOn(this);

        switch (this.device){
            case "Fridge":
                if(homeDeviceState instanceof IddleState){
                    sourceType.ENERGY.setCurrentConsumption(FRIDGE_IDDLE_STATE_ELECTRICITY_CONSUMPTION);
                }
                break;
            case "Computer":
                if(homeDeviceState instanceof IddleState)
                    sourceType.ENERGY.setCurrentConsumption(COMPUTER_IDDLE_STATE_ELECTRICITY_CONSUMPTION);
                break;
            case "TV":
                if(homeDeviceState instanceof IddleState)
                    sourceType.ENERGY.setCurrentConsumption(TV_ELECTRICITY_IDDLE_STATE_CONSUMPTION);
                break;
            case "Oven":
                if(homeDeviceState instanceof IddleState)
                    sourceType.ENERGY.setCurrentConsumption(OVEN_ELECTRICITY_IDDLE_STATE_CONSUMPTION);
                break;
            case "WashingMachine":
                if(homeDeviceState instanceof IddleState)
                    sourceType.ENERGY.setCurrentConsumption(WASHING_MACHINE_IDDLE_STATE_ELECTRICITY_CONSUMPTION);
                    sourceType.WATER.setCurrentConsumption(WASHING_MACHINE_IDDLE_STATE_WATER_CONSUMPTION);
                break;
            case "Sensor":
                if(homeDeviceState instanceof IddleState)
                    sourceType.ENERGY.setCurrentConsumption(SENSOR_ELECTRICITY_IDDLE_STATE_CONSUMPTION);
                break;
        }
    }
    public void turnOff(){
        homeDeviceState.switchOff(this);
        if(homeDeviceState instanceof OffState){
            sourceType.ENERGY.setCurrentConsumption(DEVICE_OFF_STATE);
            if(this.device == "WashingMachine"){
                sourceType.WATER.setCurrentConsumption(DEVICE_OFF_STATE);
            }
        }
    }
    public void pause(){
        homeDeviceState.pauseDev(this);
        switch (this.device){
            case "Fridge":
                if(homeDeviceState instanceof IddleState){
                    sourceType.ENERGY.setCurrentConsumption(FRIDGE_IDDLE_STATE_ELECTRICITY_CONSUMPTION);
                }
                break;
            case "Computer":
                if(homeDeviceState instanceof IddleState)
                    sourceType.ENERGY.setCurrentConsumption(COMPUTER_IDDLE_STATE_ELECTRICITY_CONSUMPTION);
                break;
            case "TV":
                if(homeDeviceState instanceof IddleState)
                    sourceType.ENERGY.setCurrentConsumption(TV_ELECTRICITY_IDDLE_STATE_CONSUMPTION);
                break;
            case "Oven":
                if(homeDeviceState instanceof IddleState)
                    sourceType.ENERGY.setCurrentConsumption(OVEN_ELECTRICITY_IDDLE_STATE_CONSUMPTION);
                break;
            case "WashingMachine":
                if(homeDeviceState instanceof IddleState)
                    sourceType.ENERGY.setCurrentConsumption(WASHING_MACHINE_IDDLE_STATE_ELECTRICITY_CONSUMPTION);
                    sourceType.WATER.setCurrentConsumption(WASHING_MACHINE_IDDLE_STATE_WATER_CONSUMPTION);
                break;
            case "Sensor":
                if(homeDeviceState instanceof IddleState)
                    sourceType.ENERGY.setCurrentConsumption(SENSOR_ELECTRICITY_IDDLE_STATE_CONSUMPTION);
                break;
        }
    }

    public void start(){
        homeDeviceState.interactDev(person, this);
        switch (this.device){
            case "Fridge":
                if(homeDeviceState instanceof RunState){
                    sourceType.ENERGY.setCurrentConsumption(FRIDGE_RUN_ELECTRICITY_CONSUMPTION);
                }
                break;
            case "Computer":
                if(homeDeviceState instanceof RunState)
                    sourceType.ENERGY.setCurrentConsumption(COMPUTER_RUN_STATE_ELECTRICITY_CONSUMPTION);
                break;
            case "TV":
                if(homeDeviceState instanceof RunState)
                    sourceType.ENERGY.setCurrentConsumption(TV_ELECTRICITY_RUN_STATE_CONSUMPTION);
                break;
            case "Oven":
                if(homeDeviceState instanceof RunState)
                    sourceType.ENERGY.setCurrentConsumption(OVEN_ELECTRICITY_RUN_STATE_CONSUMPTION);
                break;
            case "WashingMachine":
                if(homeDeviceState instanceof RunState)
                    sourceType.ENERGY.setCurrentConsumption(WASHING_MACHINE_RUN_STATE_ELECTRICITY_CONSUMPTION);
                sourceType.WATER.setCurrentConsumption(WASHING_MACHINE_RUN_STATE_WATER_CONSUMPTION);
                break;
            case "Sensor":
                if(homeDeviceState instanceof RunState)
                    sourceType.ENERGY.setCurrentConsumption(SENSOR_ELECTRICITY_RUN_STATE_CONSUMPTION);
                break;
        }
    }

    public void repair(){
        homeDeviceState.repairDev(person, this);
        if(homeDeviceState instanceof BrokenState){
            sourceType.ENERGY.setCurrentConsumption(DEVICE_BROKEN_STATE);
            if(this.device == "WashingMachine"){
                sourceType.WATER.setCurrentConsumption(DEVICE_BROKEN_STATE);
            }
        }
    }


    public void setCurrentConsumption(SourceType sourceType, double currentConsumption){
        sourceTypes.forEach(type -> {if(type.equals(sourceType)){type.setCurrentConsumption(currentConsumption);}});
    }

    public double getCurrentConsumption(SourceType sourceType){
        return sourceTypes.stream()
                .filter(type->type.equals(sourceType))
                .findFirst()
                .map(SourceType::getCurrentConsumption)
                .orElse(Constants.DEVICE_OFF_STATE);
    }

    //TODO update working hours and check life time
    public void update(){
        this.workingHours++;
        ConsumptionCollection.getInstance().update(this);
    }

    //TODO getDocumentation;
    public Documentation getDocumentation(){this.documentation = new Documentation();return documentation;}

    public boolean isNotConsume(){
        return sourceTypes.contains(SourceType.NOT_CONSUME);
    }

    //TODO visitor;
    public abstract void accept();

    protected abstract void identify();




}
