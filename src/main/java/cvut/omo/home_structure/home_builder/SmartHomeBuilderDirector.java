package cvut.omo.home_structure.home_builder;
import cvut.omo.entity.living.person.FamilyRoleType;
import cvut.omo.entity.living.pet.PetType;
import cvut.omo.home_structure.room_builder.RoomName;

/**
 * Director for {@link SmartHomeBuilder}
 */
public final class SmartHomeBuilderDirector {

    /**
     * Instance of {@link SmartHomeBuilderDirector}
     */
    public final static SmartHomeBuilderDirector INSTANCE = new SmartHomeBuilderDirector();
    private SmartHomeBuilderDirector(){}

    /**
     * Creates small home configuration.
     * 2 floors, 6 persons, 10 rooms, 3 pets.
     * @param homeBuilder {@link HomeBuilder} which will build this config.
     */
    public static void createSmallHomeConfiguration(HomeBuilder homeBuilder){

        homeBuilder
                .reset()
                .addFloor(1)
                .addRoom(RoomName.BATHROOM)
                .addRoom(RoomName.HALL)
                .addRoom(RoomName.KITCHEN)
                .addRoom(RoomName.GARAGE)
                .addRoom(RoomName.VESTIBULE)
                .and()
                .addFloor(2)
                .addRoom(RoomName.CHILDRENS_ROOM)
                .addRoom(RoomName.BEDROOM)
                .addRoom(RoomName.HALL)
                .addRoom(RoomName.BATHROOM)
                .and()
                .addPerson("Lola", FamilyRoleType.MOTHER)
                .addPerson("Boba", FamilyRoleType.FATHER)
                .addPerson("Pipa", FamilyRoleType.SON)
                .addPerson("Popa", FamilyRoleType.DAUGHTER)
                .addPerson("Mr. Pepa", FamilyRoleType.GRANDFATHER)
                .addPerson("Mr. Lolo", FamilyRoleType.GRANDMOTHER)
                .addPet(PetType.CAT)
                .addPet(PetType.DOG)
                .addPet(PetType.MINI_PIG);
    }

    /**
     * Creates large home configuration.
     * 3 floors, 8 persons, 16 rooms, 5 pets.
     * @param homeBuilder {@link HomeBuilder} which will build this config.
     */
    public static void createLargeHomeConfiguration(HomeBuilder homeBuilder){

        homeBuilder
                .reset()
                .addFloor(1)
                .addRoom( RoomName.BATHROOM)
                .addRoom(RoomName.HALL)
                .addRoom(RoomName.VESTIBULE)
                .addRoom(RoomName.KITCHEN)
                .addRoom(RoomName.GARAGE)
                .and()
                .addFloor(2)
                .addRoom(RoomName.CHILDRENS_ROOM)
                .addRoom(RoomName.BEDROOM)
                .addRoom(RoomName.HALL)
                .addRoom(RoomName.BATHROOM)
                .addRoom(RoomName.BATHROOM)
                .and()
                .addFloor(3)
                .addRoom(RoomName.CHILDRENS_ROOM)
                .addRoom(RoomName.BEDROOM)
                .addRoom(RoomName.BEDROOM)
                .addRoom(RoomName.HALL)
                .addRoom(RoomName.BATHROOM)
                .addRoom(RoomName.BATHROOM)
                .and()
                .addPerson("Lola", FamilyRoleType.MOTHER)
                .addPerson("Boba", FamilyRoleType.FATHER)
                .addPerson("Pipa", FamilyRoleType.SON)
                .addPerson("Popa", FamilyRoleType.DAUGHTER)
                .addPerson("Eliot", FamilyRoleType.SON)
                .addPerson("Male zlo", FamilyRoleType.DAUGHTER)
                .addPerson("Mr. Pepa", FamilyRoleType.GRANDFATHER)
                .addPerson("Mr. Lolo", FamilyRoleType.GRANDMOTHER)
                .addPet(PetType.CAT)
                .addPet(PetType.DOG)
                .addPet(PetType.CAT)
                .addPet(PetType.DOG)
                .addPet(PetType.MINI_PIG);
    }
}
