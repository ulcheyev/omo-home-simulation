package cvut.omo.entity.activity;

import cvut.omo.data_collections.activity_events.SmartHomeEventCollection;
import cvut.omo.entity.Responsible;
import cvut.omo.usable.item.Item;

public class WaitingActivity extends Activity{

    private final Activity activity;
    private final Item waitTo;

    public WaitingActivity(Item waitTo, Activity activity) {
        this.waitTo = waitTo;
        this.activity = activity;
        this.responsible = activity.responsible;
        this.event = activity.event;
        this.activityType = activity.activityType;
        event.addActivity(this);
        SmartHomeEventCollection.swapTwoEndActivities(event);
    }

    @Override
    protected boolean doWork(Responsible responsible) {
       if(activity.doWork(responsible)){
           return true;
       }
       return false;
    }

    @Override
    public String toString() {
        return
                responsible.getResponsibleType() +
                " wait to " + waitTo.getClass().getSimpleName() +
                " because " + waitTo.getCurrentUser().getResponsibleType() +
                " is using this item";
    }
}
