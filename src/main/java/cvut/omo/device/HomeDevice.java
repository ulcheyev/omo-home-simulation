package cvut.omo.device;

import cvut.omo.home_structure.Room;

import java.util.Set;

public interface HomeDevice{
     void turnOn();
     void turnOff();
     void pause();
     void start();
     void repair();

     Set<SourceType> getSourceTypes();
     double getCurrentConsumption(SourceType sourceType);
     Room getRoom();
     double getWorkingHours();
     Documentation getDocumentation();
     boolean isNotConsume();

     void setRoom(Room room);
     void setCurrentConsumption(SourceType sourceType, double currentConsumption);
     void update();
}
