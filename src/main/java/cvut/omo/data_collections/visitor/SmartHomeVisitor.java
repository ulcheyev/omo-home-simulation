package cvut.omo.data_collections.visitor;


import cvut.omo.device.HomeDevice;
import cvut.omo.entity.person.Person;
import cvut.omo.entity.pet.Pet;
import cvut.omo.home_structure.Floor;
import cvut.omo.home_structure.Room;

public interface SmartHomeVisitor {

    String visitFloor(Floor floor);
    String visitRoom(Room room);
    String visitHomeDevice(HomeDevice device);
    String visitPerson(Person person);
    String visitPet(Pet pet);

}
