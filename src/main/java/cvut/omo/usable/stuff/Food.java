package cvut.omo.usable.stuff;

import cvut.omo.app_utils.Utils;
import lombok.Getter;

import java.util.Arrays;

public class Food extends Stuff{

    private FoodType foodType = Utils.getRandomObjFromList(Arrays.asList(FoodType.class.getEnumConstants()));

    @Override
    public StuffType getStuffType() {
        return foodType;
    }

    private enum FoodType implements StuffType{
        MILK,
        SAUSAGE,
        CHICKEN,
        MEAT
    }

}


