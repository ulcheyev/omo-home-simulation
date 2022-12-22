package cvut.omo.event;

import cvut.omo.entity.Responsible;
import cvut.omo.entity.activity.ActivityType;
import cvut.omo.home_structure.Floor;
import cvut.omo.home_structure.Home;
import cvut.omo.home_structure.Room;

import java.util.ArrayList;
import java.util.List;

import static cvut.omo.app_utils.Randomizer.getRandomInt;
import static cvut.omo.app_utils.Randomizer.yesOrNo;

public class EventGenerator {

    public static void generateRandomEventsInRandomRooms(float fullness){

        List<Room> rooms = new ArrayList<>();
        for(Floor floor: Home.INSTANCE.getFloors()){
            rooms.addAll(floor.getRooms());
        }
        for(Room room: rooms){
            if(yesOrNo(fullness)){
                generateEvent(room);
            }
        }
    }

    public static Event generateEvent(Room room){
        HomeEvent[] values = HomeEvent.values();
        HomeEvent value = values[getRandomInt(values.length)];
        if(room.isEmpty()) {

        }
        for(ActivityType activityType: value.getChainToSolve()){
            if(activityType.getRoomType() != room.getRoomType()){
                generateEvent(room);
            }
        }
        return new Event(room, value);
    }


    public static Event generateRandomPersonEvent(Responsible responsible){
        PersonEvent[] values = PersonEvent.values();
        PersonEvent value = values[getRandomInt(values.length)];
        return new Event(responsible, value);
    }

    public static Event generateRandomHomeEvent(Room room){
        HomeEvent[] values = HomeEvent.values();
        HomeEvent value = null;
        for(HomeEvent homeEvent: values){
            if(room.getRoomType() == homeEvent.getRoomType()){
                value = homeEvent;
            }
        }
        if(value == null){
            value = HomeEvent.STUB;
        }

        return new Event(room, value);
    }
}
