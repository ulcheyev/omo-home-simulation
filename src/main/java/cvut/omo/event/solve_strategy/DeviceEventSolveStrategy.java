package cvut.omo.event.solve_strategy;

import cvut.omo.app_utils.Utils;
import cvut.omo.entity.Responsible;
import cvut.omo.entity.ResponsibleType;
import cvut.omo.entity.activity.ActivityFactory;
import cvut.omo.entity.activity.ActivityType;
import cvut.omo.event.Event;
import cvut.omo.home_structure.room_builder.Room;

public class DeviceEventSolveStrategy extends SolveStrategy {

    @Override
    public void solve(Event event) throws InterruptedException {
        Room room = event.getRoom();

        if(room.isEmpty()){
            handleResponsibles(event);
        }
        else
        {
            for (ActivityType type : event.getEventType().getChainToSolve()) {
                checkRoomAndHandle(room, type, event);
            }
        }
    }
}
