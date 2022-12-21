package cvut.omo.home_structure;

import cvut.omo.device.HomeDevice;
import cvut.omo.entity.ResponsibleType;
import cvut.omo.entity.person.Person;
import cvut.omo.entity.pet.Pet;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Home {

    private static List<Floor> floors;
    private static List<Person> persons;
    private static List<Pet> pets;

    public Home(){
        floors = new ArrayList<>();
        persons = new ArrayList<>();
        pets = new ArrayList<>();
    }


    public void addFloor(Floor floor){floors.add(floor);}
    public void addPerson(Person person){persons.add(person);}
    public void addPet(Pet pet){pets.add(pet);}

    public List<HomeDevice> getHomeDevices(){
        List<HomeDevice> res = new ArrayList<>();
        floors
                .forEach(floor -> floor.getRooms()
                        .forEach(room -> res.addAll(room.getHomeDevices())));
        return res;
    }

    public static boolean callResponsible(Room room, List<ResponsibleType> responsibles) {
        for(ResponsibleType responsibleType: responsibles){
           callResponsible(room, responsibleType);
        }
        return false;
    }
    public static boolean callResponsible(Room room, ResponsibleType responsibleType) {
        for(Person person: persons){
            if(person.getFamilyRole() == responsibleType){
                person.getRoom().removePerson(person);
                person.setRoom(room);
                return true;
            }
        }
        return false;
    }


    public void update(){
        System.out.println("Updating home");
    };
}
