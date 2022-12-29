package cvut.omo.event.solve_strategy;

import cvut.omo.app_utils.Utils;
import cvut.omo.entity.Responsible;
import cvut.omo.entity.ResponsibleType;
import cvut.omo.entity.activity.ActivityType;
import cvut.omo.entity.nulls.NullResponsible;
import cvut.omo.event.Event;
import cvut.omo.home_structure.home_builder.Home;
import cvut.omo.home_structure.room_builder.Room;

import static cvut.omo.entity.activity.ActivityFactory.*;


import java.util.List;

/**
 *
 */
public abstract class SolveStrategy {


    /**
     * @param event
     * @throws InterruptedException
     */
    public abstract void solve(Event event) throws InterruptedException;


    protected Responsible giveEfficientResponsible(ActivityType activityType){

        List<ResponsibleType> responsiblesTypes = activityType.getResponsibles();
        Responsible responsible;
        //RETURN FREE
        for(ResponsibleType responsibleType: responsiblesTypes){
             responsible = Home.INSTANCE.searchResponsibleByType(responsibleType);
            if(responsible.isFree()){
                return responsible;
            }
        }
        //RETURN RANDOM
        return Home.INSTANCE.searchResponsibleByType(giveRandomResponsibleType(activityType));

    }

    protected void handleResponsibles(Event event) throws InterruptedException {
        for(ActivityType activityType: event.getEventType().getChainToSolve()){
            Responsible responsible = giveEfficientResponsible(activityType);
            responsible.handle(createActivity(responsible, event, activityType));
        }
    }


    protected void checkRoomAndHandle(Room room, ActivityType activityType, Event event) throws InterruptedException {

        Responsible responsible = NullResponsible.INSTANCE;

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


    protected ResponsibleType giveRandomResponsibleType(ActivityType activityType){
        return activityType.getResponsibles().get(Utils.getRandomInt(activityType.getResponsibles().size()));
    }





}
