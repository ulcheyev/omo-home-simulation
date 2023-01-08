package cvut.omo.entity.living.person;


import cvut.omo.entity.ResponsibleType;

/**
 * Class represents family role.
 */
public enum FamilyRoleType implements ResponsibleType {
    FATHER, MOTHER, SON, DAUGHTER, GRANDMOTHER, GRANDFATHER;

    @Override
    public boolean isNull() {
        return false;
    }
}
