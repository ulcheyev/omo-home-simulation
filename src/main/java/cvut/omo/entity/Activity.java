package cvut.omo.entity;

import cvut.omo.entity.person.Person;
import cvut.omo.entity.pet.Pet;
import cvut.omo.home_structure.Room;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Activity {

    private ActivityType activityType;
    private Person person;
    private Pet pet;
    private Room room;
    private boolean isExucuted;


    public void setActivityType(ActivityType activityType){
        this.activityType = activityType;
    }

    public ActivityType getActivityType(){
        return activityType;
    }

//    public void setRoom(Room room){
//        this.room = room;
//    }
//
//    public Room getRoom(){
//        return room;
//    }

//    public Room findRoom(){
//
//    }

}
