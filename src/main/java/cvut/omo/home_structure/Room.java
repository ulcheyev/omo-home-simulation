package cvut.omo.home_structure;

import cvut.omo.data_collections.visitor.SmartHomeVisitor;
import cvut.omo.device.HomeDevice;
import cvut.omo.entity.Responsible;
import cvut.omo.entity.ResponsibleType;
import cvut.omo.entity.person.FamilyRoleType;
import cvut.omo.entity.person.Person;
import cvut.omo.entity.pet.Pet;
import cvut.omo.event.Event;
import cvut.omo.item.Item;
import lombok.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Room implements HomeComponent{

    private List<HomeDevice> homeDevices = new ArrayList<>();
    private List<Window> windows = new ArrayList<>();
    private List<Person> persons = new ArrayList<>();
    private List<Pet> pets = new ArrayList<>();
    private List<Item> items= new ArrayList<>();
    private List<Event> events = new ArrayList<>();

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

    public boolean contains(ResponsibleType roleType){
        for(Person person: persons){
            if (person.getFamilyRoleType() == roleType){
                return true;
            }
        }
        return false;
    }


    public void update() {
        for(Event event: events){
            event.solve();
        }
    }

    @Override
    public String accept(SmartHomeVisitor visitor) {
        return visitor.visitRoom(this);
    }


//    public boolean roomOccupancy(Room room){
//        if (person.getLocation().floor == room.floor && person.getLocation().roomType == room.roomType){
//            return true;
//        }return false;
//    }
}
