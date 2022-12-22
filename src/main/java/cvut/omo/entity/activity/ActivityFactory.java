package cvut.omo.entity.activity;

import cvut.omo.device.HomeDevice;
import cvut.omo.home_structure.Room;

public class ActivityFactory {


    public static Activity create(Room room, ActivityType activityType){
        Class<? extends Activity> solver = activityType.getSolver();
        Class<? extends HomeDevice> toUse = activityType.getToUse();
        if (Relocate.class.equals(solver)) {
            return new Relocate(room, activityType);
        }
        if (UseDevice.class.equals(solver)) {
            return new UseDevice(activityType.getToUse(), activityType);
        }
        if (UseItem.class.equals(solver)) {
            return new UseItem(activityType);
        }
        return new BaseActivity(activityType);

    }
}
