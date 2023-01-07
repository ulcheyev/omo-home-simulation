package cvut.omo.entity.item.stuff;

import cvut.omo.app_utils.Utils;

import java.util.Arrays;

/**
 * The class represents food, which can be in {@link cvut.omo.entity.device.HomeDevice},
 * for example, in {@link cvut.omo.entity.device.Fridge}.
 */
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


