package cvut.omo.entity.activity;


import cvut.omo.entity.Responsible;
import cvut.omo.entity.person.Person;
import cvut.omo.home_structure.Home;
import cvut.omo.home_structure.Room;

public class Relocate extends Activity {


    public Relocate(Room room, ActivityType activityType) {
        super(activityType);
        this.room = room;
    }

    @Override
    public void execute(Responsible responsible) {
        if(responsible.getRoom().getRoomType() == room.getRoomType()){
            System.out.println(responsible.getClass().getSimpleName() + " with name " + ((Person)responsible).getName() + " stays in the room " + responsible.getRoom().getRoomType() + " on floor " + responsible.getRoom().getFloor().getNumberOfFloor());
        }else{
            System.out.print(responsible.getClass().getSimpleName() + " with name " + ((Person)responsible).getName() + " in " +
                    responsible.getRoom().getRoomType() + " on floor " +
                    responsible.getRoom().getFloor().getNumberOfFloor());
            System.out.print(" executing relocate activity... -> " + room.getRoomType() + " " + room.getFloor().getNumberOfFloor() + "\n");
        }
    }
}
