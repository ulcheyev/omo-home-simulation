package cvut.omo.home_structure;

import cvut.omo.device.HomeDevice;
import cvut.omo.entity.person.Person;
import cvut.omo.entity.pet.Pet;
import cvut.omo.item.Item;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Room {

    private List<HomeDevice> homeDevices = new ArrayList<>();
    private List<Window> windows = new ArrayList<>();
    private List<Person> persons = new ArrayList<>();
    private List<Pet> pets = new ArrayList<>();
    private List<Item> items= new ArrayList<>();

    private Floor floor;
    private RoomType roomType;
    private Person person;

    public Room(Floor floor, RoomType roomType){
        this.floor = floor;
        this.roomType = roomType;
    }

    public void addHomeDevice(HomeDevice homeDevice){
        homeDevices.add(homeDevice);
        homeDevice.setRoom(this);
    }

    public void addWindow(){
        windows.add(new Window());
    }
    public void removeWindow(Window window){
        windows.remove(window);
    }
    public void removeHomeDevice(HomeDevice homeDevice){
        homeDevices.remove(homeDevice);
    }
    public void addItem(Item item){items.add(item);}
    public void removeItem(Item item){items.remove(item);}
    public void addPerson(Person person){persons.add(person);}
    public void removePerson(Person person){persons.remove(person);}
    public void addPet(Pet pet){pets.add(pet);}
    public void removePet(Pet pet){pets.remove(pet);}
    public boolean isEmpty(){return persons.isEmpty();}

//    public boolean roomOccupancy(Room room){
//        if (person.getLocation().floor == room.floor && person.getLocation().roomType == room.roomType){
//            return true;
//        }return false;
//    }
}
