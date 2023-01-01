package cvut.omo.entity.device;

import cvut.omo.app_utils.Constants;
import cvut.omo.data_collections.consumption.ConsumptionCollection;
import cvut.omo.event.EventGenerator;
import cvut.omo.event.event_type.HomeEvent;
import cvut.omo.exceptions.OMOException;
import cvut.omo.home_structure.home_builder.Home;
import cvut.omo.home_structure.room_builder.Room;
import cvut.omo.home_structure.room_builder.RoomName;

/**
 * Class represents circuit breaker device.
 */
public class CircuitBreaker extends HomeAppliances{

    public CircuitBreaker(double lifeTime) {
        super(lifeTime);
    }

    @Override
    protected void identify() {
        this.currentConsumption.put(SourceType.NOT_CONSUME, Constants.DEVICE_DOES_NOT_CONSUME);
        ConsumptionCollection.getInstance().put(this);
    }

    @Override
    public void disable(){
        EventGenerator.generateEvent(HomeEvent.INFO, "Circuit breaker off, the current is coming in");
        for(HomeDevice hd: Home.INSTANCE.getHomeDevices()){
            if(!hd.equals(this)) {
                hd.setCurrentConsumption(SourceType.ENERGY, Constants.DEVICE_OFF_STATE_ELECTRICITY);
            }
        }

    }

    @Override
    public void enable(){
        EventGenerator.generateEvent(HomeEvent.INFO, "Circuit breaker on, the current is not coming in");
        for(HomeDevice hd: Home.INSTANCE.getHomeDevices()){
            if(!hd.equals(this)) {
                hd.setCurrentConsumption(SourceType.ENERGY, 0);
            }
        }
    }

    @Override
    public void goIntoIddleMode(){}

    @Override
    public void run(){}

    @Override
    public void setRoom(Room room) {
        if (!room.getRoomName().equals(RoomName.VESTIBULE)) {
            throw new OMOException("Circuit Breaker must locate in Vestibule Room");
        }
        super.setRoom(room);
    }
}
