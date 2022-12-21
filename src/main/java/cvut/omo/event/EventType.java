package cvut.omo.event;

import cvut.omo.entity.ActivityType;
import lombok.Getter;
//import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum EventType {
    DEVICE_BROKE(ActivityType.LOOK_THE_DOCUMENTATION, ActivityType.FIX_DEVICE),
    POWER_FAILURE(ActivityType.POWER_OFF),

    PERSON_NEED_EAT(ActivityType.USE_DEVICE, ActivityType.PERSON_EAT),
    PET_NEED_EAT(ActivityType.PET_EAT),
    PERSON_NEED_SLEEP(ActivityType.PERSON_SLEEP),
    PET_NEED_SLEEP(ActivityType.PET_SLEEP),
    PERSON_NEED_SPORT(ActivityType.USE_SKIS, ActivityType.USE_BIKE, ActivityType.PERSON_SPORT),
    PET_NEED_GO_FOR_A_WALK(ActivityType.WALK_THE_PET),

    HOME_NEED_CLEAN(ActivityType.CLEAN_HOME),
    KNOCK_ON_THE_DOOR(ActivityType.CONTROL_THE_DOOR),

    STORM(ActivityType.POWER_OFF),
    RAIN,
    ALARM(ActivityType.CALL_FIREFIGHTERS);

    EventType(ActivityType... activities){
        chainToSolve.addAll(Arrays.asList(activities));
    }

    @Getter
    private final List<ActivityType> chainToSolve = new ArrayList<>();


}
