package cvut.omo.app_utils;

import cvut.omo.device.documentation.BrokennessLevel;
import cvut.omo.entity.nulls.NullResponsible;
import cvut.omo.entity.Responsible;
import cvut.omo.entity.ResponsibleType;
import cvut.omo.entity.activity.ActivityType;
import cvut.omo.home_structure.room_builder.Room;

import java.util.List;
import java.util.Random;

public class Utils {


    private static final Random PRNG = new Random();

    /**
     *
     * @param to
     * @return
     */
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


    public static <T> T getRandomObjFromList(List<T> objs) {
        return objs.get(getRandomInt(objs.size()));
    }






}
