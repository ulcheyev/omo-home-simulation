package cvut.omo.entity.activity;

import cvut.omo.device.HomeDevice;
import cvut.omo.entity.Responsible;
import cvut.omo.event.Event;
import cvut.omo.item.Item;
import cvut.omo.item.Usable;

/**
 *
 */
public class ItemActivity extends Activity{

    //FLAGS
    private Class<? extends Usable> toUse;

    /**
     * @param responsible
     * @param event
     * @param toUse
     * @param activityType
     * @throws InterruptedException
     */
    public ItemActivity(Responsible responsible, Event event, Class<? extends Usable> toUse, ActivityType activityType) throws InterruptedException {
        super(responsible, event, activityType);
        this.toUse = toUse;
    }

    /**
     * @param responsible
     */
    @Override
    public void doWork(Responsible responsible) {

    }


    /**
     * @return
     */
    @Override
    public String toString() {
        return  super.toString();
    }
}
