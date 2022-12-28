package cvut.omo.data_collections.events;

import cvut.omo.app_utils.Constants;
import cvut.omo.app_utils.FileWriter;
import cvut.omo.app_utils.Utils;
import cvut.omo.event.Event;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EventCollection {

    public static final EventCollection INSTANCE = new EventCollection();
    private EventCollection(){};
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


    public static boolean allSolved() {
        for(Event event: events){
            if(!event.checkSolving()){
                return false;
            };
        }
        return true;
    }

    public static void generateReport() throws IOException {
        StringBuilder sb = new StringBuilder();
        EventCollectionReportIterator iterator = createReportIterator();
        sb.append(Constants.EVENT_REPORT_HEADER);
        while (iterator.hasNext()){
            sb.append(iterator.next()).append("\n");
        }

        FileWriter.generateNewReport("events_report"+ Utils.getRandomInt(), sb.toString());

    }

    public static EventCollectionReportIterator createReportIterator(){
        return new EventCollectionReportIterator();
    }
}
