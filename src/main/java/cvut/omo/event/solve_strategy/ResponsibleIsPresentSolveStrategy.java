package cvut.omo.event.solve_strategy;

import cvut.omo.entity.Activity;
import cvut.omo.entity.ActivityType;
import cvut.omo.entity.Responsible;
import cvut.omo.entity.person.Person;
import cvut.omo.entity.person.PersonStatus;
import cvut.omo.entity.pet.Pet;
import cvut.omo.event.Event;
import cvut.omo.home_structure.Room;
import org.apache.commons.lang3.ArrayUtils;

import java.util.List;

public class ResponsibleIsPresentSolveStrategy implements SolveStrategy{

    private static int ACTIVITIES_COUNT = 0;

    @Override
    public void solve(Event event) {
        Room room = event.getRoom();
        List<? e Responsible> responsibles = room.getPets();

        responsibles.addAll(room.getPets());

        List<Person> persons = room.getPersons();
        List<Pet> pets = room.getPets();
        List<ActivityType> chainToSolve = event.getEventType().getChainToSolve();

        for(Person person: persons){
            for(ActivityType activityType: chainToSolve){
                if(activityType.getResponsibles().contains(person.getFamilyRole())){
                    while (!person.isFree()) {
                        System.out.println("Cekame, kdyz person bude free ");
                    }
                    person.setPersonStatus(PersonStatus.BUSY);
                    person.getActivityList().add(new Activity());
                    person.setPersonStatus(PersonStatus.FREE);
                    ACTIVITIES_COUNT++;
                }
            }
        }

        if(ACTIVITIES_COUNT == chainToSolve.size()){
            event.setSolved(true);
        }

    }
}
