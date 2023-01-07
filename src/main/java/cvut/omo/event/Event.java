package cvut.omo.event;

import cvut.omo.data_collections.activity_events.SmartHomeEventCollection;
import cvut.omo.entity.Responsible;
import cvut.omo.entity.activity.Activity;
import cvut.omo.entity.activity.ActivityType;
import cvut.omo.entity.nulls.NullResponsible;
import cvut.omo.event.event_type.DeviceResponsibleEvent;
import cvut.omo.event.event_type.LivingEntityResponsibleEvent;
import cvut.omo.event.event_type.EventType;
import cvut.omo.event.event_type.HomeEvent;
import cvut.omo.event.solve_strategy.DeviceEventSolveStrategy;
import cvut.omo.event.solve_strategy.LivingEntityEventSolveStrategy;
import cvut.omo.event.solve_strategy.HomeEventSolveStrategy;
import cvut.omo.event.solve_strategy.SolveStrategy;
import cvut.omo.home_structure.room_builder.Room;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class represents events that may appear in the {@link cvut.omo.home_structure.home_builder.Home}.
 * There may be a responsible for the event, who must solve it himself or there may not be one.
 */
@Getter
@Setter
public class Event {


    private Room room;
    private EventType eventType;
    private  boolean isSolved = false;
    private SolveStrategy solveStrategy;
    private String description;
    private List<Activity> chainToSolve = new ArrayList<>();
    private Responsible responsible = new NullResponsible();

    /**
     *
     * @param room event room
     * @param eventType event type
     */
    public Event(Room room, EventType eventType) {
        this.room = room;
        this.eventType = eventType;
        SmartHomeEventCollection.addEvent(this);
    }

    /**
     *
     * @param responsible responsible for event
     * @param eventType event type
     */
    public Event(Responsible responsible, EventType eventType) {
        this.responsible = responsible;
        this.room = responsible.getRoom();
        this.eventType = eventType;
        SmartHomeEventCollection.addEvent(this);
    }

    /**
     * Check, if event is solved.
     * @return true, if event is solved
     */
    public boolean isSolved(){
        return isSolved;
    }
    private Event(){}

    /**
     * Trying to solve this event. Choose correct strategy depends on {@link EventType}.
     * @throws InterruptedException {@link Thread}
     */
    public void solve() throws InterruptedException {

        if (eventType instanceof LivingEntityResponsibleEvent) {
            solveStrategy = new LivingEntityEventSolveStrategy(responsible);
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
     * Add {@link Activity} in chain to solve this event.
     * @param activity activity to add in chain
     */
    public void addActivity(Activity activity){
        chainToSolve.add(activity);
    }

    /**
     * Checks, if this event is solved (every activity must be executed successfully in {@link #chainToSolve}).
     * @return true, if event is solved
     */
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
     * Return string representation of event.
     * @return string representation of event
     */
    @Override
    public String toString() {

        String decs =
                description==null
                ? ""
                : " (" + description + ")";

        String resp_type =
                !responsible.getResponsibleType().isNull()
                ? " by "  + responsible.getResponsibleType().toString()
                : !responsible.getClass().equals(NullResponsible.class)
                ? " by "  + responsible.getClass().getSimpleName()
                : "";

        String location = room.isNull()
                ? ""
                : " in " + room.getRoomName().toString()
                + " on the "
                + room.getFloor().getNumberOfFloor()
                + " floor";

        return "Event with type " +
                eventType.toString() +
                resp_type +
                location +
                decs;
    }
}
