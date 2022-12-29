package cvut.omo.event.solve_strategy;

import cvut.omo.app_utils.Utils;
import cvut.omo.device.HomeAppliances;
import cvut.omo.entity.Responsible;
import cvut.omo.entity.ResponsibleType;
import cvut.omo.entity.activity.Activity;
import cvut.omo.entity.activity.ActivityFactory;
import cvut.omo.entity.activity.ActivityType;
import cvut.omo.entity.nulls.NullResponsible;
import cvut.omo.event.Event;
import cvut.omo.event.event_type.EventType;
import cvut.omo.home_structure.home_builder.Home;
import cvut.omo.home_structure.room_builder.Room;
import static cvut.omo.entity.activity.ActivityFactory.*;


import java.util.List;

/**
 *
 */
public class HomeEventSolveStrategy extends SolveStrategy {

    private EventType eventType;
    private Room room;
    private Event event;

    /**
     * @param event
     * @throws InterruptedException
     */
    @Override
    public void solve(Event event) throws InterruptedException {

        eventType = event.getEventType();
        room = event.getRoom();
        this.event = event;

        if (room.isNull()) {
            solveHomeEventWithNullRoom();
        } else {
            solveHomeEventWithNonNullRoom();
        }

    }

    private void solveHomeEventWithNonNullRoom() throws InterruptedException {

        if (room.isEmpty()) {
            handleResponsibles(event);
        }

        else {
            for (ActivityType activityType : eventType.getChainToSolve()) {
                checkRoomAndHandle(room, activityType, event);
            }
        }
    }

    private void solveHomeEventWithNullRoom() throws InterruptedException {

        for (ActivityType activityType : eventType.getChainToSolve()) {

            ResponsibleType responsibleType = giveRandomResponsibleType(activityType);

            //RESPONSIBLE IS DEVICE -> FOUND HOME DEVICES BY CLASS AND HANDLE
            if (responsibleType instanceof ActivityType.Device) {
                List<HomeAppliances> hds =
                        Home.INSTANCE.getHomeAppliancesByClass
                                        (((ActivityType.Device) responsibleType).getClazz());
                for (HomeAppliances homeDevice : hds) {
                    homeDevice.handle(createActivity(homeDevice, event, activityType));
                }

            }
            //RESPONSIBLE IS ENTITY -> FIND AND HANDLE
            else {
                Responsible responsible = giveEfficientResponsible(activityType);
                responsible.handle(createActivity(responsible, event, activityType));
            }
        }
    }

}
