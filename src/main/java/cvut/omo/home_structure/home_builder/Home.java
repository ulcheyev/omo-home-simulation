package cvut.omo.home_structure.home_builder;

import cvut.omo.app_utils.Utils;
import cvut.omo.entity.device.HomeAppliances;
import cvut.omo.entity.device.HomeDevice;
import cvut.omo.entity.Responsible;
import cvut.omo.entity.ResponsibleType;
import cvut.omo.entity.nulls.NullResponsible;
import cvut.omo.entity.living.person.Person;
import cvut.omo.entity.living.pet.Pet;
import cvut.omo.home_structure.Floor;
import cvut.omo.home_structure.HomeComponent;
import cvut.omo.home_structure.nulls.NullRoom;
import cvut.omo.home_structure.room_builder.Room;
import cvut.omo.home_structure.room_builder.RoomName;
import cvut.omo.entity.Usable;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Class represents home.
 */
@Getter
@Setter
public class Home {

    /**
     * Instance of {@link Home}
     */
    public final static Home INSTANCE = new Home();

    private List<Floor> floors;
    private List<Person> persons;
    private List<Pet> pets;
    private boolean isPowerEnable;
    private static int currFloorIdx = 0;


    private Home(){
        isPowerEnable = true;
        floors = new ArrayList<>();
        persons = new ArrayList<>();
        pets = new ArrayList<>();
    }

    /**
     * Adds floor to {@link #floors}
     * @param floor floor to add
     */
    public void addFloor(Floor floor){floors.add(floor);}

    /**
     * Adds entity to random floor and in random room in home
     * @param obj entity to add
     * @param <T> entity's class
     */
    public <T extends Responsible> void  addEntity( T obj ){
        int idxForFloor = Utils.getRandomInt(floors.size());
        int idxForRoom = Utils.getRandomInt(floors.get(idxForFloor).getRooms().size());
        floors.get(idxForFloor).getRooms().get(idxForRoom).addResponsible(obj);
    }

    /**
     * Returns all devices in home.
     * @return list of home devices
     */
    public List<HomeDevice> getHomeDevices(){
        List<HomeDevice> res = new ArrayList<>();
        floors.forEach(floor -> floor.getRooms()
                .forEach(room -> res.addAll(room.getHomeDevices())));
        return res;
    }

    /**
     * Returns home devices with specified class
     * @param clazz specified class
     * @return list of home devices
     */
    public List<HomeAppliances> getHomeAppliancesByClass(Class<? extends Usable> clazz){
        List<HomeDevice> hds = getHomeDevices();
        List<HomeAppliances> res = new ArrayList<>();
        for(HomeDevice hd: hds){
            if(hd.getClass().equals(clazz)){
                res.add((HomeAppliances)hd);
            }
        }
        return res;
    }

    /**
     * Returns all living responsibles in home
     * @return
     */
    public List<Responsible> getAllEntityResponsibles(){
        List<Responsible> responsibles = new ArrayList<>();
        for(Floor floor: floors){
            for (Room room: floor.getRooms()){
                responsibles.addAll(room.getResponsibles());
            }
        }
        return responsibles;
    }

    /**
     * Return list of {@link HomeComponent} in home.
     * In list: {@link Floor}, {@link Room}, {@link HomeDevice}, {@link Person}, {@link Pet}
     * @return list of {@link HomeComponent}
     */
    /* ROOM (devices in room are included) -> PERSONS -> PETS*/
    public List<HomeComponent> getComponentsForReport(){
        List<HomeComponent> homeComponents = new ArrayList<>();
        homeComponents.addAll(floors);
        homeComponents.addAll(getAllEntityResponsibles());
        return homeComponents;
    }


    /**
     * Return room which are in the home.
     * @return list of rooms
     */
    public List<Room> getAllRooms(){
        List<Room> rooms = new ArrayList<>();
        for(Floor floor: floors){
            rooms.addAll(floor.getRooms());
        }
        return rooms;
    }

    /**
     * Searches and returns responsible with specified responsible type.
     * If it does not exist -> return {@link NullResponsible}
     * @param role specified responsible type
     * @return responsible with specified responsible type
     */
    public Responsible searchResponsibleByType(ResponsibleType role) {
        List<Responsible> responsibles = new ArrayList<>();
        for(Floor floor: floors) {
            for (Room room : floor.getRooms()) {
                if(room.contains(role)){
                    responsibles.add(room.getResponsible(role));
                }
            }
        }
        if(responsibles.isEmpty()){
            return new NullResponsible();
        }
        return Utils.getRandomObjFromList(responsibles);
    }


    /**
     * Returns room with specified room name
     * @param type specified room name
     * @return room with specified room name
     */
    public Room searchRoomByType(RoomName type) {
        Room res = new NullRoom();
        for (Floor floor : floors) {
            for (Room room : floor.getRooms()) {
                if (room.getRoomName().equals(type)) {
                    res = room;
                }
            }
        }
        return res;
    }

    /**
     * Updates home.
     * Updates every floor in home.
     */
    public void update() {
        Thread thread = new Thread(
                () -> {
                    for(Floor floor: floors){
                        try {
                            floor.update();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        thread.start();
    };
}
