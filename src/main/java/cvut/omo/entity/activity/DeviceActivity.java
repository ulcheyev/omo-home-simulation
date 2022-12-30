package cvut.omo.entity.activity;

import cvut.omo.app_utils.Constants;
import cvut.omo.app_utils.Utils;
import cvut.omo.device.Capacious;
import cvut.omo.device.HomeDevice;
import cvut.omo.entity.Responsible;
import cvut.omo.entity.person.Person;
import cvut.omo.event.Event;
import cvut.omo.exceptions.OMOException;
import cvut.omo.home_structure.home_builder.Home;
import cvut.omo.usable.Usable;
import cvut.omo.usable.stuff.NullStuff;
import cvut.omo.usable.stuff.Stuff;

import java.util.ArrayList;
import java.util.List;

import static cvut.omo.app_utils.Constants.*;

public class DeviceActivity extends Activity {

    private Class<? extends Usable> toUse;
    private HomeDevice founded = null;
    private List<Stuff> stuffList = new ArrayList<>();

    public DeviceActivity(Responsible responsible, Event event, Class<? extends Usable> toUse, ActivityType activityType) {
        super(responsible, event, activityType);
        this.toUse = toUse;
    }

    @Override
    public boolean doWork(Responsible responsible) {

        if(toUse == null){
            founded = (HomeDevice) event.getResponsible();
        }

        else {
            if(room.isNull()){
                founded = Utils.getRandomObjFromList(Home.INSTANCE.getHomeAppliancesByClass(toUse));
            }
            else {
                founded = Utils.getRandomObjFromList(room.getHomeDevices());
                while(!founded.getClass().equals(toUse)){
                    founded = Utils.getRandomObjFromList(room.getHomeDevices());
                }
            }
        }

        switch (containsFlag(activityType)){
            case ON -> founded.switchOn();
            case OFF -> founded.switchOff();
            case RUN -> {
                founded.use((Person) responsible);
                if(founded instanceof Capacious){
                    try {
                        Capacious founded = ((Capacious) this.founded);
                        stuffList.add(founded.giveRandomItem());
                    }catch (OMOException e){
                        stuffList.add(new NullStuff());
                    }
                }
            }
            case REPAIR -> founded.repair(event, (Person) responsible);
            case PAUSE -> founded.pause();
            case BREAK -> founded.breakDevice();
        }
        return true;
    }

    @Override
    public String toString() {

        String device = responsible instanceof HomeDevice ? ""
                : " on " + founded.getClass().getSimpleName();

        StringBuilder s = new StringBuilder(super.toString() +
                device);

        if(!stuffList.isEmpty()){
            for(Stuff stuff: stuffList) {
                String padded = String.format("%-6s", " ");
                s.append("\n").append(padded);
                s.append("⮚ ");

                if (stuff.isNull()) {
                   s.append("Empty:( ");
                    break;
                } else {
                    s.append("Returned ")
                            .append(stuff.getStuffType());
                }
            }
        }
        return s.toString();
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

    private HomeDevice findHomeDevice(List<HomeDevice> homeDevices){
        HomeDevice homeDevice = Utils.getRandomObjFromList(homeDevices);
        while(!homeDevice.getClass().equals(event.getResponsible().getClass())){
            homeDevice = Utils.getRandomObjFromList(homeDevices);
        }
        return homeDevice;
    }

}
