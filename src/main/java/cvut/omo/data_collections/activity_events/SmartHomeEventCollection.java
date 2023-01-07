package cvut.omo.data_collections.activity_events;

import cvut.omo.app_utils.Constants;
import cvut.omo.app_utils.FileWriter;
import cvut.omo.app_utils.Utils;
import cvut.omo.event.Event;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Collection, which stores every event.
 */
public class SmartHomeEventCollection {

    public static final SmartHomeEventCollection INSTANCE = new SmartHomeEventCollection();
    private SmartHomeEventCollection(){};
    private static List<Event> events = new ArrayList<>();

    /**
     * Add event to collection.
     * @param event event to add
     */
    public static void addEvent(Event event){
        events.add(event);
        EventManager.listenTo(event);
    }

    /**
     * Remove element from collection.
     * @param event event to remove
     */
    public static void removeEvent(Event event){
        events.remove(event);
    }

    /**
     * Return event on the specified index.
     * @param idx specified index
     * @return event on specified index
     */
    public static Event at(int idx){
        return events.get(idx);
    }

    /**
     * Returns size of collection.
     * @return size of collection
     */
    public static int size(){
        return events.size();
    }

    /**
     * Returns the entire collection.
     * @return list with entire events
     */
    public static List<Event> getAll(){return events;}

    /**
     * Check, if every event in collection is solved.
     * @return true if all events solved, false if not
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
     * Swap to activities at the end.
     * Using for creation the chain of activities.
     * @param event event, the activists of which will be swapped
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
     * Generate event report.
     * @throws IOException the directory does not exist
     */
    public static void generateEventReport() throws IOException {
        System.out.println("GENERATE EVENTS REPORT");
        StringBuilder sb = new StringBuilder();
        EventReportIterator iterator = createEventReportIterator();
        sb.append(Constants.EVENT_REPORT_HEADER);
        while (iterator.hasNext()){
            sb.append(iterator.next()).append("\n");
        }
        FileWriter.generateNewReport("events_report"+ Utils.getRandomInt(), sb.toString());
    }

    /**
     * Generate activity and usage report.
     * @throws IOException the directory does not exist
     */
    public static void generateActivityAndUsageReport() throws IOException {
        System.out.println("GENERATE ACTIVITY AND USAGE REPORT");
        StringBuilder sb = new StringBuilder();
        ActivityReportIterator iterator = createActivityReportIterator();
        sb.append(Constants.ACTIVITY_REPORT_HEADER);
        while (iterator.hasNext()){
            sb.append(iterator.next()).append("\n");
        }
        FileWriter.generateNewReport("activity_and_usage_report"+ Utils.getRandomInt(), sb.toString());
    }

    /**
     * @return {@link EventReportIterator} for create event report
     */
    public static EventReportIterator createEventReportIterator(){
        return new EventReportIterator();
    }

    /**
     * @return {@link ActivityReportIterator} for create activity and usage report
     */
    public static ActivityReportIterator createActivityReportIterator(){
        return new ActivityReportIterator();
    }

}
