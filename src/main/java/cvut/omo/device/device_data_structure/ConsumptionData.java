package cvut.omo.device.device_data_structure;
import cvut.omo.app_utils.Constants;
import cvut.omo.device.HomeDevice;
import lombok.*;
import org.checkerframework.checker.units.qual.C;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ConsumptionData {

    private HomeDevice homeDevice;
    private SourceType sourceType;
    private double amountOfConsumed = 0.0;
    private double deviceCurrentConsumption = Constants.DEVICE_OFF_STATE;

    private Date date;

    public ConsumptionData(HomeDevice homeDevice, SourceType sourceType) {
        this.homeDevice = homeDevice;
        this.sourceType = sourceType;
        date = new Date();
    }

    public void update(){
        this.amountOfConsumed += deviceCurrentConsumption;
    }

    public ConsumptionData(SourceType sourceType) {
        this.sourceType = sourceType;
    }



}
