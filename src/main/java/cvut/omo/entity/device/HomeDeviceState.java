package cvut.omo.entity.device;
import cvut.omo.entity.living.person.Person;

/**
 * Class represents state machine for {@link HomeDevice}
 */
public abstract class HomeDeviceState {

    private HomeAppliances homeAppliances;

    /**
     *
     * @param homeAppliances current home device
     */
    public HomeDeviceState(HomeAppliances homeAppliances) {
        this.homeAppliances = homeAppliances;
    }

    /**
     * Method switches on current device (depends on state)
     * @param homeAppliances current device
     */
    public abstract void switchOn(HomeAppliances homeAppliances);

    /**
     * Method switches off current device (depends on state)
     * @param homeAppliances current device
     */
    public abstract void switchOff(HomeAppliances homeAppliances);

    /**
     * Method allows repairing current device (depends on state)
     * @param person person, which is responsible for repair
     * @param homeAppliances current device
     */
    public abstract void repair(Person person, HomeAppliances homeAppliances);

    /**
     * Method allows breaking current device (depends on state)
     * @param homeAppliances current device
     */
    public abstract void _break(HomeAppliances homeAppliances);

    /**
     * Method allows using current device (depends on state)
     * @param person person, which is using this device
     * @param homeAppliances current device
     */
    public abstract void use(Person person, HomeAppliances homeAppliances);

    /**
     * Method allows pausing current device (depends on state)
     * @param homeAppliances current device
     */
    public abstract void pause(HomeAppliances homeAppliances);

}
