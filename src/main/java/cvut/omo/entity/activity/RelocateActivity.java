package cvut.omo.entity.activity;


import cvut.omo.entity.Responsible;
import cvut.omo.entity.person.Person;
import cvut.omo.event.Event;
import cvut.omo.home_structure.room_builder.Room;
import lombok.Getter;

public class RelocateActivity extends Activity {

    @Getter
    private Room from;

    public RelocateActivity(Responsible responsible, Event event, Room toRoom, ActivityType activityType)  {
        super(responsible, event, activityType);
        from = responsible.getRoom();
        this.room = toRoom;
    }

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
