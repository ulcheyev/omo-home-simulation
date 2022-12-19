package cvut.omo.device.states;

import cvut.omo.device.HomeAppliances;
import cvut.omo.entity.Activity;
import cvut.omo.entity.person.FamilyRole;
import cvut.omo.entity.person.Person;
import cvut.omo.entity.person.PersonStatus;
import cvut.omo.entity.pet.Pet;

public class BrokenState implements HomeDeviceState{

    private final HomeAppliances homeAppliances;

    public BrokenState(HomeAppliances homeAppliances){
        this.homeAppliances = homeAppliances;
    }

    @Override
    public void switchOn(HomeAppliances homeAppliances) {

    }

    @Override
    public void switchOff(HomeAppliances homeAppliances) {

    }

    @Override
    public void repairDev(Person person, HomeAppliances homeAppliances) {
        if(person.getFamilyRole() == FamilyRole.SON || person.getFamilyRole() == FamilyRole.FATHER){
            person.setPersonStatus(PersonStatus.BUSY);
        }
        homeAppliances.setHomeDeviceState(new OffState(homeAppliances));
    }

    @Override
    public void breakDev(Activity activity, HomeAppliances homeAppliances) {
    }

    @Override
    public void pauseDev(HomeAppliances homeAppliances) {
    }

    @Override
    public void interactDev(Person person, HomeAppliances homeAppliances) {
    }
}
