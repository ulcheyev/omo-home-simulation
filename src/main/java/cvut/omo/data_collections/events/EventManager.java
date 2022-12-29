package cvut.omo.data_collections.events;

import cvut.omo.event.Event;

public class EventManager {


    public static void listenTo(Event event){

        try {
            event.solve();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

