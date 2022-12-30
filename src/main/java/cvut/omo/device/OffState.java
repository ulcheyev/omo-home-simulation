package cvut.omo.device;

import cvut.omo.entity.person.Person;

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
