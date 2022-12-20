package cvut.omo.device;

import cvut.omo.app_utils.Constants;
import cvut.omo.data_collections.consumption.ConsumptionCollection;
import cvut.omo.data_collections.consumption.ConsumptionData;
import cvut.omo.entity.person.Person;
import cvut.omo.home_structure.Room;
import lombok.*;

import java.util.*;

import static cvut.omo.app_utils.Constants.*;


@Setter
@Getter
@AllArgsConstructor
public abstract class HomeAppliances implements HomeDevice{

    protected Dictionary<SourceType, Double> currentConsumption;
    protected double lifeTimeInYear;
    protected double workingHours;
    protected Room room;
    protected Documentation documentation;
    protected List<Person> persons;
    protected ConsumptionData consumptionData;
    protected HomeDeviceState homeDeviceState;

    public HomeAppliances(double lifeTime){
        currentConsumption = new Hashtable<>();
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
        this.currentConsumption.put(sourceType, currentConsumption);
    }

    public double getCurrentConsumption(SourceType sourceType){
        return currentConsumption.get(sourceType);
    }

    //TODO update working hours and check life time
    public void update(){
        this.workingHours++;
        ConsumptionCollection.getInstance().update(this);
    }

    //TODO getDocumentation;
    public Documentation getDocumentation(){this.documentation = new Documentation();return documentation;}

    public boolean isNotConsume(){
        return currentConsumption.get(SourceType.NOT_CONSUME) != null;
    }

    public Set<SourceType> getSourceTypes(){
        Set<SourceType> sourceTypes = new HashSet<>();
        for (Iterator<SourceType> it = currentConsumption.keys().asIterator(); it.hasNext(); ) {
            SourceType sourceType = it.next();
            sourceTypes.add(sourceType);
        }
        return sourceTypes;
    }

    //TODO visitor;
    public abstract void accept();

    protected abstract void identify();


    //FUNCTIONAL METHODS
    protected abstract void turnOn();
    protected abstract void goIntoPauseMode();
    protected abstract void run();
    protected void turnOff(){
        for (Iterator<SourceType> it = currentConsumption.keys().asIterator(); it.hasNext(); ) {
            SourceType sourceType = it.next();
            setCurrentConsumption(sourceType, DEVICE_OFF_STATE);
        }
    };
    protected void breakDown(){
        for (Iterator<SourceType> it = currentConsumption.keys().asIterator(); it.hasNext(); ) {
            SourceType sourceType = it.next();
            setCurrentConsumption(sourceType, DEVICE_BROKEN_STATE);
        }
    }


}
