package cvut.omo.data_collections.events;

import cvut.omo.event.Event;

/**
 *
 */
public class EventManager {


    /**
     * @param event
     */
    public static void listenTo(Event event){

        try {
            event.solve();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

