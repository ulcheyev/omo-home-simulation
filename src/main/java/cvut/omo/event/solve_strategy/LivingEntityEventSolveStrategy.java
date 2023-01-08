package cvut.omo.event.solve_strategy;

import cvut.omo.entity.Responsible;
import cvut.omo.entity.activity.ActivityType;
import cvut.omo.event.Event;
import cvut.omo.event.event_type.EventType;
import lombok.NoArgsConstructor;

import static cvut.omo.entity.activity.ActivityFactory.createActivity;

/**
 * Class is needed to solve events from {@link cvut.omo.event.event_type.LivingEntityResponsibleEvent}.
 */
@NoArgsConstructor
public class LivingEntityEventSolveStrategy extends SolveStrategy {

    private Responsible responsible;

    /**
     * @param responsible for solve this event
     */
    public LivingEntityEventSolveStrategy(Responsible responsible) {
        this.responsible = responsible;
    }


    /**
     * if responsible can solve the problem himself -> try to solve.
     * if not -> search responsible and solve.
     *
     * @param event event to solve.
     */
    @Override
    public void solve(Event event) {

        EventType eventType = event.getEventType();

        for (ActivityType type : eventType.getChainToSolve()) {

            if (checkContainingResponsible(type)) {
                responsible.handle(createActivity(responsible, event, type));
            } else {
                Responsible responsible = giveEfficientResponsible(type);
                responsible.handle(createActivity(responsible, event, type));
            }

        }
    }

    private boolean checkContainingResponsible(ActivityType activityType) {
        return activityType.getResponsibles().contains(responsible.getResponsibleType());
    }


}
