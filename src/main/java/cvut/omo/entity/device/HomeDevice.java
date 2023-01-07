package cvut.omo.entity.device;

import cvut.omo.entity.device.documentation.Documentation;
import cvut.omo.entity.living.person.Person;
import cvut.omo.event.Event;
import cvut.omo.home_structure.HomeComponent;
import cvut.omo.home_structure.room_builder.Room;
import cvut.omo.entity.Usable;

import java.util.Set;

/**
 * Interface for {@link HomeAppliances}.
 * @see HomeAppliances
 */
public interface HomeDevice extends HomeComponent, Usable {

     void switchOn();
     void switchOff();
     void pause();
     void use(Person person);
     void repair(Event event, Person person);
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
