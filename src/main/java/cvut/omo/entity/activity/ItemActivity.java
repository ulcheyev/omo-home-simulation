package cvut.omo.entity.activity;

import cvut.omo.device.HomeDevice;
import cvut.omo.entity.Responsible;
import cvut.omo.event.Event;
import cvut.omo.item.Item;
import cvut.omo.item.Usable;

public class ItemActivity extends Activity{

    //FLAGS
    private Class<? extends Usable> toUse;

    public ItemActivity(Responsible responsible, Event event, Class<? extends Usable> toUse, ActivityType activityType) throws InterruptedException {
        super(responsible, event, activityType);
        this.toUse = toUse;
    }

    @Override
    public void doWork(Responsible responsible) {

    }


    @Override
    public String toString() {
        return  super.toString();
    }
}
