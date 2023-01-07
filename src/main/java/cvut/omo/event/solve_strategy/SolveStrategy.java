package cvut.omo.event.solve_strategy;

import cvut.omo.app_utils.Utils;
import cvut.omo.entity.Responsible;
import cvut.omo.entity.ResponsibleType;
import cvut.omo.entity.activity.ActivityType;
import cvut.omo.entity.nulls.NullResponsible;
import cvut.omo.event.Event;
import cvut.omo.home_structure.home_builder.Home;
import cvut.omo.home_structure.room_builder.Room;

import java.util.List;

import static cvut.omo.entity.activity.ActivityFactory.createActivity;

/**
 * Generalizing class for event solving strategies.
 */
public abstract class SolveStrategy {


    /**
     * Try to solve event.
     * @param event event to solve.
     */
    public abstract void solve(Event event);


    /**
     * Finds efficient responsible for specified activity type.
     * @param activityType activity type, which need responsible
     * @return founded responsible for specified activity type
     */
    protected Responsible giveEfficientResponsible(ActivityType activityType){

        List<ResponsibleType> responsiblesTypes = activityType.getResponsibles();
        Responsible responsible;

        for(ResponsibleType responsibleType: responsiblesTypes){
             responsible = Home.INSTANCE.searchResponsibleByType(responsibleType);
            if(responsible.isFree()){
                return responsible;
            }
        }
        return Home.INSTANCE.searchResponsibleByType(giveRandomResponsibleType(activityType));

    }

    /**
     * Search responsible for every activity in specified event and
     * gives them the task to handle this activity.
     * @param event specified {@link Event}
     */
    protected void handleResponsibles(Event event)  {
        for(ActivityType activityType: event.getEventType().getChainToSolve()){
            Responsible responsible = giveEfficientResponsible(activityType);
            responsible.handle(createActivity(responsible, event, activityType));
        }
    }

    /**
     * Check, if specified room contains responsible from specified activity type.
     * If room does not contain, {@link #giveEfficientResponsible(ActivityType)}
     * @param room specified {@link Room}
     * @param activityType specified {@link ActivityType}
     * @param event {@link Event}
     */
    protected void checkRoomAndHandle(Room room, ActivityType activityType, Event event) {

        Responsible responsible = new NullResponsible();

        for(ResponsibleType responsibleType: activityType.getResponsibles()) {
            if (room.contains(responsibleType)) {
                responsible = room.getResponsible(responsibleType);
            }
        }
        if(responsible.isNull()){
            responsible = giveEfficientResponsible(activityType);
        }
        responsible.handle(createActivity(responsible, event, activityType));
    }


    /**
     * Return random responsible type from specified activity type.
     * @param activityType specified {@link ActivityType}
     * @return random {@link Responsible}
     */
    protected ResponsibleType giveRandomResponsibleType(ActivityType activityType){
        return Utils.getRandomObjFromList(activityType.getResponsibles());
    }





}
