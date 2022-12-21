package cvut.omo.app_utils;

import cvut.omo.device.BrokennessLevel;

import java.util.Random;

public class Randomizer {

    private static final Random PRNG = new Random();

    public static int getRandomInt(int to){
        return PRNG.nextInt(to);
    }

    public static BrokennessLevel getRandomBrokennessLevel()  {
        BrokennessLevel[] brokennessLevels = BrokennessLevel.values();
        return brokennessLevels[getRandomInt(brokennessLevels.length)];
    }
}
