package cvut.omo.entity.nulls;

import cvut.omo.entity.ResponsibleType;

/**
 * Class warns against an NullPointerException
 */
public class NullResponsibleType implements ResponsibleType {
    @Override
    public boolean isNull() {
        return true;
    }
}
