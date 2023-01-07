package cvut.omo.home_structure.room_builder;

import cvut.omo.entity.device.*;
import cvut.omo.entity.device.factory.HomeDeviceFactory;
import cvut.omo.entity.device.factory.SmartHomeDeviceFactory;
import cvut.omo.home_structure.Floor;
import cvut.omo.entity.item.item.Bike;
import cvut.omo.entity.item.item.Car;
import cvut.omo.entity.item.item.PetToy;
import cvut.omo.entity.item.item.Ski;
import cvut.omo.home_structure.home_builder.SmartHomeBuilder;


/**
 * Director for {@link SmartHomeRoomBuilderDirector}.
 */
public final class SmartHomeRoomBuilderDirector {

    /**
     * Instance of {@link SmartHomeRoomBuilderDirector}.
     */
    public static final SmartHomeRoomBuilderDirector INSTANCE = new SmartHomeRoomBuilderDirector();
    private SmartHomeRoomBuilderDirector(){}
    private HomeDeviceFactory homeDeviceFactory = SmartHomeDeviceFactory.INSTANCE;

    /**
     * Creates bathroom with:
     * 1 {@link WashingMachine}
     * 1 {@link WaterLeakSensor}
     * 1 {@link FireSensor}
     * @param roomBuilder builder, which will build this room
     * @param floor room floor
     */
    public void createBathRoom(RoomBuilder roomBuilder, Floor floor){
        roomBuilder
                .reset()
                .setRoomType(RoomName.BATHROOM)
                .setWindow()
                .setFloor(floor)
                .setHomeDevice(homeDeviceFactory.createWashingMachine())
                .setHomeDevice(homeDeviceFactory.createWaterLeakSensor())
                .setHomeDevice(homeDeviceFactory.createFireSensor());

    }


    /**
     * Creates vestibule with:
     * 1 {@link CircuitBreaker}
     * 1 {@link FireSensor}
     * @param roomBuilder builder, which will build this room
     * @param floor room floor
     */
    public void createVestibule(RoomBuilder roomBuilder, Floor floor){
        roomBuilder
                .reset()
                .setRoomType(RoomName.VESTIBULE)
                .setWindow()
                .setFloor(floor)
                .setHomeDevice(homeDeviceFactory.createFireSensor())
                .setHomeDevice(homeDeviceFactory.createCircuitBreaker());
    }


    /**
     * Creates kitchen with:
     * 1 {@link Oven}
     * 1 {@link FireSensor}
     * 1 {@link WaterLeakSensor}
     * 1 {@link Fridge}
     * 1 {@link TV}
     * @param roomBuilder builder, which will build this room
     * @param floor room floor
     */
    public void createKitchen(RoomBuilder roomBuilder, Floor floor){
        roomBuilder
                .reset()
                .setRoomType(RoomName.KITCHEN)
                .setWindow()
                .setWindow()
                .setFloor(floor)
                .setHomeDevice(homeDeviceFactory.createOven())
                .setHomeDevice(homeDeviceFactory.createFireSensor())
                .setHomeDevice(homeDeviceFactory.createWaterLeakSensor())
                .setHomeDevice(homeDeviceFactory.createFridge())
                .setHomeDevice(homeDeviceFactory.createTV());
    }

    /**
     * Creates bedroom with:
     * 1 {@link FireSensor}
     * 1 {@link Computer}
     * 1 {@link TV}
     * 1 {@link PetToy}
     * @param roomBuilder builder, which will build this room
     * @param floor room floor
     */
    public void createBedRoom(RoomBuilder roomBuilder, Floor floor){
        roomBuilder
                .reset()
                .setRoomType(RoomName.BEDROOM)
                .setWindow()
                .setFloor(floor)
                .setHomeDevice(homeDeviceFactory.createFireSensor())
                .setHomeDevice(homeDeviceFactory.createTV())
                .setHomeDevice(homeDeviceFactory.createComputer())
                .addItem(new PetToy());
    }

    /**
     * Creates garage with:
     * 1 {@link FireSensor}
     * 1 {@link Ski}
     * 1 {@link Bike}
     * 1 {@link PetToy}
     * 1 {@link Car}
     * @param roomBuilder builder, which will build this room
     * @param floor room floor
     */
    public void createGarage(RoomBuilder roomBuilder, Floor floor){
        roomBuilder
                .reset()
                .setRoomType(RoomName.GARAGE)
                .setWindow()
                .setFloor(floor)
                .setHomeDevice(homeDeviceFactory.createFireSensor())
                .addItem(new Car())
                .addItem(new Bike())
                .addItem(new Ski());
    }


    /**
     * Creates hall with:
     * 1 {@link FireSensor}
     * 1 {@link PetToy}
     * @param roomBuilder builder, which will build this room
     * @param floor room floor
     */
    public void createHall(RoomBuilder roomBuilder, Floor floor){
        roomBuilder
                .reset()
                .setRoomType(RoomName.HALL)
                .setWindow()
                .setFloor(floor)
                .setHomeDevice(homeDeviceFactory.createFireSensor())
                .addItem(new PetToy());
    }


    /**
     * Creates hall with:
     * 1 {@link FireSensor}
     * 1 {@link PetToy}
     * 1 {@link TV}
     * 1 {@link Computer}
     * @param roomBuilder builder, which will build this room
     * @param floor room floor
     */
    public void createChildrensRoom(RoomBuilder roomBuilder, Floor floor){
        roomBuilder
                .reset()
                .setRoomType(RoomName.CHILDRENS_ROOM)
                .setWindow()
                .setFloor(floor)
                .setHomeDevice(homeDeviceFactory.createFireSensor())
                .setHomeDevice(homeDeviceFactory.createTV())
                .setHomeDevice(homeDeviceFactory.createComputer())
                .addItem(new PetToy());
    }

    /**
     * Created room from specified {@link RoomName}.
     * @param roomBuilder builder, which will build this room
     * @param roomName specified {@link RoomName}
     * @param floor room {@link Floor}
     */
    public void createRoom(RoomBuilder roomBuilder, RoomName roomName, Floor floor){
        switch (roomName){
            case BATHROOM -> {
                createBathRoom(roomBuilder, floor);
                break;
            }
            case BEDROOM -> {
                createBedRoom(roomBuilder, floor);
                break;
            }
            case CHILDRENS_ROOM -> {
                createChildrensRoom(roomBuilder, floor);
                break;
            }
            case KITCHEN -> {
                createKitchen(roomBuilder, floor);
                break;
            }
            case HALL -> {
                createHall(roomBuilder, floor);
                break;
            }
            case GARAGE -> {
                createGarage(roomBuilder, floor);
                break;
            }
            case VESTIBULE -> {
                createVestibule(roomBuilder, floor);
                break;
            }
        }
    }


}
