package cvut.omo.entity.living.pet;


import cvut.omo.entity.ResponsibleType;

/**
 * Class represents pet type.
 */
public enum PetType implements ResponsibleType {
    CAT,
    DOG,
    MINI_PIG;

    @Override
    public boolean isNull() {
        return false;
    }
}
