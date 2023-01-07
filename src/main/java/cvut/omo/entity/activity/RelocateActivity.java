package cvut.omo.entity.activity;


import cvut.omo.entity.Responsible;
import cvut.omo.event.Event;
import cvut.omo.home_structure.room_builder.Room;
import lombok.Getter;

/**
 * Class represents move actions in house.
 */
public class RelocateActivity extends Activity {

    @Getter
    private Room from;

    /**
     *
     * @param responsible responsible for this activity
     * @param event the event to which this activity relates
     * @param toRoom the room that the responsible will moved in
     * @param activityType implicitly RELOCATE
     */
    public RelocateActivity(Responsible responsible, Event event, Room toRoom, ActivityType activityType)  {
        super(responsible, event, activityType);
        from = responsible.getRoom();
        this.room = toRoom;
    }

    /**
     * Relocates responsible.
     * @param responsible responsible for activity
     * @return true, if activity is executed successfully
     */
    @Override
    public boolean doWork(Responsible responsible) {
        responsible.relocate(event, room);
        return true;
    }


    @Override
    public String toString() {
        return  super.toString() +
                " from room " + from.getRoomName() +
                " on the " + from.getFloor().getNumberOfFloor()
                + " floor";

    }
}
