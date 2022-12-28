package cvut.omo.home_structure;

import cvut.omo.data_collections.visitor.SmartHomeVisitor;
import cvut.omo.event.Event;
import cvut.omo.home_structure.room_builder.Room;
import lombok.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Getter
@Setter
@NoArgsConstructor
public class Floor implements HomeComponent {

    private Integer numberOfFloor;
    private List<Room> rooms = new ArrayList<>();
    private static int currRoomIdx = 0;

    public Floor(int numberOfFloor) {
        this.numberOfFloor = numberOfFloor;
    }

    public void addRoom(Room room){
        rooms.add(room);
    }

    @Override
    public String accept(SmartHomeVisitor visitor) {
        return visitor.visitFloor(this);
    }

    @Override
    public boolean isNull() {
        return false;
    }

    public void update() throws InterruptedException {
        for (Room room : rooms) {
            room.update();
        }
    }
}
