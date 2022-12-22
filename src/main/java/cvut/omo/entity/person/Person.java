package cvut.omo.entity.person;

import cvut.omo.entity.*;
import cvut.omo.entity.activity.Activity;
import cvut.omo.entity.EntityStatus;
import cvut.omo.entity.activity.ActivityType;
import cvut.omo.event.EventType;
import cvut.omo.home_structure.Room;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person extends Responsible{

    private String name;
    private FamilyRoleType familyRoleType;
    private List<Activity> activityList;

    public Person(String name, FamilyRoleType familyRoleType) {
        this.name = name;
        this.familyRoleType = familyRoleType;
    }






}
