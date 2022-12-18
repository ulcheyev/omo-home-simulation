package cvut.omo.entity.person;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private String name;
    private FamilyRole familyRole;
//    private PersonStatus personStatus;

}
