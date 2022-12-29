package cvut.omo.device;

import cvut.omo.app_utils.Utils;
import cvut.omo.entity.person.Person;
import cvut.omo.event.EventGenerator;
import cvut.omo.event.event_type.DeviceResponsibleEvent;

public class IddleState extends HomeDeviceState{

    private final static double PROBABILITY_OF_BROKE = 0.15;

    public IddleState(HomeAppliances homeAppliances){
        super(homeAppliances);
        homeAppliances.goIntoIddleMode();
    }


    @Override
    public void switchOn(HomeAppliances homeAppliances) {}

    @Override
    public void switchOff(HomeAppliances homeAppliances) {
        homeAppliances.setHomeDeviceState(new OffState(homeAppliances));
    }

    @Override
    public void repair(Person person, HomeAppliances homeAppliances) {
    }

    @Override
    public void _break(HomeAppliances homeAppliances) {
        homeAppliances.setHomeDeviceState(new BrokenState(homeAppliances));
    }

    @Override
    public void pause(HomeAppliances homeAppliances) {
    }

    @Override
    public void use(Person person, HomeAppliances homeAppliances) {

        if(Utils.getRandomDouble() < PROBABILITY_OF_BROKE){
            homeAppliances.setHomeDeviceState(new BrokenState(homeAppliances));
            String desc = "Person " + person.getName() + " broke this device...";
            EventGenerator.generateEventWithResponsible(homeAppliances, DeviceResponsibleEvent.DEVICE_BROKEN, desc);
        }else {
            homeAppliances.setHomeDeviceState(new RunState(person, homeAppliances));
        }
    }
}
