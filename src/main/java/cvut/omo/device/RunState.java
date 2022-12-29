package cvut.omo.device;

import cvut.omo.entity.person.Person;

/**
 *
 */
public class RunState extends HomeDeviceState{

    public RunState(Person person, HomeAppliances homeAppliances){
        super(homeAppliances);
        homeAppliances.run();
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
    public void switchOn(HomeAppliances homeAppliances) {

    }

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
        homeAppliances.setHomeDeviceState(new IddleState(homeAppliances));
    }

    /**
     * @param person
     * @param homeAppliances
     */
    @Override
    public void use(Person person, HomeAppliances homeAppliances) {

    }
}
