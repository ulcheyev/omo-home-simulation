package cvut.omo.entity.activity;

import cvut.omo.device.HomeDevice;
import cvut.omo.entity.Responsible;
import lombok.AllArgsConstructor;

public class UseDevice extends Activity {

    private Class<? extends HomeDevice> toUse;

    public UseDevice(Class<? extends HomeDevice> toUse, ActivityType activityType) {
        super(activityType);
        this.toUse = toUse;
    }

    @Override
    public void execute(Responsible responsible) {
        responsible.lock();
        System.out.println();
        System.out.println("Executing "+ activityType.name() +  " activity on device " + activityType.getToUse().getSimpleName() + "\n");
        responsible.unlock();
    }
}
