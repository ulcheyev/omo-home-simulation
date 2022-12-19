package cvut.omo.device.states;

import cvut.omo.device.HomeAppliances;
import cvut.omo.entity.Activity;
import cvut.omo.entity.person.Person;
import cvut.omo.entity.pet.Pet;

public class OffState implements HomeDeviceState{

    private final HomeAppliances homeAppliances;

    public OffState(HomeAppliances homeAppliances) {
        this.homeAppliances = homeAppliances;
    }


    @Override
    public void switchOn(HomeAppliances homeAppliances) {
        homeAppliances.setHomeDeviceState(new IddleState(homeAppliances));
    }

    @Override
    public void switchOff(HomeAppliances homeAppliances) {
    }

    @Override
    public void repairDev(Person person, HomeAppliances homeAppliances) {

    }

    @Override
    public void breakDev(Activity activity, HomeAppliances homeAppliances) {
        homeAppliances.setHomeDeviceState(new BrokenState(homeAppliances));
    }

    @Override
    public void pauseDev(HomeAppliances homeAppliances) {

    }

    @Override
    public void interactDev(Person person, HomeAppliances homeAppliances) {
    }
}
