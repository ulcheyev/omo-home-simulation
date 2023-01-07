package cvut.omo.entity.device;

import cvut.omo.app_utils.Utils;
import cvut.omo.data_collections.consumption.ConsumptionCollection;
import cvut.omo.data_collections.consumption.ConsumptionData;
import cvut.omo.data_collections.visitor.SmartHomeReportVisitor;
import cvut.omo.entity.device.documentation.BrokennessLevel;
import cvut.omo.entity.device.documentation.Documentation;
import cvut.omo.entity.Responsible;
import cvut.omo.entity.activity.ActivityFactory;
import cvut.omo.entity.activity.ActivityType;
import cvut.omo.entity.nulls.NullResponsibleType;
import cvut.omo.entity.living.person.Person;
import cvut.omo.event.Event;
import cvut.omo.home_structure.room_builder.Room;
import lombok.Getter;
import lombok.Setter;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.*;

import static cvut.omo.app_utils.Constants.DEVICE_BROKEN_STATE;
import static cvut.omo.app_utils.Constants.DEVICE_OFF_STATE_ELECTRICITY;

/**
 * Generalizing class for all appliances in the house
 */
@Getter
@Setter
public abstract class HomeAppliances extends Responsible implements HomeDevice{

    protected Dictionary<SourceType, Double> currentConsumption;
    protected double lifeTimeInYear;
    protected double workingHours;
    protected Room room;
    protected Documentation documentation;
    protected ConsumptionData consumptionData;
    protected HomeDeviceState homeDeviceState;
    protected BrokennessLevel brokennessLevel;

    /**
     * Constructor for class
     * @param lifeTime lifetime of current home device
     */
    public HomeAppliances(double lifeTime){
        super(new NullResponsibleType());
        currentConsumption = new Hashtable<>();
        homeDeviceState = new OffState(this);
        this.lifeTimeInYear = lifeTime;
        identify();
    }

    /**
     * Method switches on current device (depends on {@link #homeDeviceState}), then call {@link #update()}
     */
    public void switchOn(){homeDeviceState.switchOn(this); update();};

    /**
     * Method switches off current device (depends on {@link #homeDeviceState}), then call {@link #update()}
     */
    public void switchOff(){homeDeviceState.switchOff(this); update();}

    /**
     * Method pause current device (depends on {@link #homeDeviceState}), then call {@link #update()}
     */
    public void pause(){homeDeviceState.pause(this); update();};

    /**
     * Method allows using current device (depends on {@link #homeDeviceState}), then call {@link #update()}
     * @param person  {@link Person}, which uses current device
     */
    public void use(Person person){
        homeDeviceState.use(person, this);
        update();
    };

    /**
     * Method allows repairing current device (depends on {@link #homeDeviceState}), then call {@link #update()}
     * @param event {@link Event} of this method call
     * @param person {@link Person} which will repair current device
     */
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

    /**
     * Method allows breaking current device (depends on {@link #homeDeviceState}), then call {@link #update()}
     */
    public void breakDevice(){
        homeDeviceState._break(this);
        update();
    };


    /**
     * Changes current consumption of this device
     * @param sourceType source type of device
     * @param currentConsumption new consumption
     */
    public void setCurrentConsumption(SourceType sourceType, double currentConsumption){
        this.currentConsumption.put(sourceType, currentConsumption);
    }

    /**
     * Return current consumption
     * @param sourceType source type of this device which current consumption will return
     * @return {@link #currentConsumption}
     */
    public double getCurrentConsumption(SourceType sourceType){
        return currentConsumption.get(sourceType);
    }

    /**
     * Method updates consumption data in {@link ConsumptionCollection} of this device
     * (depends on {@link #homeDeviceState}).
     * Check {@link #workingHours} of this device. If {@link #workingHours > {@link #lifeTimeInYear}},
     * device will break.
     */
    public void update(){
        super.update();
        this.workingHours++;
        if(this.workingHours > this.lifeTimeInYear){
            this.breakDown();
        }
        ConsumptionCollection.getInstance().update(this);
    }

    /**
     * @return  true, if device has NOT_CONSUME source type.
     */
    public boolean isNotConsume(){
        return currentConsumption.get(SourceType.NOT_CONSUME) != null;
    }

    /**
     * @return set of source types of current device
     */
    public Set<SourceType> getSourceTypes(){
        Set<SourceType> sourceTypes = new HashSet<>();
        for (Iterator<SourceType> it = currentConsumption.keys().asIterator(); it.hasNext(); ) {
            SourceType sourceType = it.next();
            sourceTypes.add(sourceType);
        }
        return sourceTypes;
    }

    @Override
    public Object accept(SmartHomeReportVisitor visitor){
        return visitor.visitHomeDevice(this);
    };

    /**
     * Enable current device. Change current consumption.
     */
    protected abstract void enable();

    /**
     * Disable current device. Change current consumption.
     */
    protected abstract void goIntoIddleMode();

    /**
     * Run current device. Change current consumption.
     */
    protected abstract void run() throws MessagingException, IOException;

    /**
     * Disable current device. Change current consumption.
     */
    protected void disable(){
        for (Iterator<SourceType> it = currentConsumption.keys().asIterator(); it.hasNext(); ) {
            SourceType sourceType = it.next();
            setCurrentConsumption(sourceType, DEVICE_OFF_STATE_ELECTRICITY);
        }
    }

    /**
     * Break current device. Change current consumption.
     */
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

    /**
     * Identifies device. Sets source type and current consumption.
     */
    protected abstract void identify();

    /**
     * Set current device's room.
     * @param room room to set
     */
    public void setRoom(Room room) {
        this.room = room;
    }

    /**
     * Change current device {@link #homeDeviceState}
     * @param homeDeviceState state to set
     */
    public void setHomeDeviceState(HomeDeviceState homeDeviceState) {
        this.homeDeviceState = homeDeviceState;
    }


}
