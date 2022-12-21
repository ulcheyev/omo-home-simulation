package cvut.omo.event;

import cvut.omo.entity.Activity;
import cvut.omo.entity.ActivityType;
import cvut.omo.entity.ResponsibleType;
import cvut.omo.entity.person.Person;
import cvut.omo.entity.person.PersonStatus;
import cvut.omo.event.solve_strategy.ResponsibleIsNotPresentSolveStrategy;
import cvut.omo.event.solve_strategy.SolveStrategy;
import cvut.omo.home_structure.Home;
import cvut.omo.home_structure.Room;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Event {

    private Room room;
    private EventType eventType;
    private SolveStrategy strategy;
    private boolean isSolved;

    public Event(EventType eventType){
        this.eventType = eventType;
        if(room.isEmpty()){
            strategy = new ResponsibleIsNotPresentSolveStrategy();
        }
    }

    public void solve(){
        strategy.solve(this);
    }



}
