package cvut.omo.entity.activity;

import cvut.omo.device.HomeDevice;
import cvut.omo.entity.Responsible;
import cvut.omo.event.Event;

public class DeviceActivity extends Activity {

    private Class<? extends HomeDevice> toUse;

    public DeviceActivity(Responsible responsible, Event event, Class<? extends HomeDevice> toUse, ActivityType activityType) throws InterruptedException {
        super(responsible, event, activityType);
        this.toUse = toUse;
    }

    @Override
    public void doWork(Responsible responsible) {

        if(activityType.getToUse() != null) {
            System.out.println("Executing " + activityType.name() + " activity on device " + activityType.getToUse().getSimpleName() + "\n");
        }
        else{
            System.out.println("Executing " + activityType.name() + " activity"  + "\n");
        }


    }
}
