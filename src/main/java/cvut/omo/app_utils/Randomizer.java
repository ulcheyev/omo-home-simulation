package cvut.omo.app_utils;

import cvut.omo.device.documentation.BrokennessLevel;
import cvut.omo.entity.Responsible;
import cvut.omo.entity.ResponsibleType;
import cvut.omo.entity.activity.ActivityType;
import cvut.omo.entity.person.FamilyRoleType;
import cvut.omo.entity.person.Person;
import cvut.omo.event.Event;
import cvut.omo.event.EventType;
import cvut.omo.home_structure.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Randomizer {

    private static final Random PRNG = new Random();

    public static int getRandomInt(int to){
        return PRNG.nextInt(to);
    }

    public static int getRandomInt(){
        return PRNG.nextInt();
    }

    public static BrokennessLevel getRandomBrokennessLevel()  {
        BrokennessLevel[] brokennessLevels = BrokennessLevel.values();
        return brokennessLevels[getRandomInt(brokennessLevels.length)];
    }

    public static boolean yesOrNo(float probabilityOfYes) {
        return PRNG.nextInt()%100 < (probabilityOfYes * 100);
    }

    //TODO normalno sdelat
    public static FamilyRoleType generateRandomFamilyRole(Room room){
        List<FamilyRoleType> values = new ArrayList<>();
        FamilyRoleType familyRoleType = null;
        for(Person person: room.getPersons()){
            values.add(person.getFamilyRoleType());
        }
        if(values.size() != 0){
            familyRoleType = values.get(getRandomInt(values.size()));
        }
        return familyRoleType;
    }

    public static ResponsibleType getRandomResponsibleType(ActivityType activityType){
        List<ResponsibleType> responsibles = activityType.getResponsibles();
        int idx = getRandomInt(responsibles.size());
        return responsibles.get(idx);
    }



}
