package cvut.omo.device;

import cvut.omo.entity.person.Person;

public class DisconnectedState extends HomeDeviceState {


    public DisconnectedState(HomeAppliances homeAppliances) {
        super(homeAppliances);
    }

    @Override
    public void connect(HomeAppliances homeAppliances) {
        homeAppliances.setHomeDeviceState(new ConnectedState(homeAppliances));
    }

    @Override
    public void switchOn(HomeAppliances homeAppliances) {

    }

    @Override
    public void switchOff(HomeAppliances homeAppliances) {

    }

    @Override
    public void repair(Person person, HomeAppliances homeAppliances) {

    }

    @Override
    public void _break(HomeAppliances homeAppliances) {

    }

    @Override
    public void use(Person person, HomeAppliances homeAppliances) {

    }

    @Override
    public void pause(HomeAppliances homeAppliances) {

    }
}
