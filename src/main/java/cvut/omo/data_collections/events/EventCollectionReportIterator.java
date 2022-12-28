package cvut.omo.data_collections.events;

import cvut.omo.data_collections.Iterator;
import cvut.omo.entity.Responsible;
import cvut.omo.entity.ResponsibleType;
import cvut.omo.entity.activity.Activity;
import cvut.omo.entity.activity.ActivityType;
import cvut.omo.entity.activity.BaseActivity;
import cvut.omo.entity.activity.RelocateActivity;
import cvut.omo.event.Event;
import cvut.omo.home_structure.Floor;
import cvut.omo.home_structure.HomeComponent;
import cvut.omo.home_structure.room_builder.Room;
import org.checkerframework.checker.units.qual.A;

import java.util.Collection;
import java.util.Collections;

public class EventCollectionReportIterator implements Iterator {

    private static int currIdx = 0;

    @Override
    public Object next() {

        StringBuilder sb = new StringBuilder();
        Event event = EventCollection.at(currIdx++);

        Room room = event.getRoom();
        Floor floor = event.getRoom().getFloor();
        Responsible responsible = event.getResponsible();

        sb
                .append("[")
                .append(currIdx)
                .append("] ")
                .append("Event with type ")
                .append(event.getEventType());

        if(!responsible.isNull()){
            sb.append(" by ")
                    .append(getResponsibleType(responsible))
                    .append(getRoomName(responsible,room))
                    .append(getFloor(room));
        }

        sb.append(". Solved by chain:\n");
        for(int idx = 1; idx <= event.getSolvesChain().size(); idx++){
            Activity activity = event.getChainToSolve().get(idx - 1);
            sb.append("---").append(idx).append(". ").append(activity.toString()).append("\n");
        }

        return sb.toString();

    }


    @Override
    public boolean hasNext() {
        return currIdx != EventCollection.size();
    }


    private String getResponsibleType(Responsible responsible){
        if(!responsible.getResponsibleType().isNull()){
            return responsible.getResponsibleType().toString();
        }else{
            return responsible.getClass().getSimpleName();
        }
    }

    private String getRoomName(Responsible responsible, Room room) {
        if(!room.isNull()){
            return " in " + room.getRoomName().name();
        }
        return " located in "
                + responsible.getRoom().getRoomName()
                + " on the " + responsible.getRoom().getFloor().getNumberOfFloor()
                + " floor ";
    }

    private String getFloor(Room room) {
        if(!room.isNull()){
            return " on " + room.getFloor().getNumberOfFloor().toString() + " floor";
        }
        return "";
    }

}
