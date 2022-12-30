package cvut.omo.event.solve_strategy;

import cvut.omo.entity.Responsible;
import cvut.omo.entity.activity.ActivityType;
import cvut.omo.event.Event;
import cvut.omo.event.event_type.EventType;
import lombok.NoArgsConstructor;

import static cvut.omo.entity.activity.ActivityFactory.createActivity;

@NoArgsConstructor
public class EntityEventSolveStrategy extends SolveStrategy {

    private Responsible responsible;
    public EntityEventSolveStrategy(Responsible responsible) {
        this.responsible = responsible;
    }


    @Override
    public void solve(Event event) throws InterruptedException {

        EventType eventType = event.getEventType();

        for (ActivityType type : eventType.getChainToSolve()) {

            if(checkContainingResponsible(type)){
                responsible.handle(createActivity(responsible, event, type));
            }

            else{
                Responsible responsible = giveEfficientResponsible(type);
                responsible.handle(createActivity(responsible, event, type));
            }

        }
    }

    private boolean checkContainingResponsible(ActivityType activityType){
        return activityType
                .getResponsibles()
                .contains(responsible.getResponsibleType());
    }


}
