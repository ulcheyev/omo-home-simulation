package cvut.omo.entity.person;

import cvut.omo.entity.Activity;
import cvut.omo.entity.Responsible;
import cvut.omo.home_structure.Room;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Responsible {

    private String name;
    private FamilyRole familyRole;
    private PersonStatus personStatus;
    private List<Activity> activityList;
    private Room room;

    public boolean isFree(){
        return personStatus == PersonStatus.FREE;
    }

}
