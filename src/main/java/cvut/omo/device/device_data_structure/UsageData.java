package cvut.omo.device.device_data_structure;

import cvut.omo.device.HomeDevice;
import cvut.omo.entity.Activity;
import cvut.omo.entity.person.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UsageData{

    private Person person;
    private HomeDevice homeDevice;
    private Date date;
    private Activity activity;

}
