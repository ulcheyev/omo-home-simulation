package cvut.omo.device;

import cvut.omo.app_utils.Utils;
import cvut.omo.data_collections.consumption.ConsumptionCollection;
import cvut.omo.data_collections.consumption.ConsumptionData;
import cvut.omo.data_collections.visitor.SmartHomeVisitor;
import cvut.omo.device.documentation.BrokennessLevel;
import cvut.omo.device.documentation.Documentation;
import cvut.omo.entity.Responsible;
import cvut.omo.entity.activity.Activity;
import cvut.omo.entity.nulls.NullResponsibleType;
import cvut.omo.entity.person.Person;
import cvut.omo.home_structure.home_builder.Home;
import cvut.omo.home_structure.room_builder.Room;
import lombok.Getter;

import java.io.IOException;
import java.util.*;

import static cvut.omo.app_utils.Constants.DEVICE_BROKEN_STATE;
import static cvut.omo.app_utils.Constants.DEVICE_OFF_STATE;


@Getter
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

    protected boolean isEnabled;

    public HomeAppliances(double lifeTime){
        super(new NullResponsibleType());
        currentConsumption = new Hashtable<>();
        homeDeviceState = new OffState(this);
        persons = new ArrayList<>();
        this.lifeTimeInYear = lifeTime;
        isEnabled = false;
        identify();
    }

    //TODO
    //API METHODS
    public void clickOn(){
        if(Home.INSTANCE.isPowerEnable()) homeDeviceState.switchOn(this);
    };
    public void clickOff(){
        if(Home.INSTANCE.isPowerEnable())
        homeDeviceState.switchOff(this);}

    public void clickPause(){
        if(Home.INSTANCE.isPowerEnable())
        homeDeviceState.pause(this);};

    public void clickStart(Person person){
        if(Home.INSTANCE.isPowerEnable())
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
    public void update() throws InterruptedException {
        super.update();
        this.workingHours++;
        ConsumptionCollection.getInstance().update(this);
    }

    public Documentation getDocumentation(){
        if (homeDeviceState instanceof BrokenState && this.documentation == null){
            try {
                Documentation doc = new Documentation(this);
                doc.generateDocumentation();
                this.documentation = doc;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return documentation;
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
    protected abstract void goIntoPauseMode();
    protected abstract void run();
    protected void disable(){
        for (Iterator<SourceType> it = currentConsumption.keys().asIterator(); it.hasNext(); ) {
            SourceType sourceType = it.next();
            setCurrentConsumption(sourceType, DEVICE_OFF_STATE);
        }
    }
    protected void breakDown(){
        for (Iterator<SourceType> it = currentConsumption.keys().asIterator(); it.hasNext(); ) {
            SourceType sourceType = it.next();
            setCurrentConsumption(sourceType, DEVICE_BROKEN_STATE);
            this.setBrokennessLevel(Utils.getRandomBrokennessLevel());
        }
    }

    @Override
    public boolean isNull(){
        return false;
    }

    protected abstract void identify();

    public void setCurrentConsumption(Dictionary<SourceType, Double> currentConsumption) {
        this.currentConsumption = currentConsumption;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public void setLifeTimeInYear(double lifeTimeInYear) {
        this.lifeTimeInYear = lifeTimeInYear;
    }

    public void setWorkingHours(double workingHours) {
        this.workingHours = workingHours;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setDocumentation(Documentation documentation) {
        this.documentation = documentation;
    }

    public void setConsumptionData(ConsumptionData consumptionData) {
        this.consumptionData = consumptionData;
    }

    public void setHomeDeviceState(HomeDeviceState homeDeviceState) {
        this.homeDeviceState = homeDeviceState;
    }

    public void setBrokennessLevel(BrokennessLevel brokennessLevel) {
        this.brokennessLevel = brokennessLevel;
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }
}
