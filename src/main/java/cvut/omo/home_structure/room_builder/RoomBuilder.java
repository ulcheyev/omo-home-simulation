package cvut.omo.home_structure.room_builder;


import cvut.omo.entity.device.HomeDevice;
import cvut.omo.home_structure.Floor;
import cvut.omo.entity.item.item.Item;

import java.util.List;


public interface RoomBuilder {

    /**
     * Resets current room, which is building.
     *
     * @return current RoomBuilder
     */
    RoomBuilder reset();

    /**
     * Add window to room.
     *
     * @return current RoomBuilder
     */
    RoomBuilder setWindow();

    /**
     * Set room name {@link RoomName}.
     *
     * @return current RoomBuilder
     */
    RoomBuilder setRoomType(RoomName roomName);

    /**
     * Set room floor.
     *
     * @param floor floor to set
     * @return current RoomBuilder
     */
    RoomBuilder setFloor(Floor floor);

    /**
     * Add home device to room.
     *
     * @return current RoomBuilder
     */
    RoomBuilder setHomeDevice(HomeDevice homeDevice);

    /**
     * Add item to room.
     *
     * @param item item to add
     * @return current RoomBuilder
     */
    RoomBuilder addItem(Item item);

}
