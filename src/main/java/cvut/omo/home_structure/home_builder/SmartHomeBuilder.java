package cvut.omo.home_structure.home_builder;

import cvut.omo.entity.living.person.FamilyRoleType;
import cvut.omo.entity.living.person.Person;
import cvut.omo.entity.living.pet.Pet;
import cvut.omo.entity.living.pet.PetType;
import cvut.omo.exceptions.FloorException;
import cvut.omo.home_structure.Floor;
import cvut.omo.home_structure.room_builder.*;

/**
 * Class represent builder for {@link Home}.
 */
public final class SmartHomeBuilder implements HomeBuilder{

    /**
     * Instance of {@link SmartHomeBuilder}.
     */
    public static final SmartHomeBuilder INSTANCE = new SmartHomeBuilder();
    private final SmartHomeRoomBuilderDirector smartHomeRoomBuilderDirector = SmartHomeRoomBuilderDirector.INSTANCE;
    private Home home = Home.INSTANCE;
    private SmartHomeBuilder(){}

    @Override
    public HomeBuilder reset() {
        home = Home.INSTANCE;
        return this;
    }

    @Override
    public HomeBuilder addPerson(String name, FamilyRoleType familyRoleType) {
       home.addEntity(new Person(name, familyRoleType));
       return this;
    }

    @Override
    public HomeBuilder addPet(PetType petType) {
        home.addEntity(new Pet(petType));
        return this;
    }

    @Override
    public FloorStub addFloor(int floorNumber) {
        if(home.getFloors()
                .stream()
                .anyMatch(floor -> floor.getNumberOfFloor().equals(floorNumber))
        )
        {
            throw new FloorException("Floor with number " + floorNumber + " already exists");
        }else if(!home.getFloors()
                .stream()
                .anyMatch(floor -> floor.getNumberOfFloor().equals(floorNumber-1)) && floorNumber != 1){
            throw new FloorException("First you need to add a floor with a number " + (floorNumber-1));
        }
        Floor floor = new Floor(floorNumber);
        home.addFloor(floor);
        return new FloorStub(floor);
    }

    /**
     * Returns result.
     * @return instance of {@link Home}
     */
    public Home getResult(){
        return this.home;
    }

    /**
     * Class is needed, when user add floor.
     * In this case, he can only add a room to the floor.
     */
    protected class FloorStub {

        private Floor floor;
        private RoomBuilder defaultRoomBuilder = SmartHomeRoomBuilder.INSTANCE;

        public FloorStub setRoomBuilder(RoomBuilder roomBuilder){
            this.defaultRoomBuilder = roomBuilder;
            return this;
        }

        public FloorStub(Floor floor) {
            this.floor = floor;
        }

        public FloorStub addRoom(RoomName roomName) {
            SmartHomeBuilder.this.smartHomeRoomBuilderDirector.createRoom(defaultRoomBuilder, roomName, floor);
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
