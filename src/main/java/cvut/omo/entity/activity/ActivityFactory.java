package cvut.omo.entity.activity;

import cvut.omo.entity.Responsible;
import cvut.omo.event.Event;
import cvut.omo.home_structure.room_builder.Room;

/**
 * Factory for {@link Activity}.
 */
public class ActivityFactory {

    /**
     * Creates relocate activity.
     * @param res responsible for activity
     * @param event the event to which this activity relates
     * @param room the room to relocate
     * @return new {@link RelocateActivity}
     */
    public static RelocateActivity createRelocateActivity(Responsible res, Event event, Room room){
        return new RelocateActivity(res, event, room, ActivityType.RELOCATE);
    }




    /**
     * Creates new activity.
     * @param res responsible for activity
     * @param event the event to which this activity relates
     * @param activityType activity type from {@link ActivityType}
     * @return new {@link Activity}
     */
    public static Activity createActivity(Responsible res,
                                          Event event,
                                          ActivityType activityType)
    {

        Class<? extends Activity> solver = activityType.getSolver();

        if (DeviceActivity.class.equals(solver)) {
            return new DeviceActivity(res, event, activityType.getToUse(), activityType);
        }

        if (ItemActivity.class.equals(solver)) {
            return new ItemActivity(res, event, activityType.getToUse(), activityType);
        }

        return new BaseActivity(res, event, activityType);
    }
}
