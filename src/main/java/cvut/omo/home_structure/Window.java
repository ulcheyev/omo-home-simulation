package cvut.omo.home_structure;

import cvut.omo.entity.item.item.Blinds;
import cvut.omo.entity.item.item.Item;
import cvut.omo.home_structure.room_builder.Room;

/**
 * Class represents window in home.
 */
public class Window extends Item {

    private Blinds blinds = new Blinds();
    private Room room;

    public Window(Room room) {
        this.room = room;
        room.addItem(blinds);
    }


}
