package cvut.omo.event.solve_strategy;

import cvut.omo.entity.ActivityType;
import cvut.omo.entity.person.Person;
import cvut.omo.event.Event;
import cvut.omo.home_structure.Room;

import java.util.List;

public class ResponsibleIsNotPresentSolveStrategy implements SolveStrategy{
    @Override
    public void solve(Event event) {
        Room room = event.getRoom();
        List<Person> persons = room.getPersons();
        List<ActivityType> chainToSolve = event.getEventType().getChainToSolve();
    }
}
