package cvut.omo.entity.activity;

import cvut.omo.app_utils.Utils;
import cvut.omo.entity.Responsible;
import cvut.omo.event.Event;
import cvut.omo.home_structure.home_builder.Home;
import cvut.omo.home_structure.room_builder.Room;
import cvut.omo.entity.Usable;
import cvut.omo.entity.item.item.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Class represents actions with {@link Item}.
 */
public class ItemActivity extends Activity {

    private Class<? extends Usable> toUse;

    /**
     * @param responsible  responsible for this activity
     * @param event        the event to which this activity relates
     * @param toUse        the class that the responsible will interact with
     * @param activityType {@link ActivityType} type of activity
     */
    public ItemActivity(Responsible responsible, Event event, Class<? extends Usable> toUse, ActivityType activityType) {
        super(responsible, event, activityType);
        this.toUse = toUse;
    }

    /**
     * Found the item that the responsible will interact with (depends on {@link #toUse}).
     * After founding item, check, if item is free.
     * Case item is free: item will be used by responsible immediately.
     * Case item in use: responsible will have {@link WaitingActivity}.
     *
     * @param responsible responsible for activity
     * @return true, if activity is executed successfully
     */
    @Override
    public boolean doWork(Responsible responsible) {
        Item foundedItem = Utils.getRandomObjFromList(searchItems());
        if (foundedItem.isFree()) {
            try {
                foundedItem.use(responsible);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;

        } else {
            if (!foundedItem.alreadyListen(responsible)) {
                foundedItem.attach(responsible);
                responsible.handle(new WaitingActivity(foundedItem, this));
            }
        }
        return false;
    }

    private List<Item> searchItems() {
        List<Item> founded = new ArrayList<>();
        for (Room room : Home.INSTANCE.getAllRooms()) {
            for (Item item : room.getItems()) {
                if (item.getClass().equals(toUse)) {
                    founded.add(item);
                }
            }
        }
        return founded;
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
