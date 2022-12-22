package cvut.omo.event;

import cvut.omo.entity.Responsible;
import cvut.omo.entity.activity.ActivityFactory;
import cvut.omo.entity.activity.ActivityType;
import cvut.omo.entity.activity.Relocate;
import cvut.omo.home_structure.Home;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PersonEventSolveStrategy implements Strategy {

    private Responsible person;

    public PersonEventSolveStrategy(Responsible person) {
        this.person = person;
    }

    @Override
    public void solve(Event event) {

        EventType eventType = event.getEventType();

        for (ActivityType type : eventType.getChainToSolve()) {

            if(type.getRoomType() != person.getRoom().getRoomType()) {
                person.handle(new Relocate(Home.INSTANCE.searchRoom(type.getRoomType()), ActivityType.RELOCATE));
            }

            person.handle(ActivityFactory.create(Home.INSTANCE.searchRoom(type.getRoomType()), type));
        }
    }
}
