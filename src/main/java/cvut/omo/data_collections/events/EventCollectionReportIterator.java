package cvut.omo.data_collections.events;

import cvut.omo.data_collections.Iterator;
import cvut.omo.entity.activity.Activity;
import cvut.omo.event.Event;

import java.util.List;

/**
 *
 */
public class EventCollectionReportIterator implements Iterator {

    private static int currIdx = 0;

    /**
     * @return
     */
    @Override
    public Object next() {
        StringBuilder sb = new StringBuilder();
        Event event = EventCollection.at(currIdx++);

        sb
                .append("[")
                .append(currIdx)
                .append("] ")
                .append(event.toString());


        sb.append(". Solved by chain:\n");

        List<Activity> chainToSolve = event.getChainToSolve();

        for(int idx = 1; idx <= chainToSolve.size(); idx++){
            Activity activity = chainToSolve.get(idx - 1);
            sb.append("---").append(idx).append(". ").append(activity.toString()).append("\n");
        }

        return sb.toString();

    }

    /**
     * @return
     */
    @Override
    public boolean hasNext() {
        return currIdx != EventCollection.size();
    }



}
