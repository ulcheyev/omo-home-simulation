package cvut.omo.event.event_type;

import cvut.omo.entity.Responsible;
import static cvut.omo.entity.activity.ActivityType.*;
import cvut.omo.entity.activity.ActivityType;
import cvut.omo.entity.person.Person;
import cvut.omo.entity.pet.Pet;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public enum EntityResponsibleEvent implements EventType {

    NEED_EAT(Person.class, EventType.EventImportance.NOT_EMERGENCY, DEVICE_FRIDGE_ON),
    NEED_WATCH_TV(Person.class, EventType.EventImportance.NOT_EMERGENCY, DEVICE_TV_ON),
    NEED_COOKING(Person.class, EventType.EventImportance.NOT_EMERGENCY, DEVICE_OVEN_ON),
    NEED_PLAY_COMPUTER(Person.class, EventType.EventImportance.NOT_EMERGENCY, DEVICE_COMPUTER_ON),
    NEED_WASH_CLOTHES(Person.class, EventType.EventImportance.NOT_EMERGENCY, DEVICE_WASHING_MACHINE_ON),

    NEED_GO_FOR_A_WALK(Pet.class, EventType.EventImportance.NOT_EMERGENCY, TAKE_A_WALK_WITH_PET),
    PET_NEED_PET(Pet.class, EventType.EventImportance.NOT_EMERGENCY, PET_A_PET);

    EntityResponsibleEvent(Class<? extends Responsible> clazz, EventType.EventImportance eventImportance, ActivityType...chainToSolve){
        this.clazz = clazz;
        this.chainToSolve = Arrays.asList(chainToSolve);
        this.eventImportance = eventImportance;
    }

    @Getter
    private final EventType.EventImportance eventImportance;

    @Getter
    private final Class<? extends Responsible> clazz;

    @Getter
    private final List<ActivityType> chainToSolve;

    public enum EventImportance {
        EMERGENCY,
        NOT_EMERGENCY
    }
}
