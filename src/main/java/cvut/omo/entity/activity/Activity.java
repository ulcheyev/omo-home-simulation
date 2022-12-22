package cvut.omo.entity.activity;

import cvut.omo.entity.Responsible;
import cvut.omo.home_structure.Room;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public abstract class Activity {

    protected ActivityType activityType;
    protected Room room;
    protected boolean isExucuted;

    public Activity(ActivityType activityType) {
        this.activityType = activityType;
    }

    public abstract void execute(Responsible responsible);

}
