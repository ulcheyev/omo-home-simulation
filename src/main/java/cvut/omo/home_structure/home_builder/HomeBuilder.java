package cvut.omo.home_structure.home_builder;

import cvut.omo.entity.person.FamilyRoleType;
import cvut.omo.entity.pet.PetType;

public interface HomeBuilder {

    HomeBuilder reset();
    HomeBuilder addPerson(String name, FamilyRoleType familyRoleType);
    HomeBuilder addPet(PetType petType);
    SmartHomeBuilder.FloorStub addFloor(int floorNumber);

}
