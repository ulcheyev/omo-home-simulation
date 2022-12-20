package cvut.omo.device;

import cvut.omo.entity.person.FamilyRole;
import cvut.omo.entity.person.Person;
import cvut.omo.entity.person.PersonStatus;

public class BrokenState extends HomeDeviceState {

    public BrokenState(HomeAppliances homeAppliances){
        super(homeAppliances);
        homeAppliances.breakDown();
    }

    @Override
    public void switchOn(HomeAppliances homeAppliances) {}

    @Override
    public void switchOff(HomeAppliances homeAppliances) {}

    @Override
    public void repair(Person person, HomeAppliances homeAppliances) {
        if(person.getFamilyRole() == FamilyRole.SON || person.getFamilyRole() == FamilyRole.FATHER){
            person.setPersonStatus(PersonStatus.BUSY);
        }
        homeAppliances.setHomeDeviceState(new OffState(homeAppliances));
    }

    @Override
    public void _break(HomeAppliances homeAppliances) {}

    @Override
    public void pause(HomeAppliances homeAppliances) {}

    @Override
    public void use(Person person, HomeAppliances homeAppliances) {}
}
