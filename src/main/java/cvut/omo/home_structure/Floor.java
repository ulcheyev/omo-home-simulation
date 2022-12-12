package cvut.omo.home_structure;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class Floor {

    private Integer numberOfFloor;
    private List<Room> rooms = new ArrayList<>();

    public Floor(int numberOfFloor) {
        this.numberOfFloor = numberOfFloor;
    }

    public void addRoom(Room room){
        rooms.add(room);
    }
}
