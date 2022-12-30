package cvut.omo;

import cvut.omo.app_utils.Constants;
import cvut.omo.home_structure.home_builder.Home;

import static cvut.omo.event.EventGenerator.*;

public class Simulation {

    static Home home = Home.INSTANCE;

    public static void simulate(int days){
        int hours = days * Constants.DAY_IN_HOUR;

        for(int i = 0; i < hours; i++){
            if(hours % 2 == 0){
                generateRandomEntityEvent();
            }
            else if(hours % 15 == 0){
                generateRandomDeviceEvent();
            }
            else if(hours % 25 == 0){
                generateRandomHomeEvent();
            }
            hours--;
            home.update();
        }
    }


}
