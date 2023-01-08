package cvut.omo.entity.activity;

import cvut.omo.data_collections.activity_events.SmartHomeEventCollection;
import cvut.omo.entity.Responsible;
import cvut.omo.entity.item.item.Item;

/**
 * Class represents waiting activity.
 */
public class WaitingActivity extends Activity {

    private final Activity activity;
    private final Item waitTo;

    /**
     * Constructor equates all values from the activity in the parameter.
     *
     * @param waitTo   item to wait
     * @param activity the activity that will execute after waiting
     */
    public WaitingActivity(Item waitTo, Activity activity) {
        this.waitTo = waitTo;
        this.activity = activity;
        this.responsible = activity.responsible;
        this.event = activity.event;
        this.activityType = activity.activityType;
        event.addActivity(this);
        SmartHomeEventCollection.swapTwoEndActivities(event);
    }

    /**
     * @param responsible responsible for activity
     * @return {If {@link #activity } is executed successfully, return true}
     */
    @Override
    protected boolean doWork(Responsible responsible) {
        if (activity.doWork(responsible)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return responsible.getResponsibleType() + " wait to " + waitTo.getClass().getSimpleName() + " because " + waitTo.getCurrentUser().getResponsibleType() + " is using this item";
    }
}
