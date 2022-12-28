package cvut.omo.entity.activity;

import cvut.omo.entity.Responsible;
import cvut.omo.event.Event;

public class ItemActivity extends Activity{

    public ItemActivity(Responsible responsible, Event event, ActivityType activityType) throws InterruptedException {
        super(responsible, event, activityType);
    }

    @Override
    public void doWork(Responsible responsible){
        System.out.println("Executing user item activity...");
    }


    @Override
    public String toString() {
        return  super.toString();
    }
}
