package cvut.omo.home_structure.home_builder;

import cvut.omo.app_utils.Utils;
import cvut.omo.device.HomeAppliances;
import cvut.omo.device.HomeDevice;
import cvut.omo.entity.nulls.NullResponsible;
import cvut.omo.entity.Responsible;
import cvut.omo.entity.ResponsibleType;
import cvut.omo.entity.person.Person;
import cvut.omo.entity.pet.Pet;
import cvut.omo.home_structure.Floor;
import cvut.omo.home_structure.HomeComponent;
import cvut.omo.home_structure.nulls.NullRoom;
import cvut.omo.home_structure.room_builder.Room;
import cvut.omo.home_structure.room_builder.RoomName;
import cvut.omo.usable.Usable;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Home {

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

    public void addFloor(Floor floor){floors.add(floor);}

    public <T extends Responsible> void  addEntity( T obj ){
        int idxForFloor = Utils.getRandomInt(floors.size());
        int idxForRoom = Utils.getRandomInt(floors.get(idxForFloor).getRooms().size());
        floors.get(idxForFloor).getRooms().get(idxForRoom).addResponsible(obj);
    }

    public List<HomeDevice> getHomeDevices(){
        List<HomeDevice> res = new ArrayList<>();
        floors.forEach(floor -> floor.getRooms()
                .forEach(room -> res.addAll(room.getHomeDevices())));
        return res;
    }

    public void onAllDevices(){
    }


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

    public List<Responsible> getAllEntityResponsibles(){
        List<Responsible> responsibles = new ArrayList<>();
        for(Floor floor: floors){
            for (Room room: floor.getRooms()){
                responsibles.addAll(room.getResponsibles());
            }
        }
        return responsibles;
    }

    /* ROOM -> PERSONS -> PETS*/
    public List<HomeComponent> getComponentsForReport(){
        List<HomeComponent> homeComponents = new ArrayList<>();
        homeComponents.addAll(floors);
        homeComponents.addAll(getAllEntityResponsibles());
        return homeComponents;
    }


    public List<Room> getAllRooms(){
        List<Room> rooms = new ArrayList<>();
        for(Floor floor: floors){
            rooms.addAll(floor.getRooms());
        }
        return rooms;
    }

    public Responsible searchResponsibleByType(ResponsibleType role) {
        for(Floor floor: floors) {
            for (Room room : floor.getRooms()) {
                if(room.contains(role)){
                    return room.getResponsible(role);
                }
            }
        }
        return NullResponsible.INSTANCE;
    }



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

    public void update() throws InterruptedException {
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
