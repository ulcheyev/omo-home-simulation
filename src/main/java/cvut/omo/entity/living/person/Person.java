package cvut.omo.entity.living.person;

import cvut.omo.data_collections.visitor.SmartHomeReportVisitor;
import cvut.omo.entity.Responsible;
import cvut.omo.home_structure.HomeComponent;
import lombok.Getter;
import lombok.Setter;

/**
 * Class represent person entity in house.
 */
@Getter
@Setter
public class Person extends Responsible implements HomeComponent {

    private String name;

    /**
     * @param name           person name
     * @param familyRoleType person {@link FamilyRoleType}
     */
    public Person(String name, FamilyRoleType familyRoleType) {
        super(familyRoleType);
        this.name = name;
    }

    @Override
    public Object accept(SmartHomeReportVisitor visitor) {
        return visitor.visitPerson(this);
    }

    @Override
    public boolean isNull() {
        return false;
    }
}
