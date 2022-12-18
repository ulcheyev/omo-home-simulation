package cvut.omo.home_structure;

import cvut.omo.device.HomeDevice;
import cvut.omo.entity.person.Person;
import cvut.omo.entity.pet.Pet;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Home {

    private List<Floor> floors;
    private List<Person> persons;
    private List<Pet> pets;

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


    public void update(){

        System.out.println("Updating home");
    };
}
