package cvut.omo.home_structure.room_builder;

import cvut.omo.data_collections.visitor.SmartHomeVisitor;
import cvut.omo.device.HomeDevice;
import cvut.omo.entity.nulls.NullResponsible;
import cvut.omo.entity.Responsible;
import cvut.omo.entity.ResponsibleType;
import cvut.omo.entity.person.Person;
import cvut.omo.entity.pet.Pet;
import cvut.omo.event.Event;
import cvut.omo.home_structure.Floor;
import cvut.omo.home_structure.HomeComponent;
import cvut.omo.home_structure.Window;
import cvut.omo.home_structure.home_builder.Home;
import cvut.omo.item.Item;
import lombok.*;

import java.util.*;

/**
 *
 */
@Setter
@Getter
@NoArgsConstructor
public class Room implements HomeComponent {

    private List<HomeDevice> homeDevices = new ArrayList<>();
    private List<Window> windows = new ArrayList<>();
    private List<Item> items= new ArrayList<>();
    private List<Responsible> responsibles = new ArrayList<>();
    @Setter(AccessLevel.PRIVATE) private boolean isOpened;
    private Floor floor;
    private RoomName roomName;

    public Room(Floor floor, RoomName roomName){
        isOpened = true;
        this.floor = floor;
        this.roomName = roomName;
    }

    /**
     * @param responsible
     */
    public void addResponsible(Responsible responsible){
        responsible.setRoom(this);
        responsibles.add(responsible);
    }

    /**
     * @param homeDevice
     */
    public void addHomeDevice(HomeDevice homeDevice){
        homeDevices.add(homeDevice);
        homeDevice.setRoom(this);
    }


    /**
     *
     */
    public void addWindow(){
        windows.add(new Window());
    }

    /**
     * @param item
     */
    public void addItem(Item item){items.add(item);}

    /**
     * @param type
     * @return
     */
    public Responsible getResponsible(ResponsibleType type){
        for(Responsible resp: responsibles){
            if(resp.getResponsibleType().equals(type)){
                return resp;
            }
        }
        return NullResponsible.INSTANCE;
    }

    /**
     * @return
     */
    public boolean isEmpty(){return responsibles.isEmpty();}

    /**
     * @param roleType
     * @return
     */
    public boolean contains(ResponsibleType roleType){
        for(Responsible resp: responsibles){
            if (resp.getResponsibleType().equals(roleType)){
                return true;
            }
        }
        return false;
    }

    /**
     * @return
     */
   public List<Person> getPersons(){
        List<Person> persons = new ArrayList<>();
        for(Responsible responsible:responsibles){
            if(responsible instanceof  Person){
                persons.add((Person) responsible);
            }
        }
        return persons;
   }

    /**
     * @return
     */
    public List<Pet> getPets(){
        List<Pet> persons = new ArrayList<>();
        for(Responsible responsible:responsibles){
            if(responsible instanceof  Pet){
                persons.add((Pet) responsible);
            }
        }
        return persons;
    }

    /**
     * @param window
     */
    public void removeWindow(Window window){windows.remove(window);}

    /**
     * @param homeDevice
     */
    public void removeHomeDevice(HomeDevice homeDevice){homeDevices.remove(homeDevice);}

    /**
     * @param item
     */
    public void removeItem(Item item){items.remove(item);}

    /**
     * @param responsible
     */
    public void removeResponsible(Responsible responsible){
        responsible.setRoom(null);
        responsibles.remove(responsible);
    }

    /**
     * @param person
     */
    public void removePerson(Person person){responsibles.remove(person);}

    /**
     * @param pet
     */
    public void removePet(Pet pet){responsibles.remove(pet);}


    /**
     *
     */
    public void close(){
        setOpened(false);
    }

    /**
     *
     */
    public void open(){
        setOpened(true);
    }


    /**
     * @throws InterruptedException
     */
    //ToDO
    public void update() throws InterruptedException {
    Thread thread = new Thread(
            () -> {
                for(Responsible responsible: responsibles){
                    responsible.update();
                }
                for(HomeDevice homeDevice: homeDevices){
                    try {
                        homeDevice.update();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
    );
    thread.start();
    thread.join();

    }

    /**
     * @param visitor
     * @return
     */
    @Override
    public String accept(SmartHomeVisitor visitor) {
        return visitor.visitRoom(this);
    }

    /**
     * @return
     */
    @Override
    public boolean isNull() {
        return false;
    }


    /**
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(homeDevices, room.homeDevices)
                && Objects.equals(windows, room.windows)
                && Objects.equals(items, room.items)
                && Objects.equals(responsibles, room.responsibles)
                && Objects.equals(floor, room.floor)
                && roomName == room.roomName;
    }

}
