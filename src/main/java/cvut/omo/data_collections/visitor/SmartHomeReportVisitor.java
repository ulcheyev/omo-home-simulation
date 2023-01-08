package cvut.omo.data_collections.visitor;


import cvut.omo.entity.device.HomeDevice;
import cvut.omo.entity.living.person.Person;
import cvut.omo.entity.living.pet.Pet;
import cvut.omo.home_structure.Floor;
import cvut.omo.home_structure.room_builder.Room;

/**
 * The Visitor interface defines the contract for an object that is used to visit another object.
 */
public interface SmartHomeReportVisitor {

    /**
     * Visit the given floor.
     *
     * @param floor floor to visit
     * @return info about floor in string representation
     */
    String visitFloor(Floor floor);

    /**
     * Visit the given room.
     *
     * @param room room to visit
     * @return info about room in string representation
     */
    String visitRoom(Room room);

    /**
     * Visit the given device.
     *
     * @param device device to visit
     * @return info about home device in string representation
     */
    String visitHomeDevice(HomeDevice device);

    /**
     * Visit the given person.
     *
     * @param person to visit
     * @return info about person in string representation
     */
    String visitPerson(Person person);

    /**
     * Visit the given pet.
     *
     * @param pet to visit
     * @return info about pet in string representation
     */
    String visitPet(Pet pet);

}
