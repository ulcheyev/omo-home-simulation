package cvut.omo.device;

import cvut.omo.entity.person.FamilyRoleType;
import cvut.omo.entity.person.Person;
import cvut.omo.entity.EntityStatus;

/**
 *
 */
public class BrokenState extends HomeDeviceState {

    /**
     * @param homeAppliances
     */
    public BrokenState(HomeAppliances homeAppliances){
        super(homeAppliances);
        homeAppliances.breakDown();
    }

    /**
     * @param homeAppliances
     */
    @Override
    public void connect(HomeAppliances homeAppliances) {

    }

    /**
     * @param homeAppliances
     */
    @Override
    public void switchOn(HomeAppliances homeAppliances) {}

    /**
     * @param homeAppliances
     */
    @Override
    public void switchOff(HomeAppliances homeAppliances) {}

    /**
     * @param person
     * @param homeAppliances
     */
    @Override
    public void repair(Person person, HomeAppliances homeAppliances) {
        if(person.getResponsibleType() == FamilyRoleType.SON || person.getResponsibleType() == FamilyRoleType.FATHER){
            person.setEntityStatus(EntityStatus.BUSY);
        }
        homeAppliances.setHomeDeviceState(new OffState(homeAppliances));
    }

    /**
     * @param homeAppliances
     */
    @Override
    public void _break(HomeAppliances homeAppliances) {}

    /**
     * @param homeAppliances
     */
    @Override
    public void pause(HomeAppliances homeAppliances) {}

    /**
     * @param person
     * @param homeAppliances
     */
    @Override
    public void use(Person person, HomeAppliances homeAppliances) {}
}
