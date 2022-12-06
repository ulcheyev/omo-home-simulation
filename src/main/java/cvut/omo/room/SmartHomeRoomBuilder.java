package cvut.omo.room;

import cvut.omo.device.HomeDevice;

public class SmartHomeRoomBuilder implements RoomBuilder{

    private Room room;

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
    public RoomBuilder setSquare(double square) {
        room.setSquare(square);
        return this;
    }

    @Override
    public RoomBuilder setHomeDevice(HomeDevice homeDevice) {
        room.addHomeDevice(homeDevice);
        return this;
    }

    public Room getResult(){
        return room;
    }
}
