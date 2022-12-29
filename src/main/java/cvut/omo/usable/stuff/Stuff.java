package cvut.omo.usable.stuff;

import lombok.Getter;

public abstract class Stuff {

    @Getter
    private StuffType stuffType;

    public boolean isNull(){
        return false;
    }

    public interface StuffType{}
}
