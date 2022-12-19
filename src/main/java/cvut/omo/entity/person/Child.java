package cvut.omo.entity.person;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Child extends Person{
    public Child(String name, FamilyRole familyRole, PersonStatus personStatus) {
        super(name, familyRole, personStatus);
    }
}
