package cvut.omo.data_collections.activity_events;

import cvut.omo.event.Event;

/**
 * Class is using for handling events.
 */
public class EventManager {

    /**
     * Listen to event creating and trying to solve it.
     *
     * @param event event, which needs to be solved
     */
    public static void listenTo(Event event) {
        try {
            event.solve();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

