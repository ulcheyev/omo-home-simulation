package cvut.omo.entity.person;

import cvut.omo.entity.Activity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person extends Activity {

    private String name;
    private FamilyRole familyRole;
    private PersonStatus personStatus;

}
