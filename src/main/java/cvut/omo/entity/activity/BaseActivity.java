package cvut.omo.entity.activity;


import cvut.omo.entity.Responsible;

public class BaseActivity extends Activity {

    public BaseActivity(ActivityType activityType) {
        super(activityType);
    }

    @Override
    public void execute(Responsible responsible) {
        responsible.lock();
        System.out.println("Executing base activity " + activityType.name());
        responsible.unlock();
    }
}
