package cvut.omo.room;


import cvut.omo.device.HomeDevice;

public interface RoomBuilder {
    RoomBuilder reset();
    RoomBuilder setWindow();
    RoomBuilder setFloor(Floor floor);
    RoomBuilder setSquare(double square);
    RoomBuilder setHomeDevice(HomeDevice homeDevice);
}
