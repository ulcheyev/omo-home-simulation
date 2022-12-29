package cvut.omo.device;

import cvut.omo.app_utils.Constants;
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

import static cvut.omo.app_utils.Constants.*;


/**
 *
 */
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

    /**
     * @param lifeTime
     */
    public HomeAppliances(double lifeTime){
        super(new NullResponsibleType());
        currentConsumption = new Hashtable<>();
        homeDeviceState = new DisconnectedState(this);
        persons = new ArrayList<>();
        this.lifeTimeInYear = lifeTime;
        isEnabled = false;
        identify();
    }

    /**
     *
     */
    //TODO
    //API METHODS
    public void connect(){
        homeDeviceState.connect(this);
    }

    /**
     *
     */
    public void disconnect(){
        setCurrentConsumption(SourceType.ENERGY, DEVICE_DISCONNECTED_STATE);
        homeDeviceState = new DisconnectedState(this);
    }

    /**
     *
     */
    public void clickOn(){homeDeviceState.switchOn(this); update();};

    /**
     *
     */
    public void clickOff(){homeDeviceState.switchOff(this); update();}

    /**
     *
     */
    public void clickPause(){homeDeviceState.pause(this); update();};

    /**
     * @param person
     */
    public void clickStart(Person person){
        this.persons.add(person);
        homeDeviceState.use(person, this);
        update();
    };

    /**
     * @param person
     */
    public void repair(Person person){
        homeDeviceState.repair(person, this);
        update();
    };

    /**
     *
     */
    public void breakDevice(){
        homeDeviceState._break(this);
        update();
    };

    /**
     * @param sourceType
     * @param currentConsumption
     */
    public void setCurrentConsumption(SourceType sourceType, double currentConsumption){
        this.currentConsumption.put(sourceType, currentConsumption);
    }

    /**
     * @param sourceType
     * @return
     */
    public double getCurrentConsumption(SourceType sourceType){
        return currentConsumption.get(sourceType);
    }

    /**
     *
     */
    //TODO update working hours and check life time
    public void update(){
        super.update();
        this.workingHours++;
        ConsumptionCollection.getInstance().update(this);
    }

    /**
     * @return
     */
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

    /**
     * @return
     */
    public boolean isNotConsume(){
        return currentConsumption.get(SourceType.NOT_CONSUME) != null;
    }

    /**
     * @return
     */
    public Set<SourceType> getSourceTypes(){
        Set<SourceType> sourceTypes = new HashSet<>();
        for (Iterator<SourceType> it = currentConsumption.keys().asIterator(); it.hasNext(); ) {
            SourceType sourceType = it.next();
            sourceTypes.add(sourceType);
        }
        return sourceTypes;
    }

    /**
     * @param visitor
     * @return
     */
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
            setCurrentConsumption(sourceType, DEVICE_CONNECTED_ELECTRICITY_CONSUMPTION);
        }
    }
    protected void breakDown(){
        for (Iterator<SourceType> it = currentConsumption.keys().asIterator(); it.hasNext(); ) {
            SourceType sourceType = it.next();
            setCurrentConsumption(sourceType, DEVICE_BROKEN_STATE);
            this.setBrokennessLevel(Utils.getRandomBrokennessLevel());
        }
    }
    protected void connectDevice(){
        setCurrentConsumption(SourceType.ENERGY, DEVICE_CONNECTED_ELECTRICITY_CONSUMPTION);
    }

    /**
     * @return
     */
    @Override
    public boolean isNull(){
        return false;
    }

    protected abstract void identify();

    /**
     * @param currentConsumption
     */
    public void setCurrentConsumption(Dictionary<SourceType, Double> currentConsumption) {
        this.currentConsumption = currentConsumption;
    }

    /**
     * @param persons
     */
    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    /**
     * @param lifeTimeInYear
     */
    public void setLifeTimeInYear(double lifeTimeInYear) {
        this.lifeTimeInYear = lifeTimeInYear;
    }

    /**
     * @param workingHours
     */
    public void setWorkingHours(double workingHours) {
        this.workingHours = workingHours;
    }

    /**
     * @param room
     */
    public void setRoom(Room room) {
        this.room = room;
    }

    /**
     * @param documentation
     */
    public void setDocumentation(Documentation documentation) {
        this.documentation = documentation;
    }

    /**
     * @param consumptionData
     */
    public void setConsumptionData(ConsumptionData consumptionData) {
        this.consumptionData = consumptionData;
    }

    /**
     * @param homeDeviceState
     */
    public void setHomeDeviceState(HomeDeviceState homeDeviceState) {
        this.homeDeviceState = homeDeviceState;
    }

    /**
     * @param brokennessLevel
     */
    public void setBrokennessLevel(BrokennessLevel brokennessLevel) {
        this.brokennessLevel = brokennessLevel;
    }

    /**
     * @param isEnabled
     */
    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }
}
