package cvut.omo.event.event_type;

import cvut.omo.entity.Responsible;
import cvut.omo.entity.activity.ActivityType;
import cvut.omo.entity.device.HomeDevice;
import cvut.omo.entity.living.person.Person;
import cvut.omo.entity.living.pet.Pet;
import cvut.omo.home_structure.room_builder.RoomName;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

import static cvut.omo.entity.activity.ActivityType.*;

/**
 * Class represents event type, responsible for which may be {@link Person} or {@link Pet}
 */
public enum LivingEntityResponsibleEvent implements EventType {

    //PERSON

    PERSON_NEED_EAT(Person.class,  DEVICE_FRIDGE_ON, DEVICE_FRIDGE_RUN, DEVICE_FRIDGE_PAUSE, MAKE_TEA, PERSON_EAT),
    NEED_WATCH_TV(Person.class, DEVICE_TV_ON, DEVICE_TV_RUN, DEVICE_TV_OFF),
    NEED_COOKING(Person.class, DEVICE_FRIDGE_ON, DEVICE_FRIDGE_RUN, DEVICE_OVEN_ON, DEVICE_OVEN_RUN, DEVICE_FRIDGE_OFF, DEVICE_OVEN_OFF),
    NEED_PLAY_COMPUTER(Person.class,DEVICE_COMPUTER_ON, DEVICE_COMPUTER_RUN, DEVICE_COMPUTER_OFF),
    NEED_WASH_CLOTHES(Person.class, DEVICE_WASHING_MACHINE_ON, DEVICE_WASHING_MACHINE_RUN, DEVICE_WASHING_MACHINE_OFF),
    PERSON_WANTS_TO_PLAY_TETRIS(Person.class, DEVICE_COMPUTER_ON, DEVICE_COMPUTER_RUN, CALL_GRANDPA_FOR_HELP),
    PERSON_NEED_SLEEP(Person.class, PERSON_SLEEP),

    PERSON_NEED_SPORT_WITH_SKIS(Person.class, USE_SKIS),
    PERSON_NEED_RIDE_A_BIKE(Person.class, USE_BIKE),
    PERSON_NEED_DRIVE_A_CAR(Person.class, USE_CAR),

    //PET
    PET_NEED_GO_FOR_A_WALK(Pet.class,  TAKE_A_WALK_WITH_PET),
    PET_NEED_PET(Pet.class,  PET_A_PET),
    PET_NEED_EAT(Pet.class,  PET_A_PET, DEVICE_FRIDGE_ON, DEVICE_FRIDGE_RUN, FEED_PET),
    PET_NEED_SAY(Pet.class, PET_SAY),
    PET_NEED_SLEEP(Pet.class, PET_SLEEP),
    PET_NEED_PLAY_WITH_TOY(Pet.class, PLAY_WITH_TOY);

    /**
     *
     * @param clazz Responsible class ({@link Person} or {@link Pet})
     * @param chainToSolve a chain of {@link cvut.omo.entity.activity.Activity}
     *                           , in which every {@link cvut.omo.entity.activity.Activity}
     *                           must be executed successfully that the event has been resolved
     */
    LivingEntityResponsibleEvent(Class<? extends Responsible> clazz, ActivityType...chainToSolve){
        this.roomNames = List.of(RoomName.STUB);
        this.clazz = clazz;
        this.chainToSolve = Arrays.asList(chainToSolve);
    }

    @Getter
    private final Class<? extends Responsible> clazz;

    @Getter
    private final List<ActivityType> chainToSolve;

    @Getter
    private final List<RoomName> roomNames;

}
