package cvut.omo.entity.device;

import cvut.omo.entity.living.person.Person;

/**
 * Class represent broken state of {@link HomeDevice}.
 */
public class BrokenState extends HomeDeviceState {

    public BrokenState(HomeAppliances homeAppliances) {
        super(homeAppliances);
        homeAppliances.breakDown();
    }

    @Override
    public void switchOn(HomeAppliances homeAppliances) {
    }


    @Override
    public void switchOff(HomeAppliances homeAppliances) {
    }

    @Override
    public void repair(Person person, HomeAppliances homeAppliances) {
        person.lock();
        homeAppliances.setHomeDeviceState(new OffState(homeAppliances));

        person.unlock();
    }

    @Override
    public void _break(HomeAppliances homeAppliances) {
    }

    @Override
    public void pause(HomeAppliances homeAppliances) {
    }

    @Override
    public void use(Person person, HomeAppliances homeAppliances) {
    }
}
