package cvut.omo.home_structure.nulls;

import cvut.omo.home_structure.Floor;
import cvut.omo.home_structure.room_builder.Room;
import cvut.omo.home_structure.room_builder.RoomName;

public class NullRoom extends Room {

    @Override
    public boolean isNull() {
        return true;
    }

    @Override
    public Floor getFloor() {
        return new NullFloor();
    }

    @Override
    public RoomName getRoomName(){
        return RoomName.STUB;
    }
}
