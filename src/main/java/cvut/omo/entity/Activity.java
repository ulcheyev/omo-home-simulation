package cvut.omo.entity;

import cvut.omo.entity.person.Person;
import cvut.omo.entity.pet.Pet;
import cvut.omo.home_structure.Room;


public class Activity {

    private ActivityType activityType;

    protected Person person;

    protected Pet pet;

    protected Room room;

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
