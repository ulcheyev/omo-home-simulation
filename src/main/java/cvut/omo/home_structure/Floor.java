package cvut.omo.home_structure;

import cvut.omo.data_collections.visitor.SmartHomeVisitor;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Floor implements HomeComponent {

    private Integer numberOfFloor;
    private List<Room> rooms = new ArrayList<>();

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
}
