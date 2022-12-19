package cvut.omo.entity.pet;

import cvut.omo.device.HomeDevice;
import cvut.omo.entity.Activity;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Pet extends Activity {
    private PetType petType;


    public void needSleep(){

    }

    public void needGoForAWalk(){

    }

    public void brokeDevice(HomeDevice homeDevice){

    }


}
