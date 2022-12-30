package cvut.omo.home_structure;

import cvut.omo.data_collections.visitor.SmartHomeVisitor;
import cvut.omo.home_structure.room_builder.Room;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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
