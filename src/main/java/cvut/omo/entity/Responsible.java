package cvut.omo.entity;
import cvut.omo.data_collections.visitor.SmartHomeVisitor;
import cvut.omo.entity.activity.Activity;
import cvut.omo.entity.nulls.NullResponsible;
import cvut.omo.entity.nulls.NullResponsibleType;
import cvut.omo.event.Event;
import cvut.omo.home_structure.HomeComponent;
import cvut.omo.home_structure.nulls.NullRoom;
import cvut.omo.home_structure.room_builder.Room;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Getter
@Setter
public abstract class Responsible implements HomeComponent  {

    private Queue<Activity> activities;
    private ResponsibleType responsibleType;
    private EntityStatus entityStatus = EntityStatus.FREE;
    private Room room = new NullRoom();

    protected Responsible(ResponsibleType responsibleType){
        this.responsibleType = responsibleType;
        activities = new LinkedList<>();
    }

    public void handle(Activity activity) throws InterruptedException {
        if(isFree()){
            activity.execute();
        }
        else{
            activities.add(activity);
        }
    }

    public boolean isFree(){
        return entityStatus == EntityStatus.FREE;
    }


    public void lock()  {
        setEntityStatus(EntityStatus.BUSY);
    }

    public void unlock() throws InterruptedException {
        setEntityStatus(EntityStatus.FREE);
        update();
    }

    public void relocate(Room room) {
        this.room.removeResponsible(this);
        room.addResponsible(this);
    }

    public void update() throws InterruptedException {
        if(isFree()) {
            Activity peek = activities.poll();
            if (peek != null && !peek.isExecuted()) peek.execute();
        }
    }

    public abstract boolean isNull();

    public String accept(SmartHomeVisitor visitor){
        return null;
    }

}
