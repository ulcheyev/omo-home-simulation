package cvut.omo.event.event_type;


import cvut.omo.entity.activity.ActivityType;

import java.util.List;

/**
 * Interface for event type, which can be appeared in {@link cvut.omo.home_structure.home_builder.Home}
 */
public interface EventType {
    List<ActivityType> getChainToSolve();
}
