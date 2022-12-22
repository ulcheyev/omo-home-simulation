package cvut.omo.entity.person;

import cvut.omo.entity.EntityStatus;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Child extends Person{
    public Child(String name, FamilyRoleType familyRoleType) {
        super(name, familyRoleType);
    }
}
