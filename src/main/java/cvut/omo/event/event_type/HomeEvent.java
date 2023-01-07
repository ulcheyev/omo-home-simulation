package cvut.omo.event.event_type;

import cvut.omo.entity.activity.ActivityType;
import cvut.omo.entity.living.person.Person;
import cvut.omo.entity.living.pet.Pet;
import cvut.omo.home_structure.room_builder.RoomName;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

import static cvut.omo.entity.activity.ActivityType.*;

/**
 * Class represents event type, which can be appeared in home in general.
 */
public enum HomeEvent implements EventType {

    KNOCK_ON_THE_DOOR(RoomName.VESTIBULE, CONTROL_THE_DOOR, OPEN_THE_DOR),
    BULB_BURNED_OUT(RoomName.COMMON,  CHANGE_BULB),
    RAIN(RoomName.STUB, DEVICE_CURCUIT_BREAKER_ON, DEVICE_CURCUIT_BREAKER_OFF),
    WATER_LEAK_ALARM_WENT_OFF(RoomName.COMMON ,DEVICE_WATER_LEAK_SENSOR_OFF, CALL_THE_RESCUE_SERVICE),
    THUNDERSTORM(RoomName.STUB, DEVICE_CURCUIT_BREAKER_ON, DEVICE_CURCUIT_BREAKER_OFF),
    TV_STAR_HAS_DIED(RoomName.STUB, DEVICE_TV_ON, PERSON_CRY),
    NEED_TO_TIDY_UP_THE_HOUSE(RoomName.COMMON, TIDY_UP),
    PREPARE_A_HOUSE_FOR_CHRISTMAS(RoomName.STUB, DECORATE_A_CHRISTMAS_TREE),
    INFO(RoomName.STUB);

    /**
     *
     * @param roomName {@link RoomName} where event can be appeared
     * @param chainToSolve a chain of {@link cvut.omo.entity.activity.Activity}
     *                                , in which every {@link cvut.omo.entity.activity.Activity}
     *                                 must be executed successfully that the event has been resolved
     */
    HomeEvent(RoomName roomName,  ActivityType...chainToSolve){
        this.roomName = roomName;
        this.chainToSolve = Arrays.asList(chainToSolve);
    }

    @Getter
    private final List<ActivityType> chainToSolve;

    @Getter
    private final RoomName roomName;

}
