package cvut.omo.home_structure.room_builder;

import cvut.omo.device.HomeDevice;
import cvut.omo.item.Item;
import cvut.omo.home_structure.Floor;

import java.util.List;

public final class SmartHomeRoomBuilder implements RoomBuilder {

    public static final SmartHomeRoomBuilder INSTANCE = new SmartHomeRoomBuilder();
    private SmartHomeRoomBuilder(){}

    private Room room = new Room();

    @Override
    public RoomBuilder reset() {
        room = new Room();
        return this;
    }

    @Override
    public RoomBuilder setWindow() {
        room.addWindow();
        return this;
    }

    @Override
    public RoomBuilder setFloor(Floor floor) {
        room.setFloor(floor);
        floor.addRoom(room);
        return this;
    }


    @Override
    public RoomBuilder addItem(Item item) {
        room.addItem(item);
        return this;
    }

    @Override
    public RoomBuilder setHomeDevice(HomeDevice homeDevice) {
        room.addHomeDevice(homeDevice);
        return this;
    }

    @Override
    public RoomBuilder setHomeDevices(List<HomeDevice> homeDevices) {
        for(HomeDevice homeDevice: homeDevices){
            room.addHomeDevice(homeDevice);
        }
        return this;
    }

    @Override
    public RoomBuilder setRoomType(RoomName roomName) {
        room.setRoomName(roomName);
        return this;
    }

    public Room getResult(){
        Room res = room;
        reset();
        return res;
    }
}
