package cvut.omo.home_structure.room_builder;


import cvut.omo.device.HomeDevice;
import cvut.omo.item.Item;
import cvut.omo.home_structure.Floor;
import cvut.omo.home_structure.RoomType;

import java.util.List;

public interface RoomBuilder {
    RoomBuilder reset();
    RoomBuilder setWindow();
    RoomBuilder setRoomType(RoomType roomType);
    RoomBuilder setFloor(Floor floor);
    RoomBuilder setHomeDevice(HomeDevice homeDevice);
    RoomBuilder addItem(Item item);
    RoomBuilder setHomeDevices(List<HomeDevice> homeDevices);
}
