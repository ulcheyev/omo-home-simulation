package cvut.omo.home_structure.home_builder;
import cvut.omo.entity.person.FamilyRole;
import cvut.omo.entity.person.PersonStatus;
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
                .addPerson("Lola", FamilyRole.MOTHER, PersonStatus.FREE)
                .addPerson("Boba", FamilyRole.FATHER, PersonStatus.FREE)
                .addPerson("Pipa",FamilyRole.SON, PersonStatus.FREE)
                .addPerson("Popa", FamilyRole.DAUGHTER, PersonStatus.FREE)
                .addPerson("Mr. Pepa", FamilyRole.GRANDFATHER, PersonStatus.FREE)
                .addPerson("Mr. Lolo", FamilyRole.GRANDMOTHER, PersonStatus.FREE)
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
                .addPerson("Lola", FamilyRole.MOTHER, PersonStatus.FREE)
                .addPerson("Boba", FamilyRole.FATHER, PersonStatus.FREE)
                .addPerson("Pipa",FamilyRole.SON, PersonStatus.FREE)
                .addPerson("Popa", FamilyRole.DAUGHTER, PersonStatus.FREE)
                .addPerson("Eliot",FamilyRole.SON, PersonStatus.FREE)
                .addPerson("Male zlo", FamilyRole.DAUGHTER, PersonStatus.FREE)
                .addPerson("Mr. Pepa", FamilyRole.GRANDFATHER, PersonStatus.FREE)
                .addPerson("Mr. Lolo", FamilyRole.GRANDMOTHER, PersonStatus.FREE)
                //Pets
                .addPet(PetType.CAT)
                .addPet(PetType.DOG)
                .addPet(PetType.CAT)
                .addPet(PetType.DOG)
                .addPet(PetType.MINI_PIG);
    }
}
