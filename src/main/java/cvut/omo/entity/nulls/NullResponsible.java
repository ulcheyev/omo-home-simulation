package cvut.omo.entity.nulls;

import cvut.omo.entity.Responsible;
import cvut.omo.entity.ResponsibleType;
import cvut.omo.entity.activity.Activity;
import cvut.omo.home_structure.nulls.NullRoom;
import cvut.omo.home_structure.room_builder.Room;

/**
 *
 */
public class NullResponsible extends Responsible {

    public final static NullResponsible INSTANCE = new NullResponsible();

    private NullResponsible(){
        super(new NullResponsibleType());
    }

    /**
     * @return
     */
    @Override
    public Room getRoom(){
        return new NullRoom();
    }

    /**
     * @return
     */
    @Override
    public boolean isNull() {
        return true;
    }


    /**
     * @param activity
     */
    @Override
    public void handle(Activity activity){}



}
