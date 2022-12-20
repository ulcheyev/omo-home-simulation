package cvut.omo.device;

import cvut.omo.device.pdf_documentation.Documentation;
import cvut.omo.entity.person.Person;
import cvut.omo.home_structure.Room;

import java.util.Set;

public interface HomeDevice{

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
     void update();
}
