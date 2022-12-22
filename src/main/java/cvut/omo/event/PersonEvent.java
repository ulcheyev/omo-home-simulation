package cvut.omo.event;

import cvut.omo.entity.activity.ActivityType;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public enum PersonEvent implements EventType{

    PERSON_NEED_EAT(EventType.EventImportance.NOT_EMERGENCY, ActivityType.DEVICE_FRIDGE_ON),
    PERSON_NEED_WATCH_TV(EventType.EventImportance.NOT_EMERGENCY, ActivityType.DEVICE_TV_ON);

    PersonEvent (EventType.EventImportance eventImportance, ActivityType...chainToSolve){
        this.chainToSolve = Arrays.asList(chainToSolve);
        this.eventImportance = eventImportance;
    }

    @Getter
    private final EventType.EventImportance eventImportance;


    @Getter
    private final List<ActivityType> chainToSolve;

    public enum EventImportance {
        EMERGENCY,
        NOT_EMERGENCY
    }
}
