package cvut.omo.entity.pet;

import cvut.omo.device.HomeDevice;
import cvut.omo.entity.Activity;
import cvut.omo.entity.Responsible;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Pet implements Responsible {
    private PetType petType;


    public void needSleep(){

    }

    public void needGoForAWalk(){

    }

    public void brokeDevice(HomeDevice homeDevice){

    }


}
