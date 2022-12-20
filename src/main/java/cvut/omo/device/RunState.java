package cvut.omo.device;

import cvut.omo.entity.person.Person;

public class RunState extends HomeDeviceState{

    public RunState(Person person, HomeAppliances homeAppliances){
        super(homeAppliances);
        homeAppliances.run();
    }

    @Override
    public void switchOn(HomeAppliances homeAppliances) {

    }

    @Override
    public void switchOff(HomeAppliances homeAppliances) {
        homeAppliances.setHomeDeviceState(new OffState(homeAppliances));
    }

    @Override
    public void repair(Person person, HomeAppliances homeAppliances) {

    }

    @Override
    public void _break(HomeAppliances homeAppliances) {
        homeAppliances.setHomeDeviceState(new BrokenState(homeAppliances));
    }

    @Override
    public void pause(HomeAppliances homeAppliances) {
        homeAppliances.setHomeDeviceState(new IddleState(homeAppliances));
    }

    @Override
    public void use(Person person, HomeAppliances homeAppliances) {

    }
}
