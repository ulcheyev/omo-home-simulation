package cvut.omo.home_structure.room_builder;

import cvut.omo.data_collections.visitor.SmartHomeReportVisitor;
import cvut.omo.entity.device.HomeDevice;
import cvut.omo.entity.Responsible;
import cvut.omo.entity.ResponsibleType;
import cvut.omo.entity.nulls.NullResponsible;
import cvut.omo.entity.living.person.Person;
import cvut.omo.entity.living.pet.Pet;
import cvut.omo.home_structure.Floor;
import cvut.omo.home_structure.HomeComponent;
import cvut.omo.home_structure.Window;
import cvut.omo.entity.item.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Objects;

/**
 * Class represents room in home.
 */
@Setter
@Getter
@NoArgsConstructor
public class Room implements HomeComponent {

    private List<HomeDevice> homeDevices = new ArrayList<>();
    private List<Window> windows = new ArrayList<>();
    private List<Item> items= new ArrayList<>();
    private List<Responsible> responsibles = new ArrayList<>();
    private Floor floor;
    private RoomName roomName;

    /**
     *
     * @param floor room floor
     * @param roomName {@link RoomName}
     */
    public Room(Floor floor, RoomName roomName){
        this.floor = floor;
        this.roomName = roomName;
    }

    /**
     * Adds responsible to {@link #responsibles}
     * @param responsible responsible to add
     */
    public void addResponsible(Responsible responsible){
        responsible.setRoom(this);
        responsibles.add(responsible);
    }

    /**
     * Adds home device to {@link #homeDevices}
     * @param homeDevice home device to add
     */
    public void addHomeDevice(HomeDevice homeDevice){
        homeDevices.add(homeDevice);
        homeDevice.setRoom(this);
    }

    /**
     * Adds window to {@link #windows}
     */
    public void addWindow(){
        windows.add(new Window());
    }

    /**
     * Adds item to {@link #items}
     * @param item item to add
     */
    public void addItem(Item item){items.add(item);}

    /**
     * Returns responsible with specified {@link ResponsibleType}
     * @param type specified {@link ResponsibleType}
     * @return responsible with specified {@link ResponsibleType} or {@link NullResponsible} is does not exist
     *
     */
    public Responsible getResponsible(ResponsibleType type){
        for(Responsible resp: responsibles){
            if(resp.getResponsibleType().equals(type)){
                return resp;
            }
        }
        return new NullResponsible();
    }

    /**
     *
     * @return true, if room is empty
     */
    public boolean isEmpty(){return responsibles.isEmpty();}

    /**
     * Checks, if room contains specified {@link ResponsibleType}
     * @param roleType  specified {@link ResponsibleType}
     * @return true, if room contains specified {@link ResponsibleType}
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
     * Return {@link Person} in current room
     * @return list of persons
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
     * Return {@link Pet} in current room
     * @return list of pets
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
     * Remove specified responsible from current room {@link #responsibles}
     * @param responsible
     */
    public void removeResponsible(Responsible responsible){
        responsible.setRoom(null);
        responsibles.remove(responsible);
    }


    /**
     * Updates current room.
     * Updates every device in {@link #homeDevices}.
     * Updates every responsible in {@link #responsibles}.
     * @throws InterruptedException
     */
    public void update() throws InterruptedException {
    Thread thread = new Thread(
            () -> {
                try {
                    for (Responsible responsible : responsibles) {
                        responsible.update();
                    }
                    for (HomeDevice homeDevice : homeDevices) {
                        try {
                            homeDevice.update();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }catch (ConcurrentModificationException ignored){}
            }
    );
    thread.start();
    thread.join();
    }

    @Override
    public Object accept(SmartHomeReportVisitor visitor) {
        return visitor.visitRoom(this);
    }

    @Override
    public boolean isNull() {
        return false;
    }


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
