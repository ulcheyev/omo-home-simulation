package cvut.omo.event.event_type;

import cvut.omo.entity.device.FireSensor;
import cvut.omo.entity.device.HomeDevice;
import cvut.omo.entity.device.WaterLeakSensor;
import cvut.omo.entity.activity.ActivityType;
import lombok.Getter;
import org.reflections.Reflections;

import java.util.List;
import java.util.Set;

import static cvut.omo.entity.activity.ActivityType.*;
import static java.util.List.of;

/**
 * Class represents event type, responsible for which may be {@link HomeDevice}.
 */
public enum DeviceResponsibleEvent implements EventType {

    DEVICE_BROKEN(of(DEVICE_BREAK, READ_A_DOCUMENTATION, DEVICE_REPAIR)),
    FIRE_SENSOR_ALARM(of(DEVICE_FIRE_SENSOR_ON, DEVICE_FIRE_SENSOR_RUN, CALL_GRANDPA_FOR_HELP), FireSensor.class),
    WATER_LEAK_SENSOR_ALARM(of(DEVICE_WATER_LEAK_SENSOR_ON,DEVICE_WATER_LEAK_SENSOR_RUN , CALL_GRANDPA_FOR_HELP), WaterLeakSensor.class);

    @Getter
    private final Set<Class <? extends HomeDevice>> homeDevices;

    /**
     * Constructor for Device Event.
     * @param chainToSolve a chain of {@link cvut.omo.entity.activity.Activity}
     *                     , in which every {@link cvut.omo.entity.activity.Activity}
     *                     must be executed successfully that the event has been resolved
     * @param homeDevices {@link HomeDevice}, which can be {@link cvut.omo.entity.Responsible} for this event
     */
    @SafeVarargs
    DeviceResponsibleEvent(List<ActivityType> chainToSolve, Class <? extends HomeDevice> ... homeDevices) {
        this.chainToSolve = chainToSolve;
        if(homeDevices.length == 0){
            this.homeDevices = defaultConfig();
        }else {
            this.homeDevices = Set.of(homeDevices);
        }
    }

    private Set<Class<? extends HomeDevice>> defaultConfig() {
        Reflections reflections = new Reflections("cvut.omo.entity.device");
        return reflections.getSubTypesOf(HomeDevice.class);
    }

    private final List<ActivityType> chainToSolve;

    /**
     * @return chain to solve this event
     */
    @Override
    public List<ActivityType> getChainToSolve() {
        return this.chainToSolve;
    }


}
