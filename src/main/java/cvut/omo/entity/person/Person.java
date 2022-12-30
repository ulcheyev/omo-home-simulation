package cvut.omo.entity.person;

import cvut.omo.data_collections.visitor.SmartHomeVisitor;
import cvut.omo.entity.Responsible;
import cvut.omo.home_structure.HomeComponent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person extends Responsible implements HomeComponent {

    private String name;

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
