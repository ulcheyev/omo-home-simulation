package cvut.omo.entity.person;

import cvut.omo.entity.EntityStatus;
import lombok.NoArgsConstructor;

public class Adult extends Person{
    public Adult(String name, FamilyRoleType familyRoleType) {
        super(name, familyRoleType);
    }
}
