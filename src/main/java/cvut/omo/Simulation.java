package cvut.omo;

import cvut.omo.app_utils.Constants;
import cvut.omo.home_structure.home_builder.Home;

import static cvut.omo.event.EventGenerator.*;

/**
 * Class performs the functions of simulating life in the {@link Home}.
 */
public class Simulation {

    private static Home home = Home.INSTANCE;

    /**
     * Simulate life in the {@link Home}.
     * Generates random events.
     *
     * @param days number of days for simulation
     */
    public static void simulate(int days) {
        int hours = days * Constants.DAY_IN_HOUR;

        for (int i = 0; i < hours; i++) {
            if (hours % 2 == 0) {
                generateRandomEntityEvent();
            } else if (hours % 15 == 0) {
                generateRandomDeviceEvent();
            } else if (hours % 25 == 0) {
                generateRandomHomeEvent();
            }
            hours--;
            home.update();
        }
    }


}
