package cvut.omo.home_structure.home_builder;

import cvut.omo.device.factory.HomeDeviceFactory;
import cvut.omo.device.factory.SmartHomeDeviceFactory;
import cvut.omo.entity.person.FamilyRole;
import cvut.omo.entity.person.Person;
import cvut.omo.entity.person.PersonStatus;
import cvut.omo.entity.pet.Pet;
import cvut.omo.entity.pet.PetType;
import cvut.omo.exceptions.FloorException;
import cvut.omo.home_structure.Floor;
import cvut.omo.home_structure.Home;
import cvut.omo.home_structure.Room;
import cvut.omo.home_structure.RoomType;
import cvut.omo.home_structure.room_builder.RoomBuilder;
import cvut.omo.home_structure.room_builder.SmartHomeRoomBuilder;
import cvut.omo.home_structure.room_builder.SmartHomeRoomBuilderDirector;

public final class SmartHomeBuilder implements HomeBuilder{

    public static final SmartHomeBuilder INSTANCE = new SmartHomeBuilder();
    private final SmartHomeRoomBuilderDirector smartHomeRoomBuilderDirector = SmartHomeRoomBuilderDirector.INSTANCE;
    private Home home = new Home();
    private SmartHomeBuilder(){}

    @Override
    public HomeBuilder reset() {
        home = new Home();
        return this;
    }

    //TODO proverka, esli roli uzhe est. Napr 2 mamy 2 papy
    @Override
    public HomeBuilder addPerson(String name, FamilyRole familyRole, PersonStatus personStatus) {
       home.addPerson(new Person(name, familyRole,personStatus));
       return this;
    }

    @Override
    public HomeBuilder addPet(PetType petType) {
        home.addPet(new Pet(petType));
        return this;
    }

    //TODO kontrola esli dobavit 4 no net 3 -> exception
    @Override
    public FloorStub addFloor(int floorNumber) {
        if(home.getFloors()
                .stream()
                .anyMatch(floor -> floor.getNumberOfFloor().equals(floorNumber))
                )
        {
            throw new FloorException("Floor with number " + floorNumber + " already exists");
        }
        Floor floor = new Floor(floorNumber);
        home.addFloor(floor);
        return new FloorStub(floor);
    }

    public Home getResult(){
        return this.home;
    }

    public class FloorStub {

        private Floor floor;
        private RoomBuilder defaultRoomBuilder = SmartHomeRoomBuilder.INSTANCE;

        public FloorStub setRoomBuilder(RoomBuilder roomBuilder){
            this.defaultRoomBuilder = roomBuilder;
            return this;
        }

        public FloorStub(Floor floor) {
            this.floor = floor;
        }

        public FloorStub addRoom(RoomType roomType) {
            SmartHomeBuilder.this.smartHomeRoomBuilderDirector.createRoom(defaultRoomBuilder, roomType, floor);
            return this;
        }

        public FloorStub addRoom(Room room) {
            floor.addRoom(room);
            return this;
        }

        public SmartHomeBuilder and(){
            return SmartHomeBuilder.this;
        }
    }
}
