package cvut.omo.home_structure.home_builder;
import cvut.omo.entity.person.FamilyRoleType;
import cvut.omo.entity.EntityStatus;
import cvut.omo.entity.pet.PetType;
import cvut.omo.home_structure.RoomType;

public final class SmartHomeBuilderDirector {

    public final static SmartHomeBuilderDirector INSTANCE = new SmartHomeBuilderDirector();
    private SmartHomeBuilderDirector(){}

    public static void createSmallHomeConfiguration(HomeBuilder homeBuilder){

        homeBuilder
                .reset()
                //Floors
                .addFloor(1)
                .addRoom( RoomType.BATHROOM)
                .addRoom(RoomType.HALL)
                .addRoom(RoomType.KITCHEN)
                .addRoom(RoomType.GARAGE)
                .and()
                .addFloor(2)
                .addRoom(RoomType.CHILDRENS_ROOM)
                .addRoom(RoomType.BEDROOM)
                .addRoom(RoomType.HALL)
                .addRoom(RoomType.BATHROOM)
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

    public static void createLargeHomeConfiguration(HomeBuilder homeBuilder){

        homeBuilder
                .reset()
                //Floors
                .addFloor(1)
                .addRoom( RoomType.BATHROOM)
                .addRoom(RoomType.HALL)
                .addRoom(RoomType.KITCHEN)
                .addRoom(RoomType.GARAGE)
                .and()
                .addFloor(2)
                .addRoom(RoomType.CHILDRENS_ROOM)
                .addRoom(RoomType.BEDROOM)
                .addRoom(RoomType.HALL)
                .addRoom(RoomType.BATHROOM)
                .addRoom(RoomType.BATHROOM)
                .and()
                .addFloor(3)
                .addRoom(RoomType.CHILDRENS_ROOM)
                .addRoom(RoomType.BEDROOM)
                .addRoom(RoomType.BEDROOM)
                .addRoom(RoomType.HALL)
                .addRoom(RoomType.BATHROOM)
                .addRoom(RoomType.BATHROOM)
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
