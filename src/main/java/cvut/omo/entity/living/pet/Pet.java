package cvut.omo.entity.living.pet;

import cvut.omo.data_collections.visitor.SmartHomeReportVisitor;
import cvut.omo.entity.Responsible;
import cvut.omo.entity.activity.Activity;
import cvut.omo.home_structure.HomeComponent;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Class represent pet entity in house.
 */
@Getter
@Setter
public class Pet extends Responsible implements HomeComponent {

    private List<Activity> activityList;

    /**
     * @param petType {@link PetType}
     */
    public Pet(PetType petType) {
        super(petType);
    }

    @Override
    public Object accept(SmartHomeReportVisitor visitor) {
        return visitor.visitPet(this);
    }

    @Override
    public boolean isNull() {
        return false;
    }
}
