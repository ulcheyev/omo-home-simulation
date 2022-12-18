package cvut.omo.event;

import cvut.omo.home_structure.Room;

public class Event {

    private Room spawnPlace;
    private EventType eventType;

    Event(EventType eventType){
        this.eventType = eventType;
    }

}
