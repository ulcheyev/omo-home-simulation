package cvut.omo.device;

import cvut.omo.app_utils.Utils;
import cvut.omo.data_collections.consumption.ConsumptionCollection;
import cvut.omo.data_collections.consumption.ConsumptionData;
import cvut.omo.data_collections.visitor.SmartHomeVisitor;
import cvut.omo.device.documentation.BrokennessLevel;
import cvut.omo.device.documentation.Documentation;
import cvut.omo.entity.Responsible;
import cvut.omo.entity.activity.ActivityFactory;
import cvut.omo.entity.activity.ActivityType;
import cvut.omo.entity.nulls.NullResponsibleType;
import cvut.omo.entity.person.Person;
import cvut.omo.event.Event;
import cvut.omo.home_structure.room_builder.Room;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.*;

import static cvut.omo.app_utils.Constants.*;


@Getter
@Setter
public abstract class HomeAppliances extends Responsible implements HomeDevice{

    protected Dictionary<SourceType, Double> currentConsumption;
    protected List<Person> persons;

    protected double lifeTimeInYear;
    protected double workingHours;
    protected Room room;
    protected Documentation documentation;
    protected ConsumptionData consumptionData;
    protected HomeDeviceState homeDeviceState;
    protected BrokennessLevel brokennessLevel;

    public HomeAppliances(double lifeTime){
        super(new NullResponsibleType());
        currentConsumption = new Hashtable<>();
        homeDeviceState = new OffState(this);
        persons = new ArrayList<>();
        this.lifeTimeInYear = lifeTime;
        identify();
    }

    //API METHODS
    public void switchOn(){homeDeviceState.switchOn(this); update();};
    public void switchOff(){homeDeviceState.switchOff(this); update();}
    public void pause(){homeDeviceState.pause(this); update();};
    public void use(Person person){
        this.persons.add(person);
        homeDeviceState.use(person, this);
        update();
    };
    public void repair(Event event, Person person)  {
        if (homeDeviceState instanceof BrokenState && this.documentation == null) {
            try {
                Documentation doc = new Documentation(this);
                doc.generateDocumentation();
                this.documentation = doc;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        homeDeviceState.repair(person, this);

        person.handle(ActivityFactory.createActivity(
                person,
                event,
                switch (this.getBrokennessLevel()){
                    case FINE -> ActivityType.TRY_TO_FIX_IT_YOURSELF;
                    case MEDIUM -> ActivityType.CALL_GRANDFATHER_TO_HELP_REPAIR;
                    case HARDCORE -> ActivityType.THROW_THE_DEVICE_IN_THE_TRASH;})
        );

        update();
    };

    public void breakDevice(){
        homeDeviceState._break(this);
        update();
    };


    public void setCurrentConsumption(SourceType sourceType, double currentConsumption){
        this.currentConsumption.put(sourceType, currentConsumption);
    }
    public double getCurrentConsumption(SourceType sourceType){
        return currentConsumption.get(sourceType);
    }

    //TODO update working hours and check life time
    public void update(){
        super.update();
        this.workingHours++;
        if(this.workingHours > this.lifeTimeInYear){
            this.breakDown();
        }
        ConsumptionCollection.getInstance().update(this);
    }

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

    public String accept(SmartHomeVisitor visitor){
        return visitor.visitHomeDevice(this);
    };

    //FUNCTIONAL METHODS
    protected abstract void enable();
    protected abstract void goIntoIddleMode();
    protected abstract void run();
    protected void disable(){
        for (Iterator<SourceType> it = currentConsumption.keys().asIterator(); it.hasNext(); ) {
            SourceType sourceType = it.next();
            setCurrentConsumption(sourceType, DEVICE_OFF_STATE_ELECTRICITY);
        }
    }
    protected void breakDown(){
        setBrokennessLevel(Utils.getRandomBrokennessLevel());
        for (Iterator<SourceType> it = currentConsumption.keys().asIterator(); it.hasNext(); ) {
            SourceType sourceType = it.next();
            setCurrentConsumption(sourceType, DEVICE_BROKEN_STATE);
        }
    }


    @Override
    public boolean isNull(){
        return false;
    }

    protected abstract void identify();

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setHomeDeviceState(HomeDeviceState homeDeviceState) {
        this.homeDeviceState = homeDeviceState;
    }


}
