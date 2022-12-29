package cvut.omo.home_structure.home_builder;
import cvut.omo.entity.person.FamilyRoleType;
import cvut.omo.entity.pet.PetType;
import cvut.omo.home_structure.room_builder.RoomName;

/**
 *
 */
public final class SmartHomeBuilderDirector {

    public final static SmartHomeBuilderDirector INSTANCE = new SmartHomeBuilderDirector();
    private SmartHomeBuilderDirector(){}

    /**
     * @param homeBuilder
     */
    public static void createSmallHomeConfiguration(HomeBuilder homeBuilder){

        homeBuilder
                .reset()
                //Floors
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
                //Persons
                .addPerson("Lola", FamilyRoleType.MOTHER)
                .addPerson("Boba", FamilyRoleType.FATHER)
                .addPerson("Pipa", FamilyRoleType.SON)
                .addPerson("Popa", FamilyRoleType.DAUGHTER)
                .addPerson("Mr. Pepa", FamilyRoleType.GRANDFATHER)
                .addPerson("Mr. Lolo", FamilyRoleType.GRANDMOTHER)
                //Pets
                .addPet(PetType.CAT)
                .addPet(PetType.DOG)
                .addPet(PetType.MINI_PIG);
    }

    /**
     * @param homeBuilder
     */
    public static void createLargeHomeConfiguration(HomeBuilder homeBuilder){

        homeBuilder
                .reset()
                //Floors
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
                //Persons
                .addPerson("Lola", FamilyRoleType.MOTHER)
                .addPerson("Boba", FamilyRoleType.FATHER)
                .addPerson("Pipa", FamilyRoleType.SON)
                .addPerson("Popa", FamilyRoleType.DAUGHTER)
                .addPerson("Eliot", FamilyRoleType.SON)
                .addPerson("Male zlo", FamilyRoleType.DAUGHTER)
                .addPerson("Mr. Pepa", FamilyRoleType.GRANDFATHER)
                .addPerson("Mr. Lolo", FamilyRoleType.GRANDMOTHER)
                //Pets
                .addPet(PetType.CAT)
                .addPet(PetType.DOG)
                .addPet(PetType.CAT)
                .addPet(PetType.DOG)
                .addPet(PetType.MINI_PIG);
    }
}
