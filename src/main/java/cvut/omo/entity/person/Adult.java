package cvut.omo.entity.person;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Adult extends Person{
    public Adult(String name, FamilyRole familyRole, PersonStatus personStatus) {
        super(name, familyRole,personStatus);
    }
}
