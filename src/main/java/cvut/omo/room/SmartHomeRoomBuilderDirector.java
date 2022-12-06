package cvut.omo.room;

import cvut.omo.device.HomeDevice;

public class SmartHomeRoomBuilderDirector {

    //TODO esli budet factory home device
    public Room createBathRoom(Floor floor, RoomBuilder roomBuilder){
        roomBuilder.setWindow().setFloor(floor)
    }
}
