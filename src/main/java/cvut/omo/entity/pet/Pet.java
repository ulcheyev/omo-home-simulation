package cvut.omo.entity.pet;

import cvut.omo.data_collections.visitor.SmartHomeVisitor;
import cvut.omo.entity.Responsible;
import cvut.omo.entity.activity.Activity;
import cvut.omo.home_structure.HomeComponent;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Pet extends Responsible implements HomeComponent {

    private List<Activity> activityList;

    public Pet(PetType petType) {
        super(petType);
    }

    @Override
    public String accept(SmartHomeVisitor visitor) {
        return visitor.visitPet(this);
    }

    @Override
    public boolean isNull() {
        return false;
    }
}
