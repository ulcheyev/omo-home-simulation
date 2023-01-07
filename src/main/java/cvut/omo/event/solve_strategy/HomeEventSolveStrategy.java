package cvut.omo.event.solve_strategy;

import cvut.omo.entity.device.HomeAppliances;
import cvut.omo.entity.Responsible;
import cvut.omo.entity.ResponsibleType;
import cvut.omo.entity.activity.ActivityType;
import cvut.omo.event.Event;
import cvut.omo.event.event_type.EventType;
import cvut.omo.home_structure.home_builder.Home;
import cvut.omo.home_structure.room_builder.Room;

import java.util.List;

import static cvut.omo.entity.activity.ActivityFactory.createActivity;

/**
 * Class is needed to solve events from {@link cvut.omo.event.event_type.HomeEvent}
 */
public class HomeEventSolveStrategy extends SolveStrategy {

    private EventType eventType;
    private Room room;
    private Event event;

    /**
     * Check, if event {@link Room} is null.
     * (NullRoom in {@link cvut.omo.home_structure.room_builder.RoomName} is according to STUB)
     * @param event event to solve.
     */
    @Override
    public void solve(Event event)  {

        eventType = event.getEventType();
        room = event.getRoom();
        this.event = event;

        if (room.isNull()) {
            solveHomeEventWithNullRoom();
        } else {
            solveHomeEventWithNonNullRoom();
        }

    }

    private void solveHomeEventWithNonNullRoom()  {

        if (room.isEmpty()) {
            handleResponsibles(event);
        }

        else {
            for (ActivityType activityType : eventType.getChainToSolve()) {
                checkRoomAndHandle(room, activityType, event);
            }
        }
    }

    private void solveHomeEventWithNullRoom() {

        for (ActivityType activityType : eventType.getChainToSolve()) {

            ResponsibleType responsibleType = giveRandomResponsibleType(activityType);

            if (responsibleType instanceof ActivityType.Device) {
                List<HomeAppliances> hds =
                        Home.INSTANCE.getHomeAppliancesByClass
                                        (((ActivityType.Device) responsibleType).getClazz());
                for (HomeAppliances homeDevice : hds) {
                    homeDevice.handle(createActivity(homeDevice, event, activityType));
                }

            }
            else {
                Responsible responsible = giveEfficientResponsible(activityType);
                responsible.handle(createActivity(responsible, event, activityType));
            }
        }
    }

}
