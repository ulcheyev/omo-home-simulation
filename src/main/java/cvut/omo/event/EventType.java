package cvut.omo.event;


import cvut.omo.entity.Responsible;
import cvut.omo.entity.activity.ActivityType;
import cvut.omo.home_structure.RoomType;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public interface EventType {

    List<ActivityType> getChainToSolve();
    EventImportance getEventImportance();


//    DEVICE_BROKE(EventImportance.NOT_EMERGENCY),
//    POWER_FAILURE(false, EventImportance.EMERGENCY, ActivityType.POWER_OFF),
//    PERSON_NEED_LOL(true ,EventImportance.NOT_EMERGENCY, ActivityType.DEVICE_FRIDGE_ON);


//    PET_NEED_EAT(EventImportance.NOT_EMERGENCY),
//    PERSON_NEED_SLEEP(EventImportance.EMERGENCY),
//    PET_NEED_SLEEP(EventImportance.EMERGENCY),
//    PERSON_NEED_SPORT(EventImportance.NOT_EMERGENCY),
//    PET_NEED_GO_FOR_A_WALK(EventImportance.NOT_EMERGENCY),

//    HOME_NEED_CLEAN(EventImportance.NOT_EMERGENCY);
//    KNOCK_ON_THE_DOOR(EventImportance.NOT_EMERGENCY),

//    STORM(EventImportance.EMERGENCY, ActivityType.POWER_OFF),
//    RAIN(EventImportance.EMERGENCY, ActivityType.POWER_OFF),
//    ALARM(EventImportance.EMERGENCY, ActivityType.POWER_OFF);
//
//    EventType(boolean responsible, EventImportance eventImportance, ActivityType...chainToSolve){
//        this.hasResponsible = responsible;
//        this.chainToSolve = Arrays.asList(chainToSolve);
//        this.eventImportance = eventImportance;
//    }
//
//
//
//    @Getter
//    private boolean hasResponsible;
//
//
//
     enum EventImportance {
        EMERGENCY,
        NOT_EMERGENCY
    }
}
