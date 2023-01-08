package cvut.omo.data_collections.activity_events;

import cvut.omo.data_collections.Iterator;
import cvut.omo.entity.activity.Activity;
import cvut.omo.event.Event;

import java.util.List;

/**
 * Class represents iterating function over SmartHomeCollection to generate Events report.
 */
public class EventReportIterator implements Iterator {

    private static int currIdx = 0;

    @Override
    public Object next() {
        StringBuilder sb = new StringBuilder();
        Event event = SmartHomeEventCollection.at(currIdx++);

        sb
                .append("[")
                .append(currIdx)
                .append("] ")
                .append(event.toString());

        List<Activity> chainToSolve = event.getChainToSolve();

        if (!chainToSolve.isEmpty()) {
            sb.append(". Solved by chain:\n");

            for (int idx = 1; idx <= chainToSolve.size(); idx++) {
                Activity activity = chainToSolve.get(idx - 1);
                sb.append("---").append(idx).append(". ").append(activity.toString()).append("\n");
            }
        } else {
            sb.append("\n");
        }

        return sb.toString();

    }

    @Override
    public boolean hasNext() {
        return currIdx != SmartHomeEventCollection.size();
    }


}
