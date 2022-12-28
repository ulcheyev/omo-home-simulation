package cvut.omo.home_structure.room_builder;

import cvut.omo.device.factory.HomeDeviceFactory;
import cvut.omo.device.factory.SmartHomeDeviceFactory;
import cvut.omo.item.Bike;
import cvut.omo.item.Car;
import cvut.omo.item.PetToy;
import cvut.omo.item.Ski;
import cvut.omo.home_structure.Floor;


public final class SmartHomeRoomBuilderDirector {

    public static final SmartHomeRoomBuilderDirector INSTANCE = new SmartHomeRoomBuilderDirector();
    private SmartHomeRoomBuilderDirector(){}
    private HomeDeviceFactory homeDeviceFactory = SmartHomeDeviceFactory.INSTANCE;


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

    public void createVestibule(RoomBuilder roomBuilder, Floor floor){
        roomBuilder
                .reset()
                .setRoomType(RoomName.VESTIBULE)
                .setWindow()
                .setFloor(floor)
                .setHomeDevice(homeDeviceFactory.createFireSensor())
                .setHomeDevice(homeDeviceFactory.createCircuitBreaker());
    }

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

    public void createBedRoom(RoomBuilder roomBuilder, Floor floor){
        roomBuilder
                .reset()
                .setRoomType(RoomName.BEDROOM)
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
                .setRoomType(RoomName.GARAGE)
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
                .setRoomType(RoomName.HALL)
                .setWindow()
                .setFloor(floor)
                .setHomeDevice(homeDeviceFactory.createFireSensor())
                .addItem(new PetToy());
    }

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
