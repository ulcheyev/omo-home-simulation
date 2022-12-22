package cvut.omo.home_structure;

import cvut.omo.app_utils.Randomizer;
import cvut.omo.device.HomeDevice;
import cvut.omo.entity.ResponsibleType;
import cvut.omo.entity.person.FamilyRoleType;
import cvut.omo.entity.person.Person;
import cvut.omo.entity.pet.Pet;
import cvut.omo.event.Event;
import cvut.omo.item.Food;
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
    private List<Event> homeEvents;


    private Home(){
        floors = new ArrayList<>();
        persons = new ArrayList<>();
        pets = new ArrayList<>();
    }


    public void addFloor(Floor floor){floors.add(floor);}


    //TODO sdelat normalno
    public void addPerson(Person person) {
        int idxForFloor = Randomizer.getRandomInt(floors.size());
        int idxForRoom = Randomizer.getRandomInt(floors.get(idxForFloor).getRooms().size());
        floors.get(idxForFloor).getRooms().get(idxForRoom).addPerson(person);
        person.setRoom(floors.get(idxForFloor).getRooms().get(idxForRoom));
    }

    //TODO sdelat normalno
    public void addPet (Pet pet) {
       int idxForFloor = Randomizer.getRandomInt(floors.size());
       int idxForRoom = Randomizer.getRandomInt(floors.get(idxForFloor).getRooms().size());
       floors.get(idxForFloor).getRooms().get(idxForRoom).addPet(pet);
    }

    //TODO sdelat normalno
    public Person searchPerson (ResponsibleType role) {
        Person res = null;
        for(Floor floor: floors) {
            for (Room room : floor.getRooms()) {
                for (Person person : room.getPersons()) {
                    if (person.getFamilyRoleType() == role) {
                        res = person;
                    }
                }
            }
        }
        return res;
    }

    public Room searchRoom (RoomType type) {
        if(type == RoomType.STUB){
            return searchRoom(RoomType.values()[Randomizer.getRandomInt(RoomType.values().length)]);
        }else {
            Room res = null;
            for (Floor floor : floors) {
                for (Room room : floor.getRooms()) {
                    if (room.getRoomType() == type) {
                        res = room;
                    }
                }
            }
            return res;
        }
    }

    public List<HomeDevice> getHomeDevices(){
        List<HomeDevice> res = new ArrayList<>();
        floors
                .forEach(floor -> floor.getRooms()
                        .forEach(room -> res.addAll(room.getHomeDevices())));
        return res;
    }


    public void update(){
        System.out.println("Updating home");
    };
}
