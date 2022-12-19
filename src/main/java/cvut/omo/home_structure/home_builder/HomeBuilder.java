package cvut.omo.home_structure.home_builder;

import cvut.omo.entity.person.FamilyRole;
import cvut.omo.entity.person.PersonStatus;
import cvut.omo.entity.pet.PetType;
import cvut.omo.home_structure.Room;
import cvut.omo.home_structure.RoomType;
import cvut.omo.home_structure.room_builder.RoomBuilder;

public interface HomeBuilder {

    HomeBuilder reset();
    HomeBuilder addPerson(String name, FamilyRole familyRole, PersonStatus personStatus);
    HomeBuilder addPet(PetType petType);
    SmartHomeBuilder.FloorStub addFloor(int floorNumber);

}
