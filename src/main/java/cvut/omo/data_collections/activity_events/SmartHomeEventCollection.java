package cvut.omo.data_collections.activity_events;

import cvut.omo.app_utils.Constants;
import cvut.omo.app_utils.FileWriter;
import cvut.omo.app_utils.Utils;
import cvut.omo.event.Event;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SmartHomeEventCollection {

    public static final SmartHomeEventCollection INSTANCE = new SmartHomeEventCollection();
    private SmartHomeEventCollection(){};
    private static List<Event> events = new ArrayList<>();

    public static void addEvent(Event event){
        events.add(event);
        EventManager.listenTo(event);
    }

    public static void removeEvent(Event event){
        events.remove(event);
    }

    public static Event at(int idx){
        return events.get(idx);
    }

    public static int size(){
        return events.size();
    }

    public static List<Event> getAll(){return events;}


    public static boolean allSolved() {
        for(Event event: events){
            if(!event.checkSolving()){
                return false;
            };
        }
        return true;
    }

    public static void swapTwoEndActivities(Event event){
        for(Event event1: events){
            if(event.equals(event1)){
                int last = event.getChainToSolve().size() - 1;
                int predLast = Math.max(last - 1, 0);
                Collections.swap(event.getChainToSolve(), predLast, last);
            }
        }
    }

    public static void generateEventReport() throws IOException {
        StringBuilder sb = new StringBuilder();
        EventReportIterator iterator = createEventReportIterator();
        sb.append(Constants.EVENT_REPORT_HEADER);
        while (iterator.hasNext()){
            sb.append(iterator.next()).append("\n");
        }
        FileWriter.generateNewReport("events_report"+ Utils.getRandomInt(), sb.toString());
    }

    public static void generateActivityAndUsageReport() throws IOException {
        StringBuilder sb = new StringBuilder();
        ActivityReportIterator iterator = createActivityReportIterator();
        sb.append(Constants.ACTIVITY_REPORT_HEADER);
        while (iterator.hasNext()){
            sb.append(iterator.next()).append("\n");
        }
        FileWriter.generateNewReport("activity_and_usage_report"+ Utils.getRandomInt(), sb.toString());
    }

    public static EventReportIterator createEventReportIterator(){
        return new EventReportIterator();
    }
    public static ActivityReportIterator createActivityReportIterator(){
        return new ActivityReportIterator();
    }

}
