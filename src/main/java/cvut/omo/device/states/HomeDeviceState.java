package cvut.omo.device.states;
import cvut.omo.device.HomeAppliances;
import cvut.omo.entity.Activity;
import cvut.omo.entity.person.Person;
import cvut.omo.entity.pet.Pet;

public interface HomeDeviceState {

    void switchOn(HomeAppliances homeAppliances);
    void switchOff(HomeAppliances homeAppliances);
    void repairDev(Person person, HomeAppliances homeAppliances);
    void breakDev(Activity activity,HomeAppliances homeAppliances);
    void interactDev(Person person, HomeAppliances homeAppliances);
    void pauseDev(HomeAppliances homeAppliances);

}
