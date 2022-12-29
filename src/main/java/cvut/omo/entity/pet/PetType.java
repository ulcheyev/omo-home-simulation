package cvut.omo.entity.pet;


import cvut.omo.entity.ResponsibleType;

/**
 *
 */
public enum PetType implements ResponsibleType {
    CAT,
    DOG,
    MINI_PIG;

    /**
     * @return
     */
    @Override
    public boolean isNull() {
        return false;
    }
}
