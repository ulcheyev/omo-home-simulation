package cvut.omo.event.event_type;

import static cvut.omo.entity.activity.ActivityType.*;

import cvut.omo.entity.activity.ActivityType;
import cvut.omo.home_structure.room_builder.RoomName;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public enum HomeEvent implements EventType {

    /*
     *STUB - IS NOT IN ROOM -> IN HOME IN GENERAL (f.e STORM)
     *COMMON - DOES NOT MATTER IN WHICH ROOM (f.e bulb burned out)
     */

    KNOCK_ON_THE_DOOR(RoomName.VESTIBULE, EventType.EventImportance.NOT_EMERGENCY, CONTROL_THE_DOOR, OPEN_THE_DOR),
    FIRE_ALARM_WENT_OFF(RoomName.COMMON, EventType.EventImportance.EMERGENCY,DEVICE_FIRE_SENSOR_OFF, CALL_THE_RESCUE_SERVICE),
    BULB_BURNED_OUT(RoomName.COMMON, EventType.EventImportance.EMERGENCY, CHANGE_BULB),
    WATER_LEAK_ALARM_WENT_OFF(RoomName.COMMON, EventType.EventImportance.EMERGENCY,DEVICE_WATER_LEAK_SENSOR_OFF, CALL_THE_RESCUE_SERVICE),
    STORM(RoomName.STUB, EventType.EventImportance.NOT_EMERGENCY, HOME_POWER_OFF);


    HomeEvent(RoomName roomName, EventType.EventImportance eventImportance, ActivityType...chainToSolve){
        this.roomName = roomName;
        this.chainToSolve = Arrays.asList(chainToSolve);
        this.eventImportance = eventImportance;
    }

    @Getter
    private final EventType.EventImportance eventImportance;

    @Getter
    private final List<ActivityType> chainToSolve;

    @Getter
    private final RoomName roomName;

    public enum Importance {
        EMERGENCY,
        NOT_EMERGENCY
    }
}
