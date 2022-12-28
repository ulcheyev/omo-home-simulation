package cvut.omo.device;

import cvut.omo.device.documentation.Documentation;
import cvut.omo.entity.person.Person;
import cvut.omo.event.Event;
import cvut.omo.home_structure.HomeComponent;
import cvut.omo.home_structure.room_builder.Room;

import java.util.List;
import java.util.Set;

public interface HomeDevice extends HomeComponent {

     void clickOn();
     void clickOff();
     void clickPause();
     void clickStart(Person person);
     void repair(Person person);
     void breakDevice();

     Set<SourceType> getSourceTypes();
     double getCurrentConsumption(SourceType sourceType);
     Room getRoom();
     double getWorkingHours();
     Documentation getDocumentation();
     boolean isNotConsume();


     void setRoom(Room room);
     void setCurrentConsumption(SourceType sourceType, double currentConsumption);
     void update() throws InterruptedException;
}
