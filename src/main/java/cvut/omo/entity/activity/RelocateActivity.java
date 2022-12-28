package cvut.omo.entity.activity;


import cvut.omo.entity.Responsible;
import cvut.omo.entity.person.Person;
import cvut.omo.event.Event;
import cvut.omo.home_structure.room_builder.Room;

public class RelocateActivity extends Activity {


    public RelocateActivity(Responsible responsible, Event event, Room toRoom, ActivityType activityType) throws InterruptedException {
        super(responsible, event, activityType);
        this.room = toRoom;
    }

    @Override
    public void doWork(Responsible responsible) {

//        if (responsible.getClass().equals(Person.class)) {
//            System.out.print(responsible.getClass().getSimpleName() +
//                    " with name " + ((Person)responsible).getName() + " in " +
//                    responsible.getRoom().getRoomName() + " on floor " +
//                    responsible.getRoom().getFloor().getNumberOfFloor()
//                     + " executing relocate activity -> "
//                    + roomTo.getRoomName().name() + " on " +
//                    roomTo.getFloor().getNumberOfFloor() + "\n");
//        }
//        else{
//            System.out.print(responsible.getClass().getSimpleName() +
//                    " with type " + responsible.getResponsibleType() + " in " +
//                    responsible.getRoom().getRoomName() + " on floor " +
//                    responsible.getRoom().getFloor().getNumberOfFloor()
//                    + " executing relocate activity -> "
//                    + roomTo.getRoomName().name() + " on " +
//                    roomTo.getFloor().getNumberOfFloor() + "\n");
//        }
        responsible.relocate(event.getRoom());
    }
}
