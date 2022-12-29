package cvut.omo.data_collections.events;

import cvut.omo.app_utils.Constants;
import cvut.omo.app_utils.FileWriter;
import cvut.omo.app_utils.Utils;
import cvut.omo.event.Event;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 */
public class EventCollection {

    public static final EventCollection INSTANCE = new EventCollection();
    private EventCollection(){};
    private static List<Event> events = new ArrayList<>();

    /**
     * @param event
     */
    public static void addEvent(Event event){
        events.add(event);
        EventManager.listenTo(event);
    }

    /**
     * @param event
     */
    public static void removeEvent(Event event){
        events.remove(event);
    }

    /**
     * @param idx
     * @return
     */
    public static Event at(int idx){
        return events.get(idx);
    }

    /**
     * @return
     */
    public static int size(){
        return events.size();
    }


    /**
     * @return
     */
    public static boolean allSolved() {
        for(Event event: events){
            if(!event.checkSolving()){
                return false;
            };
        }
        return true;
    }

    /**
     * @param event
     */
    public static void swapTwoEndActivities(Event event){
        for(Event event1: events){
            if(event.equals(event1)){
                int last = event.getChainToSolve().size() - 1;
                int predLast = Math.max(last - 1, 0);
                Collections.swap(event.getChainToSolve(), predLast, last);
            }
        }
    }

    /**
     * @throws IOException
     */
    public static void generateReport() throws IOException {
        StringBuilder sb = new StringBuilder();
        EventCollectionReportIterator iterator = createReportIterator();
        sb.append(Constants.EVENT_REPORT_HEADER);
        while (iterator.hasNext()){
            sb.append(iterator.next()).append("\n");
        }

        FileWriter.generateNewReport("events_report"+ Utils.getRandomInt(), sb.toString());

    }

    /**
     * @return
     */
    public static EventCollectionReportIterator createReportIterator(){
        return new EventCollectionReportIterator();
    }
}
