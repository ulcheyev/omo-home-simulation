package cvut.omo.event;

import cvut.omo.entity.Responsible;
import cvut.omo.home_structure.Room;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Event {
    private Room room;
    private EventType eventType;
    private boolean isSolved;
    private Responsible solver;
    private Strategy strategy;

    public Event(Room room, EventType eventType){
        room.getEvents().add(this);
        this.room = room;
        this.eventType = eventType;
    }

    public Event(Responsible responsible, EventType eventType){
        this.solver = responsible;
        this.room = responsible.getRoom();
        this.eventType = eventType;
    }

    public void solve() {

        if (eventType instanceof PersonEvent) {
            strategy = new PersonEventSolveStrategy(solver);
            strategy.solve(this);
        }

        if (eventType instanceof HomeEvent) {
            strategy = new HomeEventSolveStrategy();
            strategy.solve(this);
        }
    }

}
