package cvut.omo.home_structure;

import cvut.omo.data_collections.visitor.SmartHomeReportVisitor;
import cvut.omo.home_structure.room_builder.Room;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Class represents floor in home.
 */
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

    /**
     * Adds room to floor.
     * @param room
     */
    public void addRoom(Room room){
        rooms.add(room);
    }

    @Override
    public Object accept(SmartHomeReportVisitor visitor) {
        return visitor.visitFloor(this);
    }

    @Override
    public boolean isNull() {
        return false;
    }

    /**
     * Updates floor.
     * Updates every room in {@link #rooms}
     * @throws InterruptedException {@link Thread}
     */
    public void update() throws InterruptedException {
        for (Room room : rooms) {
            room.update();
        }
    }
}
