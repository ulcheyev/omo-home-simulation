package cvut.omo.event;

import cvut.omo.entity.Responsible;
import cvut.omo.event.event_type.DeviceResponsibleEvent;
import cvut.omo.event.event_type.EntityResponsibleEvent;
import cvut.omo.event.event_type.EventType;
import cvut.omo.event.event_type.HomeEvent;
import cvut.omo.home_structure.home_builder.Home;
import cvut.omo.home_structure.nulls.NullRoom;
import cvut.omo.home_structure.room_builder.Room;
import cvut.omo.home_structure.room_builder.RoomName;

import static cvut.omo.app_utils.Utils.getRandomInt;
import static cvut.omo.app_utils.Utils.getRandomObjFromList;

public class EventGenerator {

    public static void generateRandomEntityEvent(){
        EntityResponsibleEvent[] values = EntityResponsibleEvent.values();
        EntityResponsibleEvent value = values[getRandomInt(values.length)];
        generatePersonEvent(value);
    }

    public static void generatePersonEvent(EntityResponsibleEvent eventType){

        int size = Home.INSTANCE.getAllEntityResponsibles().size();
        Responsible responsible = Home.INSTANCE.getAllEntityResponsibles().get(getRandomInt(size));

        while(!eventType.getClazz().equals(responsible.getClass())){
            responsible = Home.INSTANCE.getAllEntityResponsibles().get(getRandomInt(size));
        }

        Event event = new Event(responsible, eventType);
        if (eventType.getRoomNames().contains(RoomName.STUB)) {
            event.setRoom(new NullRoom());
        }else{
            event.setRoom(responsible.getRoom());
        }
    }

    public static void generateRandomHomeEvent(){
        HomeEvent[] values = HomeEvent.values();
        HomeEvent value = values[getRandomInt(values.length)];
        while(value.equals(HomeEvent.INFO)){
            value = values[getRandomInt(values.length)];
        }
        generateHomeEvent(value);
    }

    public static void generateHomeEvent(HomeEvent event){
        Room room = new NullRoom();

        if (event.getRoomName().equals(RoomName.STUB)) {
            new Event(room, event);
        }

        else{

            room = getRandomObjFromList(Home.INSTANCE.getAllRooms());

            while(!event.getRoomName().equals(room.getRoomName()))
            {
                if(event.getRoomName().equals(RoomName.COMMON)){
                    break;
                }

                room = getRandomObjFromList(Home.INSTANCE.getAllRooms());
            }

           new Event(room, event);
        }
    }
    public static void generateRandomDeviceEvent(){
        DeviceResponsibleEvent[] values = DeviceResponsibleEvent.values();
        DeviceResponsibleEvent value = values[getRandomInt(values.length)];
        while (value.equals(DeviceResponsibleEvent.DEVICE_BROKEN)) {
            value = values[getRandomInt(values.length)];
        }
        generateDeviceEvent(value);
    }


    public static void generateDeviceEvent(DeviceResponsibleEvent eventType){
        Responsible homeDevice = (Responsible) getRandomObjFromList(Home.INSTANCE.getHomeDevices());
        while(!eventType.getHomeDevices().contains(homeDevice.getClass())){
            homeDevice = (Responsible) getRandomObjFromList(Home.INSTANCE.getHomeDevices());
        }

        if(eventType == DeviceResponsibleEvent.FIRE_SENSOR_ALARM || eventType == DeviceResponsibleEvent.WATER_LEAK_SENSOR_ALARM){
            int rnd = getRandomInt(750);

            if(rnd == 1){
                Room room = homeDevice.getRoom();
                Event event = new Event(homeDevice, eventType);
                event.setRoom(room);
            }else{
                generateRandomDeviceEvent();
                return;
            }
        }
        Room room = homeDevice.getRoom();
        Event event = new Event(homeDevice, eventType);
        event.setRoom(room);
    }

    public static void generateEventWithResponsible(Responsible homeDevice, EventType eventType, String description){
        Room room = homeDevice.getRoom();
        Event event = new Event(homeDevice, eventType);
        event.setRoom(room);
        if(description != null){
            event.setDescription(description);
        }
    }

    public static void generateEvent(EventType eventType, String description){
        Event event = new Event(new NullRoom(), eventType);
        if(description != null){
            event.setDescription(description);
        }
    }

}
