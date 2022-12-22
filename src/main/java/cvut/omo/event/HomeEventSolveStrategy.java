package cvut.omo.event;

import cvut.omo.app_utils.Randomizer;
import cvut.omo.entity.ResponsibleType;
import cvut.omo.entity.activity.ActivityFactory;
import cvut.omo.entity.activity.ActivityType;
import cvut.omo.entity.activity.Relocate;
import cvut.omo.entity.person.FamilyRoleType;
import cvut.omo.entity.person.Person;
import cvut.omo.home_structure.Home;
import cvut.omo.home_structure.Room;

public class HomeEventSolveStrategy implements Strategy{

    @Override
    public void solve(Event event) {

        EventType eventType = event.getEventType();
        Room room = event.getRoom();
        Person person = null;

        if (room.isEmpty()) {
            for (ActivityType type : eventType.getChainToSolve()) {
                ResponsibleType randomResponsibleType = Randomizer.getRandomResponsibleType(type);
                person = Home.INSTANCE.searchPerson(randomResponsibleType);
                //TODO handle EVENT
                person.handle(new Relocate(room, ActivityType.RELOCATE));
                person.handle(ActivityFactory.create(room, type));

            }
        }else {
            FamilyRoleType role = Randomizer.generateRandomFamilyRole(room);
            person = Home.INSTANCE.searchPerson(role);

            for (ActivityType type : eventType.getChainToSolve()) {

                boolean founded = false;
                for(ResponsibleType resp: type.getResponsibles()) {
                    if(resp == role) {
                        if (!person.getRoom().getRoomType().equals(type.getRoomType())) {
                            person.handle(new Relocate(Home.INSTANCE.searchRoom(type.getRoomType()), ActivityType.RELOCATE));
                        }
                        person.handle(ActivityFactory.create(room, type));
                        founded = true;
                    }
                }
                if(!founded) {
                        ResponsibleType randomResponsibleType = Randomizer.getRandomResponsibleType(type);
                        person = Home.INSTANCE.searchPerson(randomResponsibleType);
                        person.handle(new Relocate(room, ActivityType.RELOCATE));
                        person.handle(ActivityFactory.create(room, type));
                    }
                }

        }
    }
}
