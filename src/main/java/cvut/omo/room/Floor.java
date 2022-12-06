package cvut.omo.room;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Floor {

    private int numberOfFloor;
    private List<Room> rooms;

    public void addRoom(Room room){
        rooms.add(room);
    }
}
