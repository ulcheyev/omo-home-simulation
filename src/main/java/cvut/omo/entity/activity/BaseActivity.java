package cvut.omo.entity.activity;


import cvut.omo.entity.Responsible;
import cvut.omo.event.Event;

/**
 * Class represent activity, that does not have its own functionality.
 */
public class BaseActivity extends Activity {

    private static final String SLEEP_FLAG = "SLEEP";

    /**
     *
     * @param res responsible for this activity
     * @param event the event to which this activity relates
     * @param activityType {@link ActivityType} type of activity
     */
    public BaseActivity(Responsible res, Event event, ActivityType activityType) {
        super(res, event, activityType);
    }

    @Override
    public boolean doWork(Responsible responsible)  {
        if(activityType.name().contains(SLEEP_FLAG)){
            responsible.goToSleep();
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
