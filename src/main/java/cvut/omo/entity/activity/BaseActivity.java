package cvut.omo.entity.activity;


import cvut.omo.entity.Responsible;
import cvut.omo.event.Event;

/**
 *
 */
public class BaseActivity extends Activity {

    public BaseActivity(Responsible res, Event event, ActivityType activityType) throws InterruptedException {
        super(res, event, activityType);
    }

    /**
     * @param responsible
     */
    @Override
    public void doWork(Responsible responsible)  {

    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
