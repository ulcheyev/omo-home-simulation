package cvut.omo.entity;

import cvut.omo.entity.activity.Activity;
import cvut.omo.home_structure.Room;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.Queue;

@Getter
@Setter
public abstract class Responsible {

    private Queue<Activity> activities;
    private EntityStatus entityStatus = EntityStatus.FREE;
    private Room room;

    protected Responsible(){
        activities = new LinkedList<>();
    }


    public void handle(Activity activity){
        if(isFree()){
            activity.execute(this);
        }
        else{
            activities.add(activity);
        }
    }

    public boolean isFree(){
        return entityStatus == EntityStatus.FREE;
    }

    public void setStatus(EntityStatus entityStatus) {
        this.entityStatus = entityStatus;
    }

    public void lock(){
        this.entityStatus = EntityStatus.BUSY;
    }

    public void unlock(){
        this.entityStatus = EntityStatus.FREE;
        Activity peek = activities.peek();
        if(peek != null) peek.execute(this);
    }


    public void relocate(Room room) {

    }

    public void update(){};

}
