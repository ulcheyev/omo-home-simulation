package cvut.omo.entity.person;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Adult extends Person{
    public Adult(String name, FamilyRole familyRole) {
        super(name, familyRole);
    }
}
