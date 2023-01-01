package cvut.omo.entity;

import cvut.omo.data_collections.visitor.SmartHomeReportVisitor;
import cvut.omo.entity.activity.Activity;
import cvut.omo.entity.activity.WaitingActivity;
import cvut.omo.entity.living.LivingEntityStatus;
import cvut.omo.event.Event;
import cvut.omo.event.EventGenerator;
import cvut.omo.event.event_type.HomeEvent;
import cvut.omo.home_structure.HomeComponent;
import cvut.omo.home_structure.nulls.NullRoom;
import cvut.omo.home_structure.room_builder.Room;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Class represents entity, which can do some {@link Activity}
 */
@Getter
@Setter
public abstract class Responsible implements HomeComponent  {


    private Queue<Activity> activities;
    private ResponsibleType responsibleType;
    private LivingEntityStatus livingEntityStatus = LivingEntityStatus.FREE;
    private Room room = new NullRoom();



    /**
     * Constructor for responsible
     * @param responsibleType any type, which extend {@link ResponsibleType}
     */
    protected Responsible(ResponsibleType responsibleType){
        this.responsibleType = responsibleType;
        activities = new LinkedList<>();
    }

    /**
     * Method processes the activity supplied to the parameter.
     * If current responsible is busy, puts activity in responsible activities queue.
     * @param activity activity to handle
     */
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

    /**
     *
     * {@return true, if current responsible is free}
     */
    public boolean isFree(){
        return livingEntityStatus == LivingEntityStatus.FREE;
    }

    /**
     * Lock current person, set status to busy
     */
    public void lock()  {
        setLivingEntityStatus(LivingEntityStatus.BUSY);
    }

    /**
     * Unlock current person, set status to free
     */
    public void unlock() {
        setLivingEntityStatus(LivingEntityStatus.FREE);
        update();
    }

    /**
     * Move to specified room
     * @param room
     */
    public void relocate(Event event, Room room) {
        this.room.removeResponsible(this);
        room.addResponsible(this);
    }

    /**
     * Update current responsible.
     * Poll activity from {@link #activities}. Trying to solve it.
     */
    public void update() {
        if(isFree()) {
            Activity peek = activities.poll();
            if (peek != null && !peek.isExecuted()) {
                peek.execute();
            }
        }
    }

    /**
     * Responsible go to sleep. Lock then unlock.
     */
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

     /**
     *
     * @return true, if this responsible is not null. {@link cvut.omo.entity.nulls.NullResponsible}
     */
    public abstract boolean isNull();

    @Override
    public Object accept(SmartHomeReportVisitor visitor) {return null;}

}
