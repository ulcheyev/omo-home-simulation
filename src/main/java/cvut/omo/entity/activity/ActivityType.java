package cvut.omo.entity.activity;

import cvut.omo.entity.device.*;
import cvut.omo.entity.ResponsibleType;
import cvut.omo.entity.living.person.FamilyRoleType;
import cvut.omo.entity.living.pet.PetType;
import cvut.omo.home_structure.room_builder.RoomName;
import cvut.omo.entity.Usable;
import cvut.omo.entity.item.item.Bike;
import cvut.omo.entity.item.item.Car;
import cvut.omo.entity.item.item.PetToy;
import cvut.omo.entity.item.item.Ski;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static cvut.omo.entity.living.person.FamilyRoleType.*;
import static java.util.List.of;

public enum ActivityType {



    //DEVICE

    //PERSON
    PET_A_PET(of(RoomName.COMMON),null),
    TAKE_A_WALK_WITH_PET(of(RoomName.STUB), null,  DAUGHTER, SON),
    FEED_PET(of(RoomName.COMMON), null),
    PERSON_SLEEP(of(RoomName.BEDROOM), null),

    USE_BIKE(of(RoomName.GARAGE), Bike.class, ItemActivity.class),
    USE_SKIS(of(RoomName.GARAGE), Ski.class, ItemActivity.class),
    USE_CAR(of(RoomName.GARAGE), Car.class, ItemActivity.class),

    //ON DEVICE
    DEVICE_FRIDGE_ON(of(RoomName.KITCHEN),Fridge.class, DeviceActivity.class),
    DEVICE_COMPUTER_ON(RoomType.CHILL.getRooms(), Computer.class, DeviceActivity.class),
    DEVICE_OVEN_ON(of(RoomName.KITCHEN), Oven.class, DeviceActivity.class),
    DEVICE_WASHING_MACHINE_ON(of(RoomName.BATHROOM), WashingMachine.class, DeviceActivity.class),
    DEVICE_WATER_LEAK_SENSOR_ON(of(RoomName.KITCHEN, RoomName.BATHROOM), WaterLeakSensor.class, DeviceActivity.class, FATHER, GRANDFATHER),
    DEVICE_FIRE_SENSOR_ON(of(RoomName.STUB), FireSensor.class, DeviceActivity.class, FATHER, GRANDFATHER),
    DEVICE_TV_ON(RoomType.CHILL.getRooms(), TV.class, DeviceActivity.class),

    DEVICE_CURCUIT_BREAKER_ON(of(RoomName.STUB), CircuitBreaker.class, DeviceActivity.class, Device.CIRCUIT_BREAKER),


    //OFF Device -> CONF THE SAME AS ON
    DEVICE_FRIDGE_OFF(DEVICE_FRIDGE_ON),
    DEVICE_COMPUTER_OFF(DEVICE_COMPUTER_ON),
    DEVICE_OVEN_OFF(DEVICE_OVEN_ON),
    DEVICE_WASHING_MACHINE_OFF(DEVICE_WASHING_MACHINE_ON),
    DEVICE_WATER_LEAK_SENSOR_OFF(DEVICE_WATER_LEAK_SENSOR_ON),
    DEVICE_FIRE_SENSOR_OFF(DEVICE_FIRE_SENSOR_ON),
    DEVICE_TV_OFF(DEVICE_TV_ON),
    DEVICE_CURCUIT_BREAKER_OFF(of(RoomName.VESTIBULE), CircuitBreaker.class, DeviceActivity.class, FATHER, GRANDFATHER),


    //RUN DEVICES -> CONF THE SAME AS ON
    DEVICE_FRIDGE_RUN(DEVICE_FRIDGE_ON),
    DEVICE_FIRE_SENSOR_RUN(of(RoomName.STUB), FireSensor.class, DeviceActivity.class),
    DEVICE_WATER_LEAK_SENSOR_RUN(of(RoomName.STUB), WaterLeakSensor.class, DeviceActivity.class),
    DEVICE_COMPUTER_RUN(DEVICE_COMPUTER_ON),
    DEVICE_OVEN_RUN(DEVICE_OVEN_ON),
    DEVICE_WASHING_MACHINE_RUN(DEVICE_WASHING_MACHINE_ON),
    DEVICE_TV_RUN(DEVICE_TV_ON),

    //PAUSE DEVICES -> CONF THE SAME AS ON
    DEVICE_FRIDGE_PAUSE(DEVICE_FRIDGE_ON),
    DEVICE_COMPUTER_PAUSE(DEVICE_COMPUTER_ON),
    DEVICE_OVEN_PAUSE(DEVICE_OVEN_ON),
    DEVICE_WASHING_MACHINE_PAUSE(DEVICE_WASHING_MACHINE_ON),
    DEVICE_TV_PAUSE(DEVICE_TV_ON),

    //REPAIR
    DEVICE_REPAIR(of(RoomName.COMMON), null, DeviceActivity.class,  FATHER),
    DEVICE_BREAK(of(RoomName.COMMON), null, DeviceActivity.class),
    CHANGE_BULB(of(RoomName.COMMON), null, BaseActivity.class, FATHER, GRANDFATHER),
    READ_A_DOCUMENTATION(of(RoomName.COMMON), null, BaseActivity.class, FATHER),
    TRY_TO_FIX_IT_YOURSELF(of(RoomName.COMMON),null,BaseActivity.class, FATHER),
    CALL_GRANDFATHER_TO_HELP_REPAIR(of(RoomName.COMMON),null,BaseActivity.class, FATHER),
    GRANDFATHER_REPAIRING_THE_DEVICE(of(RoomName.COMMON),null,BaseActivity.class, GRANDFATHER),
    THROW_THE_DEVICE_IN_THE_TRASH(of(RoomName.COMMON),null,BaseActivity.class, FATHER),



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

    PLAY_WITH_TOY(of(RoomName.STUB), PetToy.class, ItemActivity.class, PetType.class.getEnumConstants()),

    //PERSON AND PETS
    RELOCATE(of(RoomName.STUB), RelocateActivity.class, null, FamilyRoleType.class.getEnumConstants(), PetType.class.getEnumConstants());


    /**
     *
     * @param roomNames {@link RoomName} where activity can be executed
     * @param use necessary device/item class for executing
     * @param solver class that extends {@link Activity}, which will be solving this activity
     * @param roles responsible roles, which can this execute this activity
     *              (similarity of the use case diagram)
     */
    ActivityType(List<RoomName > roomNames, Class<? extends Usable> use, Class<? extends Activity> solver, ResponsibleType...roles) {
        assignVariables(roomNames, solver, use, roles);
    }

    /**
     *
     * @param roomNames {@link RoomName} where activity can be executed
     * @param solver necessary device/item class for executing
     * @param use class that extends {@link Activity}, which will be solving this activity
     * @param roles responsible roles, which can this execute this activity
     *                  (similarity of the use case diagram)
     */
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

    /**
     * Solver will be implicitly BaseActivity.
     * @param roomNames {@link RoomName} where activity can be executed
     * @param use class that extends {@link Activity}, which will be solving this activity
     * @param roles responsible roles, which can this execute this activity
     *               (similarity of the use case diagram)
     */
    ActivityType(List<RoomName > roomNames, Class<? extends HomeDevice> use, ResponsibleType...roles) {
        assignVariables(roomNames, null, use, roles);
    }



    /**
     * @param activityType assign all values from activityType parameter
     */
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


    /**
     * Class represent device, which can be responsible for some activities.
     * */
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
