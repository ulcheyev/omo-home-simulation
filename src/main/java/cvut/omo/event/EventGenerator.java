package cvut.omo.event;

import java.util.Random;

public class EventGenerator {

    private static final Random rand = new Random();

    public static Event generateEvent(){
        EventType[] values = EventType.values();
        return new Event(values[rand.nextInt(values.length)]);
    }

}
