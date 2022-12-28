package cvut.omo.entity.person;

import cvut.omo.data_collections.visitor.SmartHomeVisitor;
import cvut.omo.entity.*;
import cvut.omo.entity.activity.Activity;
import cvut.omo.event.Event;
import cvut.omo.home_structure.HomeComponent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Person extends Responsible implements HomeComponent {

    private String name;
    private List<Activity> activityList;

    public Person(String name, FamilyRoleType familyRoleType) {
        super(familyRoleType);
        this.name = name;
    }

    @Override
    public String accept(SmartHomeVisitor visitor) {
        return visitor.visitPerson(this);
    }

    @Override
    public boolean isNull() {
        return false;
    }
}
