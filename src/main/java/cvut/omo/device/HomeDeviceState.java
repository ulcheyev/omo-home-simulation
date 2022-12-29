package cvut.omo.device;
import cvut.omo.device.HomeAppliances;
import cvut.omo.entity.person.Person;

/**
 *
 */
public abstract class HomeDeviceState {

    public HomeAppliances homeAppliances;

    public HomeDeviceState(HomeAppliances homeAppliances) {
        this.homeAppliances = homeAppliances;
    }

    /**
     * @param homeAppliances
     */
    public abstract void connect(HomeAppliances homeAppliances);

    /**
     * @param homeAppliances
     */
    public abstract void switchOn(HomeAppliances homeAppliances);

    /**
     * @param homeAppliances
     */
    public abstract void switchOff(HomeAppliances homeAppliances);

    /**
     * @param person
     * @param homeAppliances
     */
    public abstract void repair(Person person, HomeAppliances homeAppliances);

    /**
     * @param homeAppliances
     */
    public abstract void _break(HomeAppliances homeAppliances);

    /**
     * @param person
     * @param homeAppliances
     */
    public abstract void use(Person person, HomeAppliances homeAppliances);

    /**
     * @param homeAppliances
     */
    public abstract void pause(HomeAppliances homeAppliances);

}
