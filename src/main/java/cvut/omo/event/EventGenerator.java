package cvut.omo.event;

import cvut.omo.app_utils.Utils;
import cvut.omo.entity.Responsible;
import cvut.omo.event.event_type.DeviceResponsibleEvent;

import static cvut.omo.event.event_type.DeviceResponsibleEvent.*;

import cvut.omo.event.event_type.LivingEntityResponsibleEvent;
import cvut.omo.event.event_type.EventType;
import cvut.omo.event.event_type.HomeEvent;
import cvut.omo.home_structure.home_builder.Home;
import cvut.omo.home_structure.nulls.NullRoom;
import cvut.omo.home_structure.room_builder.Room;
import cvut.omo.home_structure.room_builder.RoomName;

import java.util.List;

import static cvut.omo.app_utils.Utils.getRandomInt;
import static cvut.omo.app_utils.Utils.getRandomObjFromList;

/**
 * Class providing functions for generating events.
 */
public class EventGenerator {

    /**
     * Generates {@link Event} with random type from {@link LivingEntityResponsibleEvent}.
     */
    public static void generateRandomEntityEvent() {
        LivingEntityResponsibleEvent[] values = LivingEntityResponsibleEvent.values();
        LivingEntityResponsibleEvent value = values[getRandomInt(values.length)];
        generatePersonEvent(value);
    }

    /**
     * Generates event with specified event type.
     * Check, if room is stub -> set {@link NullRoom}.
     * Search and set {@link Responsible} for generated {@link Event}.
     *
     * @param eventType specified event type
     */
    public static void generatePersonEvent(LivingEntityResponsibleEvent eventType) {

        int size = Home.INSTANCE.getAllEntityResponsibles().size();
        Responsible responsible = Home.INSTANCE.getAllEntityResponsibles().get(getRandomInt(size));

        while (!eventType.getClazz().equals(responsible.getClass())) {
            responsible = Home.INSTANCE.getAllEntityResponsibles().get(getRandomInt(size));
        }

        Event event = new Event(responsible, eventType);

        if (eventType.getRoomNames().contains(RoomName.STUB)) {
            event.setRoom(new NullRoom());
        } else {
            event.setRoom(responsible.getRoom());
        }
    }

    /**
     * Generates {@link Event} with random type from {@link HomeEvent}.
     */
    public static void generateRandomHomeEvent() {
        HomeEvent[] values = HomeEvent.values();
        HomeEvent value = values[getRandomInt(values.length)];
        while (value.equals(HomeEvent.INFO)) {
            value = values[getRandomInt(values.length)];
        }
        generateHomeEvent(value);
    }

    /**
     * Generates event with specified event type.
     * Check, if room is stub -> set {@link NullRoom}.
     * If room is not stub, search a suitable room, see {@link HomeEvent}.
     *
     * @param eventType specified event type
     */
    public static void generateHomeEvent(HomeEvent eventType) {
        Room room = new NullRoom();

        if (eventType.getRoomName().equals(RoomName.STUB)) {
            new Event(room, eventType);
        } else {

            room = getRandomObjFromList(Home.INSTANCE.getAllRooms());

            while (!eventType.getRoomName().equals(room.getRoomName())) {
                if (eventType.getRoomName().equals(RoomName.COMMON)) {
                    break;
                }

                room = getRandomObjFromList(Home.INSTANCE.getAllRooms());
            }

            new Event(room, eventType);
        }
    }

    /**
     * Generates {@link Event} with random type from {@link DeviceResponsibleEvent}.
     */
    public static void generateRandomDeviceEvent() {
        DeviceResponsibleEvent[] values = DeviceResponsibleEvent.values();
        DeviceResponsibleEvent value = values[getRandomInt(values.length)];
        while (value.equals(DeviceResponsibleEvent.DEVICE_BROKEN)) {
            value = values[getRandomInt(values.length)];
        }
        generateDeviceEvent(value);
    }


    /**
     * Generates event with specified event type.
     *
     * @param eventType specified type
     */
    public static void generateDeviceEvent(DeviceResponsibleEvent eventType) {

        Responsible homeDevice = (Responsible) getRandomObjFromList(Home.INSTANCE.getHomeDevices());


        while (!eventType.getHomeDevices().contains(homeDevice.getClass())) {
            homeDevice = (Responsible) getRandomObjFromList(Home.INSTANCE.getHomeDevices());
        }

        if (eventType.equals(DeviceResponsibleEvent.FIRE_SENSOR_ALARM) || eventType.equals(DeviceResponsibleEvent.WATER_LEAK_SENSOR_ALARM)) {

            boolean probability = Utils.yesOrNo(0.09f);
            if (!probability) {
                eventType = skipAlarmEvents();
            }
        }
        Room room = homeDevice.getRoom();
        Event event = new Event(homeDevice, eventType);
        event.setRoom(room);
    }

    private static DeviceResponsibleEvent skipAlarmEvents() {
        DeviceResponsibleEvent[] values = DeviceResponsibleEvent.values();
        DeviceResponsibleEvent eventType = getRandomObjFromList(List.of(values));
        while (eventType.equals(FIRE_SENSOR_ALARM) || eventType.equals(WATER_LEAK_SENSOR_ALARM)) {
            eventType = getRandomObjFromList(List.of(values));
        }
        return eventType;
    }

    /**
     * Generates event with responsible, which will solve it.
     *
     * @param responsible responsible for event
     * @param eventType   specified event type
     * @param description description for event
     */
    public static void generateEventWithResponsible(Responsible responsible, EventType eventType, String description) {
        Room room = responsible.getRoom();
        Event event = new Event(responsible, eventType);
        event.setRoom(room);
        if (description != null) {
            event.setDescription(description);
        }
    }

    /**
     * Generates event. Sets event room to {@link NullRoom}.
     *
     * @param eventType   event type
     * @param description description for event
     */
    public static void generateEvent(EventType eventType, String description) {
        Event event = new Event(new NullRoom(), eventType);
        if (description != null) {
            event.setDescription(description);
        }
    }

}
