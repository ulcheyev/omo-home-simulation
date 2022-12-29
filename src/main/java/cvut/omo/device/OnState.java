package cvut.omo.device;

import cvut.omo.entity.person.Person;

/**
 *
 */
public class OnState extends HomeDeviceState{

    public OnState(HomeAppliances homeAppliances){
        super(homeAppliances);
        homeAppliances.enable();
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
    public void switchOff(HomeAppliances homeAppliances) {
        homeAppliances.setHomeDeviceState(new OffState(homeAppliances));
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
        homeAppliances.setHomeDeviceState(new BrokenState(homeAppliances));
    }

    /**
     * @param homeAppliances
     */
    @Override
    public void pause(HomeAppliances homeAppliances) {
    }

    /**
     * @param person
     * @param homeAppliances
     */
    @Override
    public void use(Person person, HomeAppliances homeAppliances) {
        homeAppliances.setHomeDeviceState(new RunState(person, homeAppliances));
    }
}
