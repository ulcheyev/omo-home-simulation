package cvut.omo.event.solve_strategy;

import cvut.omo.entity.activity.ActivityType;
import cvut.omo.event.Event;
import cvut.omo.home_structure.room_builder.Room;

/**
 * Class is needed to solve events from {@link cvut.omo.event.event_type.DeviceResponsibleEvent}.
 */
public class DeviceEventSolveStrategy extends SolveStrategy {


    /**
     * The solution to the event depends on the room in which the event appeared.
     * If it's empty, just need to find the responsible. if not,
     * check if there are responsible for each activity's in event chain to solve
     *
     * @param event event to solve.
     */
    @Override
    public void solve(Event event) {

        Room room = event.getRoom();

        if (room.isEmpty()) {
            handleResponsibles(event);
        } else {
            for (ActivityType type : event.getEventType().getChainToSolve()) {
                checkRoomAndHandle(room, type, event);
            }
        }
    }
}
