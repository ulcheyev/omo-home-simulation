package cvut.omo.entity.activity;

import cvut.omo.device.*;
import cvut.omo.entity.ResponsibleType;
import cvut.omo.entity.person.FamilyRoleType;
import cvut.omo.entity.pet.PetType;
import static cvut.omo.entity.person.FamilyRoleType.*;
import static cvut.omo.entity.pet.PetType.*;

import cvut.omo.home_structure.room_builder.RoomName;
import lombok.Getter;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum ActivityType {

    /*
    * RoomName.STUB -> Activity does not need specified room to self-execute
    * RoomName.COMMON -> Activity need specified rooms, but depends on event room
    *
    * */

    //DEVICE
    HOME_POWER_OFF(List.of(RoomName.STUB), CircuitBreaker.class,  DeviceActivity.class, Device.CIRCUIT_BREAKER),

    //PERSON
    PET_A_PET(List.of(RoomName.COMMON),null, BaseActivity.class , FamilyRoleType.class.getEnumConstants()),
    TAKE_A_WALK_WITH_PET(List.of(RoomName.STUB), null, BaseActivity.class, DAUGHTER, SON),

    //ON
    DEVICE_FRIDGE_ON(List.of(RoomName.KITCHEN),Fridge.class, DeviceActivity.class),
    DEVICE_COMPUTER_ON(RoomType.CHILL.getRooms(), Computer.class, DeviceActivity.class),
    DEVICE_OVEN_ON(List.of(RoomName.KITCHEN), Oven.class, DeviceActivity.class),
    DEVICE_WASHING_MACHINE_ON(List.of(RoomName.BATHROOM), WashingMachine.class, DeviceActivity.class),
    DEVICE_WATER_LEAK_SENSOR_ON(RoomType.getAll(), WaterLeakSensor.class, DeviceActivity.class, FATHER, GRANDFATHER),
    DEVICE_FIRE_SENSOR_ON(RoomType.getAll(), FireSensor.class, DeviceActivity.class, FATHER, GRANDFATHER),
    DEVICE_TV_ON(RoomType.CHILL.getRooms(), TV.class, DeviceActivity.class),

    //OFF Device -> CONF THE SAME AS ON
    DEVICE_FRIDGE_OFF(DEVICE_FRIDGE_ON),
    DEVICE_COMPUTER_OFF(DEVICE_COMPUTER_ON),
    DEVICE_OVEN_OFF(DEVICE_OVEN_ON),
    DEVICE_WASHING_MACHINE_OFF(DEVICE_WASHING_MACHINE_ON),
    DEVICE_WATER_LEAK_SENSOR_OFF(DEVICE_WATER_LEAK_SENSOR_ON),
    DEVICE_FIRE_SENSOR_OFF(DEVICE_FIRE_SENSOR_ON),
    DEVICE_TV_OFF(DEVICE_TV_ON),

    //RUN DEVICES -> CONF THE SAME AS ON
    DEVICE_FRIDGE_RUN(DEVICE_FRIDGE_ON),
    DEVICE_COMPUTER_RUN(DEVICE_COMPUTER_ON),
    DEVICE_OVEN_RUN(DEVICE_OVEN_ON),
    DEVICE_WASHING_MACHINE_RUN(DEVICE_WASHING_MACHINE_ON),

    //REPAIR
    DEVICE_REPAIR(List.of(RoomName.COMMON), null, DeviceActivity.class,  FATHER, GRANDFATHER),
    CHANGE_BULB(List.of(RoomName.COMMON), null, BaseActivity.class, FATHER, GRANDFATHER),

    //OTHER
    CONTROL_THE_DOOR(List.of(RoomName.VESTIBULE), BaseActivity.class, null),
    CALL_THE_RESCUE_SERVICE(List.of(RoomName.STUB), FireSensor.class, null),
    OPEN_THE_DOR(List.of(RoomName.VESTIBULE), BaseActivity.class, null),

    //PERSON AND PETS
    BREAK_DEVICE(RoomType.getAll(),BaseActivity.class,  null ,PetType.class.getEnumConstants()),
    RELOCATE(List.of(RoomName.STUB), RelocateActivity.class, null, FamilyRoleType.class.getEnumConstants(), PetType.class.getEnumConstants());

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

//    STUB(BaseActivity.class,RoomType.STUB,  null, FamilyRoleType.class.getEnumConstants()),

    //
//    //PETS
//    SAY(BaseActivity.class, PetType.class.getEnumConstants()),
//    PET_SLEEP(BaseActivity.class, PetType.class.getEnumConstants()),
//    PET_EAT(BaseActivity.class, PetType.class.getEnumConstants()),
//    PLAY_WITH_TOY(BaseActivity.class, PetType.class.getEnumConstants()),

//


    ActivityType(List<RoomName > roomNames, Class<? extends HomeDevice> use, Class<? extends Activity> solver, ResponsibleType...roles) {
        this.roomNames = roomNames;
        toUse = use;
        this.solver = solver;
        if (roles.length == 0) {
            defaultConfig();
        } else {
            responsibles.addAll(Arrays.asList(roles));
        }
    }

    ActivityType(List<RoomName > roomNames, Class<? extends Activity> solver, Class<? extends HomeDevice> use, ResponsibleType[]... roles){
        this.roomNames = roomNames;
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

    ActivityType(ActivityType activityType){
        this.roomNames = activityType.getRoomNames();
        toUse = activityType.getToUse();
        this.solver = activityType.getSolver();
        if (activityType.getResponsibles().size() == 0) {
            defaultConfig();
        } else {
            responsibles.addAll(activityType.getResponsibles());
        }
    }

    @Getter
    private final List<ResponsibleType> responsibles = new ArrayList<>();

    @Getter
    private final Class<? extends Activity> solver;

    @Getter
    private final Class<? extends HomeDevice> toUse;

    @Getter
    private List<RoomName> roomNames;


    private void defaultConfig(){
        responsibles.addAll(Arrays.asList(FamilyRoleType.class.getEnumConstants()));
    }


    public enum Device implements ResponsibleType{

        CIRCUIT_BREAKER(CircuitBreaker.class);

        Device(Class<? extends HomeDevice> clazz) {
            this.clazz = clazz;
        }

        @Getter
        private final Class<? extends HomeDevice> clazz;
        @Override
        public boolean isNull() {
            return false;
        }
    }

}
