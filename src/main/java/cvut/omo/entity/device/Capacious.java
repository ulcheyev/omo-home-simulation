package cvut.omo.entity.device;

import cvut.omo.entity.item.stuff.Stuff;


/**
 * Interface for {@link HomeDevice}, which can have {@link Stuff}.
 */
public interface Capacious {
    /**
     * Return random item from this device.
     *
     * @return random item from this device
     */
    Stuff giveRandomItem();
}
