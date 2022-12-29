package cvut.omo.home_structure.home_builder;

import cvut.omo.entity.person.FamilyRoleType;
import cvut.omo.entity.person.Person;
import cvut.omo.entity.pet.Pet;
import cvut.omo.entity.pet.PetType;
import cvut.omo.exceptions.FloorException;
import cvut.omo.home_structure.Floor;
import cvut.omo.home_structure.room_builder.Room;
import cvut.omo.home_structure.room_builder.RoomName;
import cvut.omo.home_structure.room_builder.RoomBuilder;
import cvut.omo.home_structure.room_builder.SmartHomeRoomBuilder;
import cvut.omo.home_structure.room_builder.SmartHomeRoomBuilderDirector;

/**
 *
 */
public final class SmartHomeBuilder implements HomeBuilder{

    public static final SmartHomeBuilder INSTANCE = new SmartHomeBuilder();
    private final SmartHomeRoomBuilderDirector smartHomeRoomBuilderDirector = SmartHomeRoomBuilderDirector.INSTANCE;
    private Home home = Home.INSTANCE;
    private SmartHomeBuilder(){}

    /**
     * @return
     */
    @Override
    public HomeBuilder reset() {
        home = Home.INSTANCE;
        return this;
    }

    /**
     * @param name
     * @param familyRoleType
     * @return
     */
    //TODO proverka, esli roli uzhe est. Napr 2 mamy 2 papy
    @Override
    public HomeBuilder addPerson(String name, FamilyRoleType familyRoleType) {
       home.addEntity(new Person(name, familyRoleType));
       return this;
    }

    /**
     * @param petType
     * @return
     */
    @Override
    public HomeBuilder addPet(PetType petType) {
        home.addEntity(new Pet(petType));
        return this;
    }

    /**
     * @param floorNumber
     * @return
     */
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

    /**
     * @return
     */
    public Home getResult(){
        return this.home;
    }

    protected class FloorStub {

        private Floor floor;
        private RoomBuilder defaultRoomBuilder = SmartHomeRoomBuilder.INSTANCE;

        public FloorStub setRoomBuilder(RoomBuilder roomBuilder){
            this.defaultRoomBuilder = roomBuilder;
            return this;
        }

        /**
         * @param floor
         */
        public FloorStub(Floor floor) {
            this.floor = floor;
        }

        /**
         * @param roomName
         * @return
         */
        public FloorStub addRoom(RoomName roomName) {
            SmartHomeBuilder.this.smartHomeRoomBuilderDirector.createRoom(defaultRoomBuilder, roomName, floor);
            return this;
        }

        /**
         * @param room
         * @return
         */
        public FloorStub addRoom(Room room) {
            floor.addRoom(room);
            return this;
        }

        /**
         * @return
         */
        public SmartHomeBuilder and(){
            return SmartHomeBuilder.this;
        }
    }
}
