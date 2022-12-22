package cvut.omo.event;

import cvut.omo.entity.activity.ActivityType;
import cvut.omo.home_structure.RoomType;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public enum HomeEvent implements EventType{

    KNOCK_ON_THE_DOOR(RoomType.HALL, EventType.EventImportance.NOT_EMERGENCY, ActivityType.CONTROL_THE_DOOR),
    FIRE_ALARM_WENT_OFF(RoomType.HALL, EventType.EventImportance.EMERGENCY, ActivityType.CALL_THE_RESCUE_SERVICE);

//    STUB(RoomType.STUB, EventType.EventImportance.NOT_EMERGENCY, ActivityType.STUB);


    HomeEvent(RoomType roomType, EventType.EventImportance eventImportance, ActivityType...chainToSolve){
        this.roomType = roomType;
        this.chainToSolve = Arrays.asList(chainToSolve);
        this.eventImportance = eventImportance;
    }

    @Getter
    private final EventType.EventImportance eventImportance;

    @Getter
    private final List<ActivityType> chainToSolve;

    @Getter
    private final RoomType roomType;

    public enum EventImportance {
        EMERGENCY,
        NOT_EMERGENCY
    }
}
