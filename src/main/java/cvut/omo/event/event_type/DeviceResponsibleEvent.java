package cvut.omo.event.event_type;

import cvut.omo.device.FireSensor;
import cvut.omo.device.HomeDevice;
import cvut.omo.device.Sensor;
import cvut.omo.device.WaterLeakSensor;
import cvut.omo.entity.activity.ActivityType;
import static cvut.omo.entity.activity.ActivityType.*;
import static java.util.List.of;
import lombok.Getter;
import org.reflections.Reflections;

import java.util.List;
import java.util.Set;

public enum DeviceResponsibleEvent implements EventType {

    /*
    * EVENTS DEPEND ON HOME DEVICE LOCATION, DONT NEED TO SPECIFY ROOM
    *
    * */

    DEVICE_BROKEN(of(DEVICE_BREAK, READ_A_DOCUMENTATION, DEVICE_REPAIR)),
    FIRE_SENSOR_ALARM(of(DEVICE_FIRE_SENSOR_ON, DEVICE_FIRE_SENSOR_RUN, CALL_GRANDPA_FOR_HELP), FireSensor.class),
    WATER_LEAK_SENSOR_ALARM(of(DEVICE_WATER_LEAK_SENSOR_ON,DEVICE_WATER_LEAK_SENSOR_RUN , CALL_GRANDPA_FOR_HELP), WaterLeakSensor.class);

    @Getter
    private final Set<Class <? extends HomeDevice>> homeDevices;

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
        Reflections reflections = new Reflections("cvut.omo.device");
        return reflections.getSubTypesOf(HomeDevice.class);
    }

    private final List<ActivityType> chainToSolve;

    @Override
    public List<ActivityType> getChainToSolve() {
        return this.chainToSolve;
    }


}
