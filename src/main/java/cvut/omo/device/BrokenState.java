package cvut.omo.device;

import cvut.omo.entity.person.FamilyRoleType;
import cvut.omo.entity.person.Person;
import cvut.omo.entity.EntityStatus;

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
        if(person.getFamilyRoleType() == FamilyRoleType.SON || person.getFamilyRoleType() == FamilyRoleType.FATHER){
            person.setEntityStatus(EntityStatus.BUSY);
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
