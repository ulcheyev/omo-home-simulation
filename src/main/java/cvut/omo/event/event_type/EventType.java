package cvut.omo.event.event_type;


import cvut.omo.entity.activity.ActivityType;

import java.util.List;

public interface EventType {

    List<ActivityType> getChainToSolve();
    EventImportance getEventImportance();

     enum EventImportance {
        EMERGENCY,
        NOT_EMERGENCY
    }
}
