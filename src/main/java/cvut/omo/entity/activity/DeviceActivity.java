package cvut.omo.entity.activity;

import cvut.omo.app_utils.Constants;
import cvut.omo.device.HomeDevice;
import cvut.omo.entity.Responsible;
import cvut.omo.entity.person.Person;
import cvut.omo.event.Event;
import cvut.omo.home_structure.home_builder.Home;
import cvut.omo.item.Usable;


import static cvut.omo.app_utils.Constants.*;

public class DeviceActivity extends Activity {

    private Class<? extends Usable> toUse;
    private HomeDevice founded = null;

    public DeviceActivity(Responsible responsible, Event event, Class<? extends Usable> toUse, ActivityType activityType) throws InterruptedException {
        super(responsible, event, activityType);
        this.toUse = toUse;
    }

    @Override
    public void doWork(Responsible responsible) {

        if(toUse == null){
            for(HomeDevice homeDevice: room.getHomeDevices()) {
                if (homeDevice.getClass().equals(event.getResponsible().getClass())) {
                    founded = homeDevice;
                    break;
                }
            }
        }
        else {

            if(room.isNull()){
                founded = Home.INSTANCE.getHomeAppliancesByClass(toUse).get(0);
            }

            else {
                for (HomeDevice homeDevice : room.getHomeDevices()) {
                    if (homeDevice.getClass().equals(toUse)) {
                        founded = homeDevice;
                        break;
                    }
                }
            }
        }

        switch (containsFlag(activityType)){
            case ON -> founded.clickOn();
            case OFF -> founded.clickOff();
            case RUN -> founded.clickStart((Person) responsible);
            case REPAIR -> founded.repair((Person) responsible);
            case PAUSE -> founded.clickPause();
        }

    }

    @Override
    public String toString() {

        return super.toString() +
                " on " + founded.getClass().getSimpleName();
    }

    private String containsFlag(ActivityType activityType){
        String name = activityType.name();
        for(String flag: Constants.flags){
            if(name.contains(flag)){
                return flag;
            }
        }
        return name;
    }
}
