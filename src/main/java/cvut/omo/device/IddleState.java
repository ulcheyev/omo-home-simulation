package cvut.omo.device;

import cvut.omo.entity.person.Person;

public class IddleState extends HomeDeviceState{

    public IddleState(HomeAppliances homeAppliances){
        super(homeAppliances);
        homeAppliances.goIntoPauseMode();
    }

    @Override
    public void switchOn(HomeAppliances homeAppliances) {}

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
    }

    @Override
    public void use(Person person, HomeAppliances homeAppliances) {
        homeAppliances.setHomeDeviceState(new RunState(person, homeAppliances));
    }
}
