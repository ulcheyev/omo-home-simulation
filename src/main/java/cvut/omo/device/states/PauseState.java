package cvut.omo.device.states;

import cvut.omo.device.HomeAppliances;
import cvut.omo.entity.Activity;
import cvut.omo.entity.person.Person;
import cvut.omo.entity.pet.Pet;

public class PauseState implements HomeDeviceState{

    private final HomeAppliances homeAppliances;

    public PauseState(HomeAppliances homeAppliances){
        this.homeAppliances = homeAppliances;
    }

    @Override
    public void switchOn(HomeAppliances homeAppliances) {

    }

    @Override
    public void switchOff(HomeAppliances homeAppliances) {
        homeAppliances.setHomeDeviceState(new OffState(homeAppliances));
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
        homeAppliances.setHomeDeviceState(new RunState(homeAppliances));
    }
}
