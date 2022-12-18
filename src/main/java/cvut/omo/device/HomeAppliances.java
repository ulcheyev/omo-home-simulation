package cvut.omo.device;

import cvut.omo.app_utils.Constants;
import cvut.omo.data_collections.consumption.ConsumptionCollection;
import cvut.omo.home_structure.Room;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Setter
@Getter
@AllArgsConstructor
@ToString
public abstract class HomeAppliances implements HomeDevice{

    protected Set<SourceType> sourceTypes;

    protected double lifeTimeInYear;
    protected double workingHours;

    protected Room room;

    protected Documentation documentation;

    //TODO
    protected HomeDeviceState homeDeviceState;

    public HomeAppliances(double lifeTime){
        sourceTypes = new HashSet<>();
        this.lifeTimeInYear = lifeTime;
        identify();
    }

    //TODO state. Change current consumption depends on state. Washing machine has water consumption also.
    public void turnOn(){}
    public void turnOff(){}
    public void pause(){}
    public void stop(){}


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
