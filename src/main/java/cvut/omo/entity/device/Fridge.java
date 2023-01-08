package cvut.omo.entity.device;

import cvut.omo.app_utils.Constants;
import cvut.omo.app_utils.Utils;
import cvut.omo.data_collections.consumption.ConsumptionCollection;
import cvut.omo.exceptions.OMOException;
import cvut.omo.entity.item.stuff.Food;
import cvut.omo.entity.item.stuff.Stuff;

import java.util.ArrayList;
import java.util.List;


/**
 * Class represents fridge device.
 */
public class Fridge extends HomeAppliances implements Capacious {

    private static final int FRIDGE_FULLNESS = 10;
    private List<Food> foods = new ArrayList<>();

    public Fridge(double lifeTime) {
        super(lifeTime);
        fullFridge();
    }

    private void fullFridge() {
        for (int i = 0; i < FRIDGE_FULLNESS; i++) {
            foods.add(new Food());
        }
    }

    @Override
    public void enable() {
        this.setCurrentConsumption(SourceType.ENERGY, Constants.FRIDGE_IDDLE_STATE_ELECTRICITY_CONSUMPTION);
    }

    @Override
    public void goIntoIddleMode() {
        this.setCurrentConsumption(SourceType.ENERGY, Constants.FRIDGE_IDDLE_STATE_ELECTRICITY_CONSUMPTION);
    }

    @Override
    public void run() {
        this.setCurrentConsumption(SourceType.ENERGY, Constants.FRIDGE_RUN_ELECTRICITY_CONSUMPTION);
    }

    @Override
    protected void identify() {
        this.currentConsumption.put(SourceType.ENERGY, Constants.DEVICE_OFF_STATE_ELECTRICITY);
        ConsumptionCollection.getInstance().put(this);
    }

    @Override
    public Stuff giveRandomItem() {
        if (foods.isEmpty()) {
            throw new OMOException("Fridge is empty");
        }
        Food randomObjFromList = Utils.getRandomObjFromList(foods);
        foods.remove(randomObjFromList);
        return randomObjFromList;
    }

}
