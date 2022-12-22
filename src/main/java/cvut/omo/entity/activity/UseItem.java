package cvut.omo.entity.activity;

import cvut.omo.entity.Responsible;

public class UseItem extends Activity{

    public UseItem(ActivityType activityType) {
        super(activityType);
    }

    @Override
    public void execute(Responsible responsible) {
        System.out.println("Executing user item activity...");
    }
}
