package cvut.omo.device;

import cvut.omo.entity.person.Person;

public class ConnectedState extends HomeDeviceState{

    public ConnectedState(HomeAppliances homeAppliances) {
        super(homeAppliances);
        homeAppliances.connectDevice();
    }

    @Override
    public void connect(HomeAppliances homeAppliances) {

    }

    @Override
    public void switchOn(HomeAppliances homeAppliances) {
        homeAppliances.setHomeDeviceState(new OnState(homeAppliances));
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
    public void use(Person person, HomeAppliances homeAppliances) {

    }

    @Override
    public void pause(HomeAppliances homeAppliances) {

    }
}
