package cvut.omo.entity.nulls;

import cvut.omo.entity.ResponsibleType;

/**
 *
 */
public class NullResponsibleType implements ResponsibleType {
    /**
     * @return
     */
    @Override
    public boolean isNull() {
        return true;
    }
}
