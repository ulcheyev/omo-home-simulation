package cvut.omo.home_structure.home_builder;

import cvut.omo.entity.living.person.FamilyRoleType;
import cvut.omo.entity.living.pet.PetType;

/**
 * Interface for home builder.
 */
public interface HomeBuilder {

    /**
     * Resets current home, which is building.
     *
     * @return current HomeBuilder
     */
    HomeBuilder reset();

    /**
     * Adds person to home.
     *
     * @return current HomeBuilder
     */
    HomeBuilder addPerson(String name, FamilyRoleType familyRoleType);

    /**
     * Adds pet to home.
     *
     * @return current HomeBuilder
     */
    HomeBuilder addPet(PetType petType);

    /**
     * Adds floor to home.
     *
     * @return {@link cvut.omo.home_structure.home_builder.SmartHomeBuilder.FloorStub}
     */
    SmartHomeBuilder.FloorStub addFloor(int floorNumber);

}
