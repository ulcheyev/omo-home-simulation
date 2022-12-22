package cvut.omo.home_structure.room_builder;

import cvut.omo.device.factory.HomeDeviceFactory;
import cvut.omo.device.factory.SmartHomeDeviceFactory;
import cvut.omo.item.Bike;
import cvut.omo.item.Car;
import cvut.omo.item.PetToy;
import cvut.omo.item.Ski;
import cvut.omo.home_structure.Floor;
import cvut.omo.home_structure.RoomType;


public final class SmartHomeRoomBuilderDirector {

    public static final SmartHomeRoomBuilderDirector INSTANCE = new SmartHomeRoomBuilderDirector();
    private SmartHomeRoomBuilderDirector(){}
    private HomeDeviceFactory homeDeviceFactory = SmartHomeDeviceFactory.INSTANCE;


    public void createBathRoom(RoomBuilder roomBuilder, Floor floor){
        roomBuilder
                .reset()
                .setRoomType(RoomType.BATHROOM)
                .setWindow()
                .setFloor(floor)
                .setHomeDevice(homeDeviceFactory.createWashingMachine())
                .setHomeDevice(homeDeviceFactory.createWaterLeakSensor());
    }

    public void createKitchen(RoomBuilder roomBuilder, Floor floor){
        roomBuilder
                .reset()
                .setRoomType(RoomType.KITCHEN)
                .setWindow()
                .setWindow()
                .setFloor(floor)
                .setHomeDevice(homeDeviceFactory.createOven())
                .setHomeDevice(homeDeviceFactory.createFireSensor())
                .setHomeDevice(homeDeviceFactory.createWaterLeakSensor())
                .setHomeDevice(homeDeviceFactory.createFridge())
                .setHomeDevice(homeDeviceFactory.createTV());
    }

    public void createBedRoom(RoomBuilder roomBuilder, Floor floor){
        roomBuilder
                .reset()
                .setRoomType(RoomType.BEDROOM)
                .setWindow()
                .setFloor(floor)
                .setHomeDevice(homeDeviceFactory.createFireSensor())
                .setHomeDevice(homeDeviceFactory.createWaterLeakSensor())
                .setHomeDevice(homeDeviceFactory.createTV())
                .setHomeDevice(homeDeviceFactory.createComputer())
                .addItem(new PetToy());
    }

    public void createGarage(RoomBuilder roomBuilder, Floor floor){
        roomBuilder
                .reset()
                .setRoomType(RoomType.GARAGE)
                .setWindow()
                .setFloor(floor)
                .setHomeDevice(homeDeviceFactory.createFireSensor())
                .addItem(new Car())
                .addItem(new Bike())
                .addItem(new Ski());
    }

    public void createHall(RoomBuilder roomBuilder, Floor floor){
        roomBuilder
                .reset()
                .setRoomType(RoomType.HALL)
                .setWindow()
                .setFloor(floor)
                .setHomeDevice(homeDeviceFactory.createFireSensor())
                .setHomeDevice(homeDeviceFactory.createCircuitBreaker())
                .addItem(new PetToy());
    }

    public void createChildrensRoom(RoomBuilder roomBuilder, Floor floor){
        roomBuilder
                .reset()
                .setRoomType(RoomType.CHILDRENS_ROOM)
                .setWindow()
                .setFloor(floor)
                .setHomeDevice(homeDeviceFactory.createFireSensor())
                .setHomeDevice(homeDeviceFactory.createTV())
                .setHomeDevice(homeDeviceFactory.createComputer())
                .addItem(new PetToy());
    }

    public void createRoom(RoomBuilder roomBuilder, RoomType roomType, Floor floor){
        switch (roomType){
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
        }
    }


}
