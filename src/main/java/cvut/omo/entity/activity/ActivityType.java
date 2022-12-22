package cvut.omo.entity.activity;

import cvut.omo.device.*;
import cvut.omo.entity.Responsible;
import cvut.omo.entity.ResponsibleType;
import cvut.omo.entity.person.FamilyRoleType;
import cvut.omo.entity.pet.PetType;
import cvut.omo.event.EventType;
import cvut.omo.home_structure.Room;
import cvut.omo.home_structure.RoomType;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum ActivityType {

    //DEVICE
    POWER_OFF(RoomType.HALL, CircuitBreaker.class,  UseDevice.class, FamilyRoleType.class.getEnumConstants()),

    //PERSON

    //ON | OFF Device
    DEVICE_FRIDGE_ON(RoomType.KITCHEN, Fridge.class, UseDevice.class), DEVICE_FRIDGE_OFF(RoomType.KITCHEN, Fridge.class, UseDevice.class),
    DEVICE_COMPUTER_ON(RoomType.STUB, Computer.class, UseDevice.class), DEVICE_COMPUTER_OFF(RoomType.STUB, Computer.class, UseDevice.class),
    DEVICE_OVEN_ON(RoomType.KITCHEN, Oven.class, UseDevice.class), DEVICE_OVEN_OFF(RoomType.KITCHEN, Oven.class, UseDevice.class),
    DEVICE_WASHING_MACHINE_ON(RoomType.BATHROOM, WashingMachine.class, UseDevice.class), DEVICE_WASHING_MACHINE_OFF(RoomType.BATHROOM, WashingMachine.class, UseDevice.class),
    DEVICE_WATER_LEAK_SENSOR_ON(RoomType.STUB, WaterLeakSensor.class, UseDevice.class), DEVICE_WATER_LEAK_SENSOR_OFF(RoomType.STUB, WaterLeakSensor.class, UseDevice.class),
    DEVICE_FIRE_SENSOR_ON(RoomType.STUB, FireSensor.class, UseDevice.class), DEVICE_FIRE_SENSOR_OFF(RoomType.STUB, FireSensor.class, UseDevice.class),
    DEVICE_TV_ON(RoomType.STUB, TV.class, UseDevice.class), DEVICE_TV_OFF(RoomType.STUB, TV.class, UseDevice.class),

    //RUN DEVICE
//    FIX_DEVICE(BaseActivity.class, FamilyRoleType.FATHER, FamilyRoleType.GRANDFATHER),

//    USE_BIKE(UseItem.class, FamilyRoleType.class.getEnumConstants()),
//    USE_SKIS(UseItem.class, FamilyRoleType.class.getEnumConstants()),
//    USE_CAR(UseItem.class, FamilyRoleType.FATHER, FamilyRoleType.GRANDFATHER, FamilyRoleType.MOTHER, FamilyRoleType.GRANDMOTHER),
//
//    PERSON_SPORT(BaseActivity.class, FamilyRoleType.class.getEnumConstants()),
//    PERSON_EAT(BaseActivity.class, FamilyRoleType.class.getEnumConstants()),
//    PERSON_SLEEP(BaseActivity.class, FamilyRoleType.class.getEnumConstants()),
//    COOK(BaseActivity.class, FamilyRoleType.MOTHER),
//    CLEAN_HOME(BaseActivity.class, FamilyRoleType.MOTHER, FamilyRoleType.DAUGHTER),
//    LOOK_THE_DOCUMENTATION(BaseActivity.class, FamilyRoleType.FATHER, FamilyRoleType.GRANDFATHER),
//    MAKE_TEA(BaseActivity.class, FamilyRoleType.class.getEnumConstants()),
//    WALK_THE_PET(BaseActivity.class, FamilyRoleType.DAUGHTER, FamilyRoleType.SON),
//    WATCH_SERIALS(BaseActivity.class, FamilyRoleType.class.getEnumConstants()),
//    CALL_FIREFIGHTERS(BaseActivity.class, FamilyRoleType.FATHER, FamilyRoleType.GRANDFATHER, FamilyRoleType.MOTHER, FamilyRoleType.GRANDMOTHER),
//    PLAY_COMPUTER(BaseActivity.class, FamilyRoleType.DAUGHTER, FamilyRoleType.SON),
    CONTROL_THE_DOOR(BaseActivity.class,RoomType.HALL,  null, FamilyRoleType.class.getEnumConstants()),
    CALL_THE_RESCUE_SERVICE(RoomType.HALL, FireSensor.class, null),
//    STUB(BaseActivity.class,RoomType.STUB,  null, FamilyRoleType.class.getEnumConstants()),

    //
//    //PETS
//    SAY(BaseActivity.class, PetType.class.getEnumConstants()),
//    PET_SLEEP(BaseActivity.class, PetType.class.getEnumConstants()),
//    PET_EAT(BaseActivity.class, PetType.class.getEnumConstants()),
//    PLAY_WITH_TOY(BaseActivity.class, PetType.class.getEnumConstants()),
//
//    //PERSON AND PETS
//    BREAK_DEVICE(BaseActivity.class, FamilyRoleType.class.getEnumConstants(), PetType.class.getEnumConstants()),
    RELOCATE(Relocate.class, RoomType.STUB, null, FamilyRoleType.class.getEnumConstants(), PetType.class.getEnumConstants());


    ActivityType(RoomType roomType, Class<? extends HomeDevice> use, Class<? extends Activity> solver, ResponsibleType...roles) {
        this.roomType = roomType;
        toUse = use;
        this.solver = solver;
        if (roles.length == 0) {
            defaultConfig();
        } else {
            responsibles.addAll(Arrays.asList(roles));
        }
    }

    ActivityType(Class<? extends Activity> solver, RoomType roomType, Class<? extends HomeDevice> use, ResponsibleType[]... roles){
        this.roomType = roomType;
        toUse = use;
        this.solver = solver;
        if (roles.length == 0) {
            defaultConfig();
        } else {
            for(ResponsibleType[] responsibleType : roles){
                responsibles.addAll(List.of(responsibleType));
            }
        }
    }

    @Getter
    private final List<ResponsibleType> responsibles = new ArrayList<>();

    @Getter
    private final Class<? extends Activity> solver;

    @Getter
    private RoomType roomType;

    private void defaultConfig(){
        responsibles.addAll(Arrays.asList(FamilyRoleType.class.getEnumConstants()));
    }

    @Getter
    private final Class<? extends HomeDevice> toUse;

    private enum Device implements ResponsibleType{
        CIRCUIT_BREAKER
    }

}
