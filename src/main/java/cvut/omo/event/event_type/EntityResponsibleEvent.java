package cvut.omo.event.event_type;

import cvut.omo.entity.Responsible;
import static cvut.omo.entity.activity.ActivityType.*;
import cvut.omo.entity.activity.ActivityType;
import cvut.omo.entity.person.Person;
import cvut.omo.entity.pet.Pet;
import cvut.omo.home_structure.room_builder.RoomName;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public enum EntityResponsibleEvent implements EventType {

    //PERSON
    PERSON_NEED_EAT(Person.class,  DEVICE_FRIDGE_RUN, DEVICE_FRIDGE_PAUSE, MAKE_TEA, PERSON_EAT),
    NEED_WATCH_TV(Person.class, DEVICE_TV_RUN, DEVICE_TV_OFF),
    NEED_COOKING(Person.class, DEVICE_FRIDGE_RUN, DEVICE_OVEN_RUN, DEVICE_FRIDGE_OFF, DEVICE_OVEN_OFF),
    NEED_PLAY_COMPUTER(Person.class, DEVICE_COMPUTER_RUN, DEVICE_COMPUTER_OFF),
    NEED_WASH_CLOTHES(Person.class,  DEVICE_WASHING_MACHINE_RUN, DEVICE_WASHING_MACHINE_OFF),
    PERSON_WANTS_TO_PLAY_TETRIS(Person.class, DEVICE_COMPUTER_ON, CALL_GRANDPA_FOR_HELP),
    PERSON_NEED_SLEEP(Person.class, PERSON_SLEEP),

    //PET
    NEED_GO_FOR_A_WALK(Pet.class,  TAKE_A_WALK_WITH_PET),
    PET_NEED_PET(Pet.class,  PET_A_PET),
    PET_NEED_EAT(Pet.class,  PET_A_PET, DEVICE_FRIDGE_RUN, FEED_PET),
    NEED_SAY(Pet.class, PET_SAY),
    PET_NEED_SLEEP(Pet.class, PET_SLEEP);

    EntityResponsibleEvent(Class<? extends Responsible> clazz, ActivityType...chainToSolve){
        this.clazz = clazz;
        this.chainToSolve = Arrays.asList(chainToSolve);
    }


    @Getter
    private final Class<? extends Responsible> clazz;

    @Getter
    private final List<ActivityType> chainToSolve;

}
