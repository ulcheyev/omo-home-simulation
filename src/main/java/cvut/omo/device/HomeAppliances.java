package cvut.omo.device;

import cvut.omo.app_utils.Constants;
import cvut.omo.data_collections.consumption.ConsumptionCollection;
import cvut.omo.data_collections.consumption.ConsumptionData;
import cvut.omo.entity.person.Person;
import cvut.omo.home_structure.Room;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static cvut.omo.app_utils.Constants.*;


@Setter
@Getter
@AllArgsConstructor
public abstract class HomeAppliances implements HomeDevice{

    protected Set<SourceType> sourceTypes;
    protected double lifeTimeInYear;
    protected double workingHours;
    protected Room room;
    protected Documentation documentation;
    protected List<Person> persons;
    protected ConsumptionData consumptionData;
    protected HomeDeviceState homeDeviceState;

    public HomeAppliances(double lifeTime){
        sourceTypes = new HashSet<>();
        homeDeviceState = new OffState(this);
        persons = new ArrayList<>();
        this.lifeTimeInYear = lifeTime;
        identify();
    }

    //API METHODS
    public void clickOn(){homeDeviceState.switchOn(this);};
    public void clickOff(){homeDeviceState.switchOff(this);}
    public void clickPause(){homeDeviceState.pause(this);};
    public void clickStart(Person person){
        this.persons.add(person);
        homeDeviceState.use(person, this);
    };
    public void repair(Person person){
        homeDeviceState.repair(person, this);
    };
    public void breakDevice(){
        homeDeviceState._break(this);
    };



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
    public Documentation getDocumentation(){
        if(this.homeDeviceState instanceof BrokenState && this.documentation == null) {
            this.documentation = new Documentation();
        }
        return documentation;
    }

    public boolean isNotConsume(){
        return sourceTypes.contains(SourceType.NOT_CONSUME);
    }

    public HomeDeviceState getHomeDeviceState() {
        return homeDeviceState;
    }

    //TODO visitor;
    public abstract void accept();

    protected abstract void identify();


    //FUNCTIONAL METHODS
    protected abstract void turnOn();
    protected abstract void goIntoPauseMode();
    protected abstract void run();
    protected void turnOff(){
        for(SourceType sourceType: this.sourceTypes){
            setCurrentConsumption(sourceType, DEVICE_OFF_STATE);
        }
    };
    protected void breakDown(){
        for(SourceType sourceType: this.sourceTypes){
            setCurrentConsumption(sourceType, DEVICE_BROKEN_STATE);
        }
    };


}
