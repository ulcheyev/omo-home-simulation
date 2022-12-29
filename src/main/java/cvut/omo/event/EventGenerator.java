package cvut.omo.event;

import cvut.omo.app_utils.Utils;
import cvut.omo.device.HomeDevice;
import cvut.omo.entity.Responsible;
import cvut.omo.event.event_type.DeviceResponsibleEvent;
import cvut.omo.event.event_type.EventType;
import cvut.omo.event.event_type.HomeEvent;
import cvut.omo.event.event_type.EntityResponsibleEvent;
import cvut.omo.home_structure.home_builder.Home;
import cvut.omo.home_structure.nulls.NullRoom;
import cvut.omo.home_structure.room_builder.Room;
import cvut.omo.home_structure.room_builder.RoomName;

import static cvut.omo.app_utils.Utils.getRandomInt;
import static cvut.omo.app_utils.Utils.getRandomObjFromList;

public class EventGenerator {

    public static void generateRandomPersonEvent(){
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
        event.setRoom(responsible.getRoom());
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
        generateDeviceEvent(values[Utils.getRandomInt(values.length)]);
    }


    public static void generateDeviceEvent(DeviceResponsibleEvent eventType){
        Responsible homeDevice = (Responsible) getRandomObjFromList(Home.INSTANCE.getHomeDevices());
        while(!eventType.getHomeDevices().contains(homeDevice.getClass())){
            homeDevice = (Responsible) getRandomObjFromList(Home.INSTANCE.getHomeDevices());
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



    public static void generateRandomEvents(int numberOfEvents){

        int localNum = numberOfEvents;
        while(localNum > 0){

            int myRandomNumber = Utils.getRandomInt();

            if(myRandomNumber % 2 == 0){
                EventGenerator.generateRandomDeviceEvent();
            }

            else if(myRandomNumber % 3 == 0){
                EventGenerator.generateRandomPersonEvent();
            }

            else{
                EventGenerator.generateRandomHomeEvent();
            }

            localNum--;
        }
    }
}
