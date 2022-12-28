package cvut.omo.entity.activity;

import cvut.omo.device.HomeDevice;
import cvut.omo.entity.Responsible;
import cvut.omo.event.Event;
import cvut.omo.home_structure.room_builder.Room;

public class ActivityFactory {

    public static RelocateActivity createRelocateActivity(Responsible res, Event event, Room room) throws InterruptedException {
        return new RelocateActivity(res, event, room, ActivityType.RELOCATE);
    }




    public static Activity createActivity(Responsible res,
                                          Event event,
                                          ActivityType activityType) throws InterruptedException
    {

        Class<? extends Activity> solver = activityType.getSolver();

        if (DeviceActivity.class.equals(solver)) {
            return new DeviceActivity(res, event, activityType.getToUse(), activityType);
        }

        if (ItemActivity.class.equals(solver)) {
            return new ItemActivity(res, event,activityType);
        }

        return new BaseActivity(res, event, activityType);
    }
}
