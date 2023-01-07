package cvut.omo.entity.item.stuff;

import lombok.Getter;

/**
 * The class represents things that can be in {@link cvut.omo.entity.device.HomeDevice}.
 */
public abstract class Stuff {

    @Getter
    private StuffType stuffType;

    public boolean isNull(){
        return false;
    }

    /**
     * Interface for stuff type.
     */
    public interface StuffType{}
}
