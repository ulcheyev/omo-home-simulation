package cvut.omo.entity.pet;

import cvut.omo.data_collections.visitor.SmartHomeVisitor;
import cvut.omo.device.HomeDevice;
import cvut.omo.entity.Responsible;
import cvut.omo.entity.activity.Activity;
import cvut.omo.entity.EntityStatus;
import cvut.omo.event.EventType;
import cvut.omo.home_structure.HomeComponent;
import cvut.omo.home_structure.Room;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Pet extends Responsible implements HomeComponent {

    private PetType petType;
    private List<Activity> activityList;

    public Pet(PetType petType) {
        this.petType = petType;
    }

    public void needSleep(){}

    public void needGoForAWalk(){}

    public void brokeDevice(HomeDevice homeDevice) {}

    @Override
    public String accept(SmartHomeVisitor visitor) {
        return visitor.visitPet(this);
    }
}
