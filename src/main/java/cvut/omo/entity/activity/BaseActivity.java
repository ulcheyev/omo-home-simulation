package cvut.omo.entity.activity;


import cvut.omo.entity.Responsible;
import cvut.omo.event.Event;

public class BaseActivity extends Activity {

    public BaseActivity(Responsible res, Event event, ActivityType activityType) throws InterruptedException {
        super(res, event, activityType);
    }

    @Override
    public void doWork(Responsible responsible){
        System.out.println("Executing base activity " + activityType.name() + " by " + responsible.getResponsibleType() +
                " on the floor "  + " in room ");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
