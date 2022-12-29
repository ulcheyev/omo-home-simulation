package cvut.omo.device;

import cvut.omo.entity.person.Person;

/**
 *
 */
public class DisconnectedState extends HomeDeviceState {


    public DisconnectedState(HomeAppliances homeAppliances) {
        super(homeAppliances);
    }

    /**
     * @param homeAppliances
     */
    @Override
    public void connect(HomeAppliances homeAppliances) {
        homeAppliances.setHomeDeviceState(new ConnectedState(homeAppliances));
    }

    /**
     * @param homeAppliances
     */
    @Override
    public void switchOn(HomeAppliances homeAppliances) {

    }

    /**
     * @param homeAppliances
     */
    @Override
    public void switchOff(HomeAppliances homeAppliances) {

    }

    /**
     * @param person
     * @param homeAppliances
     */
    @Override
    public void repair(Person person, HomeAppliances homeAppliances) {

    }

    /**
     * @param homeAppliances
     */
    @Override
    public void _break(HomeAppliances homeAppliances) {

    }

    /**
     * @param person
     * @param homeAppliances
     */
    @Override
    public void use(Person person, HomeAppliances homeAppliances) {

    }

    /**
     * @param homeAppliances
     */
    @Override
    public void pause(HomeAppliances homeAppliances) {

    }
}
