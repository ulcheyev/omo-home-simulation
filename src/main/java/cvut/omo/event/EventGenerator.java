package cvut.omo.event;

import cvut.omo.app_utils.Utils;
import cvut.omo.entity.Responsible;
import cvut.omo.event.event_type.DeviceResponsibleEvent;
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

        int size = Home.INSTANCE.getAllEntityResponsibles().size();
        Responsible responsible = Home.INSTANCE.getAllEntityResponsibles().get(getRandomInt(size));

        while(!value.getClazz().equals(responsible.getClass())){
            value = values[getRandomInt(values.length)];
        }

        Event event = new Event(responsible, value);
        event.setRoom(responsible.getRoom());
    }

    public static void generateRandomHomeEvent(){
        HomeEvent[] values = HomeEvent.values();
        HomeEvent value = values[Utils.getRandomInt(values.length)];
        Room room = new NullRoom();

        if (value.getRoomName().equals(RoomName.STUB)) {
            new Event(room, value);
        }

        else{
            room = getRandomObjFromList(Home.INSTANCE.getAllRooms());
            RoomName roomName = room.getRoomName();

            while(!value.getRoomName().equals(roomName))
            {
                if(value.getRoomName().equals(RoomName.COMMON)){
                    break;
                }

                value = values[Utils.getRandomInt(values.length)];
            }

           new Event(room, value);
        }
    }

    public static void generateRandomDeviceEvent(){

        DeviceResponsibleEvent[] values = DeviceResponsibleEvent.values();
        DeviceResponsibleEvent value = values[Utils.getRandomInt(values.length)];

        Responsible homeDevice = (Responsible) getRandomObjFromList(Home.INSTANCE.getHomeDevices());

        while(!value.getHomeDevices().contains(homeDevice.getClass())){
            value = values[Utils.getRandomInt(values.length)];
        }

        Room room = homeDevice.getRoom();
        Event event = new Event(homeDevice, value);
        event.setRoom(room);
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
