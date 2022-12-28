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

    KNOCK_ON_THE_DOOR(RoomName.VESTIBULE, CONTROL_THE_DOOR, OPEN_THE_DOR),
    FIRE_ALARM_WENT_OFF(RoomName.COMMON, DEVICE_FIRE_SENSOR_OFF, CALL_THE_RESCUE_SERVICE),
    BULB_BURNED_OUT(RoomName.COMMON,  CHANGE_BULB),
    RAIN(RoomName.STUB, HOME_POWER_OFF, DEVICE_CURCUIT_BREAKER_ON);


    HomeEvent(RoomName roomName,  ActivityType...chainToSolve){
        this.roomName = roomName;
        this.chainToSolve = Arrays.asList(chainToSolve);
    }

    @Getter
    private final List<ActivityType> chainToSolve;

    @Getter
    private final RoomName roomName;

}
