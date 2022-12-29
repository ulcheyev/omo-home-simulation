package cvut.omo.entity.activity;
import cvut.omo.device.*;
import cvut.omo.entity.ResponsibleType;
import cvut.omo.entity.person.FamilyRoleType;
import cvut.omo.entity.pet.PetType;
import static cvut.omo.entity.person.FamilyRoleType.*;
import static java.util.List.of;
import cvut.omo.home_structure.room_builder.RoomName;
import cvut.omo.usable.Usable;
import lombok.Getter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum ActivityType {

    /*
    * RoomName.STUB -> Activity does not need specified room to self-execute
    * RoomName.COMMON -> Activity need specified rooms, but depends on event room/ e.g responsible location room
    *
    * */

    //DEVICE
    HOME_POWER_OFF(of(RoomName.STUB), CircuitBreaker.class, DeviceActivity.class, Device.CIRCUIT_BREAKER),

    //PERSON
    PET_A_PET(of(RoomName.COMMON),null),
    TAKE_A_WALK_WITH_PET(of(RoomName.STUB), null,  DAUGHTER, SON),
    FEED_PET(of(RoomName.COMMON), null),
    PERSON_SLEEP(of(RoomName.BEDROOM), null),

    //ON DEVICE
    DEVICE_FRIDGE_ON(of(RoomName.KITCHEN),Fridge.class, DeviceActivity.class),
    DEVICE_COMPUTER_ON(RoomType.CHILL.getRooms(), Computer.class, DeviceActivity.class),
    DEVICE_OVEN_ON(of(RoomName.KITCHEN), Oven.class, DeviceActivity.class),
    DEVICE_WASHING_MACHINE_ON(of(RoomName.BATHROOM), WashingMachine.class, DeviceActivity.class),
    DEVICE_WATER_LEAK_SENSOR_ON(of(RoomName.KITCHEN, RoomName.BATHROOM), WaterLeakSensor.class, DeviceActivity.class, FATHER, GRANDFATHER),
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
    DEVICE_TV_RUN(DEVICE_TV_ON),
    DEVICE_CURCUIT_BREAKER_RUN(of(RoomName.VESTIBULE), CircuitBreaker.class, DeviceActivity.class, FATHER, GRANDFATHER),

    //PAUSE DEVICES -> CONF THE SAME AS ON
    DEVICE_FRIDGE_PAUSE(DEVICE_FRIDGE_ON),
    DEVICE_COMPUTER_PAUSE(DEVICE_COMPUTER_ON),
    DEVICE_OVEN_PAUSE(DEVICE_OVEN_ON),
    DEVICE_WASHING_MACHINE_PAUSE(DEVICE_WASHING_MACHINE_ON),
    DEVICE_TV_PAUSE(DEVICE_TV_ON),

    //REPAIR
    DEVICE_REPAIR(of(RoomName.COMMON), null, DeviceActivity.class,  FATHER, GRANDFATHER),
    CHANGE_BULB(of(RoomName.COMMON), null, BaseActivity.class, FATHER, GRANDFATHER),

    //OTHER
    CONTROL_THE_DOOR(of(RoomName.VESTIBULE), null),
    CALL_THE_RESCUE_SERVICE(of(RoomName.STUB), FireSensor.class),
    OPEN_THE_DOR(of(RoomName.VESTIBULE), null),
    MAKE_TEA(of(RoomName.KITCHEN),  Fridge.class),
    PERSON_EAT(of(RoomName.KITCHEN),  null),
    PERSON_CRY(of(RoomName.BATHROOM), null, MOTHER, GRANDMOTHER),
    CALL_GRANDPA_FOR_HELP(of(RoomName.BEDROOM, RoomName.CHILDRENS_ROOM), null, BaseActivity.class, GRANDFATHER),
    TIDY_UP(of(RoomName.COMMON), null, BaseActivity.class, MOTHER, DAUGHTER),
    DECORATE_A_CHRISTMAS_TREE(of(RoomName.HALL), null, BaseActivity.class, MOTHER, DAUGHTER, SON),

    //PETS
    PET_SAY(of(RoomName.STUB), BaseActivity.class, null, PetType.class.getEnumConstants()),
    PET_SLEEP(of(RoomName.COMMON), BaseActivity.class, null, PetType.class.getEnumConstants()),
    PET_EAT(of(RoomName.KITCHEN), BaseActivity.class, null, PetType.class.getEnumConstants()),
    PLAY_WITH_TOY(of(RoomName.COMMON), BaseActivity.class, null, PetType.class.getEnumConstants()),

    //PERSON AND PETS
    RELOCATE(of(RoomName.STUB), RelocateActivity.class, null, FamilyRoleType.class.getEnumConstants(), PetType.class.getEnumConstants());
//    USE_BIKE(UseItem.class, FamilyRoleType.class.getEnumConstants()),
//    USE_SKIS(UseItem.class, FamilyRoleType.class.getEnumConstants()),
//    USE_CAR(UseItem.class, FamilyRoleType.FATHER, FamilyRoleType.GRANDFATHER, FamilyRoleType.MOTHER, FamilyRoleType.GRANDMOTHER),
//    PERSON_SPORT(BaseActivity.class, FamilyRoleType.class.getEnumConstants()),
//    PERSON_EAT(BaseActivity.class, FamilyRoleType.class.getEnumConstants()),
//    PERSON_SLEEP(BaseActivity.class, FamilyRoleType.class.getEnumConstants()),
//    COOK(BaseActivity.class, FamilyRoleType.MOTHER),
//    CLEAN_HOME(BaseActivity.class, FamilyRoleType.MOTHER, FamilyRoleType.DAUGHTER),
//    LOOK_THE_DOCUMENTATION(BaseActivity.class, FamilyRoleType.FATHER, FamilyRoleType.GRANDFATHER),
//    WALK_THE_PET(BaseActivity.class, FamilyRoleType.DAUGHTER, FamilyRoleType.SON),
//    WATCH_SERIALS(BaseActivity.class, FamilyRoleType.class.getEnumConstants()),
//    CALL_FIREFIGHTERS(BaseActivity.class, FamilyRoleType.FATHER, FamilyRoleType.GRANDFATHER, FamilyRoleType.MOTHER, FamilyRoleType.GRANDMOTHER),
//    PLAY_COMPUTER(BaseActivity.class, FamilyRoleType.DAUGHTER, FamilyRoleType.SON),

    ActivityType(List<RoomName > roomNames, Class<? extends Usable> use, Class<? extends Activity> solver, ResponsibleType...roles) {
        assignVariables(roomNames, solver, use, roles);
    }

    ActivityType(List<RoomName > roomNames, Class<? extends Activity> solver, Class<? extends HomeDevice> use, ResponsibleType[]... roles){
        assignVariables(roomNames, solver, use);
        if (roles.length == 0) {
            defaultConfig();
        } else {
            for(ResponsibleType[] responsibleTypes: roles){
                responsibles.addAll(of(responsibleTypes));
            }
        }
    }

    ActivityType(List<RoomName > roomNames, Class<? extends HomeDevice> use, ResponsibleType...roles) {
        assignVariables(roomNames, null, use, roles);
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

    private void assignVariables(List<RoomName > roomNames, Class<? extends Activity> solver, Class<? extends Usable> use, ResponsibleType... roles){
        this.roomNames = roomNames;
        toUse = use;
        if(solver != null){
            this.solver = solver;

        }
        else{
           this. solver = BaseActivity.class;
        }
        if (roles.length == 0) {
            defaultConfig();
        } else {
            responsibles.addAll(Arrays.asList(roles));
        }
    }

    private void defaultConfig(){
        responsibles.addAll(Arrays.asList(FamilyRoleType.class.getEnumConstants()));
    }


    @Getter
    private final List<ResponsibleType> responsibles = new ArrayList<>();

    @Getter
    private Class<? extends Activity> solver;

    @Getter
    private Class<? extends Usable> toUse;

    @Getter
    private List<RoomName> roomNames;


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
