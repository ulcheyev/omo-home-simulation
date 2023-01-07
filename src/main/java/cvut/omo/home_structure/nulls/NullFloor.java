package cvut.omo.home_structure.nulls;

import cvut.omo.home_structure.Floor;

/**
 * Class warns against an NullPointerException.
 */
public class NullFloor extends Floor {

    @Override
    public boolean isNull() {
        return true;
    }

}
