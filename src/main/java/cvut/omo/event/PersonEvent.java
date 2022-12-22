package cvut.omo.event;

import cvut.omo.entity.activity.ActivityType;
import cvut.omo.entity.person.Person;
import cvut.omo.home_structure.RoomType;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public enum PersonEvent implements EventType{

    NEED_EAT(EventType.EventImportance.NOT_EMERGENCY, ActivityType.DEVICE_FRIDGE_ON),
    NEED_WATCH_TV(EventType.EventImportance.NOT_EMERGENCY, ActivityType.DEVICE_TV_ON),
    NEED_COOKING(EventType.EventImportance.NOT_EMERGENCY, ActivityType.DEVICE_OVEN_ON),
    NEED_PLAY_COMPUTER(EventType.EventImportance.NOT_EMERGENCY, ActivityType.DEVICE_COMPUTER_ON),
    NEED_WASH_CLOTHES(EventType.EventImportance.NOT_EMERGENCY, ActivityType.DEVICE_WASHING_MACHINE_ON);

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
