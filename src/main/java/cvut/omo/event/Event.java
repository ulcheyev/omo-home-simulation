package cvut.omo.event;

import cvut.omo.data_collections.events.EventCollection;
import cvut.omo.entity.Responsible;
import cvut.omo.entity.activity.Activity;
import cvut.omo.entity.activity.ActivityType;
import cvut.omo.entity.nulls.NullResponsible;
import cvut.omo.event.event_type.DeviceResponsibleEvent;
import cvut.omo.event.event_type.EventType;
import cvut.omo.event.event_type.HomeEvent;
import cvut.omo.event.event_type.EntityResponsibleEvent;
import cvut.omo.event.solve_strategy.DeviceEventSolveStrategy;
import cvut.omo.event.solve_strategy.HomeEventSolveStrategy;
import cvut.omo.event.solve_strategy.EntityEventSolveStrategy;
import cvut.omo.event.solve_strategy.SolveStrategy;
import cvut.omo.home_structure.room_builder.Room;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 *
 */
@Getter
@Setter
public class Event {


    private Room room;
    private EventType eventType;
    private  boolean isSolved = false;
    private SolveStrategy solveStrategy;

    private List<Activity> chainToSolve = new ArrayList<>();
    private Responsible responsible = NullResponsible.INSTANCE;


    public Event(Room room, EventType eventType) {
        this.room = room;
        this.eventType = eventType;
        EventCollection.addEvent(this);
    }

    public Event(Responsible responsible, EventType eventType) {
        this.responsible = responsible;
        this.room = responsible.getRoom();
        this.eventType = eventType;
        EventCollection.addEvent(this);
    }

    /**
     * @return
     */
    public boolean isSolved(){
        return isSolved;
    }
    private Event(){}

    /**
     * @throws InterruptedException
     */
    public void solve() throws InterruptedException {

        if (eventType instanceof EntityResponsibleEvent) {
            solveStrategy = new EntityEventSolveStrategy(responsible);
            solveStrategy.solve(this);
        }

        else if (eventType instanceof HomeEvent) {
            solveStrategy = new HomeEventSolveStrategy();
            solveStrategy.solve(this);
        }

        else if (eventType instanceof DeviceResponsibleEvent) {
            solveStrategy = new DeviceEventSolveStrategy();
            solveStrategy.solve(this);
        }
    }

    /**
     * @param activity
     */
    public void addActivity(Activity activity){
        chainToSolve.add(activity);
    }

    /**
     * @return
     */
    //TODO
    public boolean checkSolving(){
       List<ActivityType> activityTypes = new ArrayList<>();
       for(Activity activity: chainToSolve){
           activityTypes.add(activity.getActivityType());
       }
       for(ActivityType activityType: eventType.getChainToSolve()){
           if(!activityTypes.contains(activityType)){
               return false;
           }
       }
       isSolved = true;
       return true;
    }

    /**
     * @return
     */
    public List<Activity> getReversedChainToSolve(){
        List<Activity> res = chainToSolve;
        Collections.reverse(res);
        return res;
    }

    /**
     * @return
     */
    @Override
    public String toString() {

        String type =
                !responsible.getResponsibleType().isNull()
                ? " by "  + responsible.getResponsibleType().toString()
                : !responsible.getClass().equals(NullResponsible.class)
                ? " by "  + responsible.getClass().getSimpleName()
                : "";

        String location = room.isNull()
                ? "Home"
                : room.getRoomName().toString()
                + " on the "
                + room.getFloor().getNumberOfFloor()
                + " floor";

        return "Event with type " +
                eventType.toString()+
                type +
                " in " +
                location;
    }
}
