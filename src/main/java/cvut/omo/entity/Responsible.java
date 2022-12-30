package cvut.omo.entity;
import cvut.omo.data_collections.visitor.SmartHomeVisitor;
import cvut.omo.entity.activity.Activity;
import cvut.omo.entity.activity.ActivityFactory;
import cvut.omo.entity.activity.WaitingActivity;
import cvut.omo.entity.nulls.NullResponsible;
import cvut.omo.entity.nulls.NullResponsibleType;
import cvut.omo.event.Event;
import cvut.omo.event.EventGenerator;
import cvut.omo.event.event_type.HomeEvent;
import cvut.omo.exceptions.OMOException;
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

    public void handle(Activity activity){

        if(isFree()){
            activity.execute();
        }
        else{
            if(!(activity instanceof WaitingActivity)) {
                String some_text = "Is busy. The activity "
                        + activity.getActivityType() + " will wait...";

                EventGenerator.generateEventWithResponsible(this, HomeEvent.INFO,
                        some_text);
            }
            activities.add(activity);
        }
    }

    public boolean isFree(){
        return entityStatus == EntityStatus.FREE;
    }


    public void lock()  {
        setEntityStatus(EntityStatus.BUSY);
    }

    public void unlock() {
        setEntityStatus(EntityStatus.FREE);
        update();
    }

    public void relocate(Event event, Room room) {
        this.room.removeResponsible(this);
        room.addResponsible(this);
    }

    public void update() {
        if(isFree()) {
            Activity peek = activities.poll();
            if (peek != null && !peek.isExecuted()) {
                peek.execute();
            }
        }
    }

    public void goToSleep(){
        lock();
        Thread thread = new Thread(() -> {
            int sleep_count = 8;
            while(sleep_count > 0){
                try {
                    Thread.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sleep_count--;
            }
            unlock();
        });
        thread.start();
    }

    public abstract boolean isNull();

    public String accept(SmartHomeVisitor visitor){
        return null;
    }


}
