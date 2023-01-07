package cvut.omo.entity.device;

import cvut.omo.entity.living.person.Person;
/**
 * Class represent off state of {@link HomeDevice}.
 */
public class OffState extends HomeDeviceState{

    public OffState(HomeAppliances homeAppliances) {
        super(homeAppliances);
        homeAppliances.disable();
    }


    @Override
    public void switchOn(HomeAppliances homeAppliances) {
        homeAppliances.setHomeDeviceState(new OnState(homeAppliances));
    }

    @Override
    public void switchOff(HomeAppliances homeAppliances) {}

    @Override
    public void repair(Person person, HomeAppliances homeAppliances) {}

    @Override
    public void _break(HomeAppliances homeAppliances) {
        homeAppliances.setHomeDeviceState(new BrokenState(homeAppliances));
    }

    @Override
    public void pause(HomeAppliances homeAppliances) {}

    @Override
    public void use(Person person, HomeAppliances homeAppliances) {
    }
}
