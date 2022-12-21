package cvut.omo.entity;

import cvut.omo.device.CircuitBreaker;
import cvut.omo.device.HomeDevice;
import cvut.omo.entity.person.FamilyRole;
import cvut.omo.entity.person.Person;
import cvut.omo.entity.pet.Pet;
import cvut.omo.entity.pet.PetType;
import cvut.omo.home_structure.Home;
import cvut.omo.home_structure.Room;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum ActivityType {
    //DEVICE
    POWER_OFF(Device.CIRCUIT_BREAKER),

    //PERSON
    USE_DEVICE(FamilyRole.class.getEnumConstants()),
    ON_DEVICE(FamilyRole.class.getEnumConstants()),
    OFF_DEVICE(FamilyRole.class.getEnumConstants()),
    RUN_DEVICE(FamilyRole.class.getEnumConstants()),
    STOP_DEVICE(FamilyRole.class.getEnumConstants()),
    FIX_DEVICE(FamilyRole.FATHER, FamilyRole.GRANDFATHER),

    USE_BIKE(FamilyRole.class.getEnumConstants()),
    USE_SKIS(FamilyRole.class.getEnumConstants()),
    USE_CAR(FamilyRole.FATHER, FamilyRole.GRANDFATHER, FamilyRole.MOTHER, FamilyRole.GRANDMOTHER),

    PERSON_SPORT(FamilyRole.class.getEnumConstants()),
    PERSON_EAT(FamilyRole.class.getEnumConstants()),
    PERSON_SLEEP(FamilyRole.class.getEnumConstants()),
    COOK(FamilyRole.MOTHER),
    CLEAN_HOME(FamilyRole.MOTHER,FamilyRole.DAUGHTER),
    LOOK_THE_DOCUMENTATION(FamilyRole.FATHER, FamilyRole.GRANDFATHER),
    MAKE_TEA(FamilyRole.class.getEnumConstants()),
    WALK_THE_PET(FamilyRole.DAUGHTER, FamilyRole.SON),
    WATCH_SERIALS(FamilyRole.class.getEnumConstants()),
    CALL_FIREFIGHTERS(FamilyRole.FATHER, FamilyRole.GRANDFATHER, FamilyRole.MOTHER, FamilyRole.GRANDMOTHER),
    PLAY_COMPUTER(FamilyRole.DAUGHTER, FamilyRole.SON),
    CONTROL_THE_DOOR(FamilyRole.class.getEnumConstants()),

    //PETS
    SAY(PetType.class.getEnumConstants()),
    PET_SLEEP(PetType.class.getEnumConstants()),
    PET_EAT(PetType.class.getEnumConstants()),
    PLAY_WITH_TOY(PetType.class.getEnumConstants()),

    //PERSON AND PETS
    BREAK_DEVICE(FamilyRole.class.getEnumConstants(), PetType.class.getEnumConstants());


    ActivityType(ResponsibleType...roles){
        responsibles.addAll(Arrays.asList(roles));
    }

    ActivityType(ResponsibleType[]...roles){
        for(ResponsibleType[] responsibleType : roles){
            responsibles.addAll(List.of(responsibleType));
        }
    }

    @Getter
    private final List<ResponsibleType> responsibles = new ArrayList<>();


//    public boolean callResponsible(ResponsibleType responsibleType, Room room) {
//        return Home.relocateResponsible(responsibleType, room);
//    }

    private enum Device implements ResponsibleType{
        CIRCUIT_BREAKER
    }

}
