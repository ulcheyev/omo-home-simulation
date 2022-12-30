package cvut.omo.entity.activity;

import cvut.omo.app_utils.Utils;
import cvut.omo.entity.Responsible;
import cvut.omo.event.Event;
import cvut.omo.home_structure.home_builder.Home;
import cvut.omo.home_structure.room_builder.Room;
import cvut.omo.usable.Usable;
import cvut.omo.usable.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemActivity extends Activity{

    //FLAGS
    private Class<? extends Usable> toUse;

    public ItemActivity(Responsible responsible, Event event, Class<? extends Usable> toUse, ActivityType activityType) {
        super(responsible, event, activityType);
        this.toUse = toUse;
    }

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

        }
        else{
            if(!foundedItem.alreadyListen(responsible)){
                foundedItem.attach(responsible);
                responsible.handle(new WaitingActivity(foundedItem, this));
            }
        }
        return false;
    }


    private List<Item> searchItems(){
        List<Item> founded = new ArrayList<>();
        for(Room room: Home.INSTANCE.getAllRooms()){
           for(Item item: room.getItems()){
               if(item.getClass().equals(toUse)){
                   founded.add(item);
               }
           }
        }
        return founded;
    }


    @Override
    public String toString() {
        return  super.toString();
    }
}
