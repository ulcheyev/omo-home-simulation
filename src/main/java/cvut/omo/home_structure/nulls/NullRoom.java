package cvut.omo.home_structure.nulls;

import cvut.omo.home_structure.Floor;
import cvut.omo.home_structure.room_builder.Room;
import cvut.omo.home_structure.room_builder.RoomName;

/**
 *
 */
public class NullRoom extends Room {

    /**
     * @return
     */
    @Override
    public boolean isNull() {
        return true;
    }

    /**
     * @return
     */
    @Override
    public Floor getFloor() {
        return new NullFloor();
    }

    /**
     * @return
     */
    @Override
    public RoomName getRoomName(){
        return RoomName.STUB;
    }
}
