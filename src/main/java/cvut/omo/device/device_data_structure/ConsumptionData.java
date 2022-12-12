package cvut.omo.device.device_data_structure;
import cvut.omo.device.HomeDevice;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ConsumptionData {

    private HomeDevice homeDevice;
    private SourceType sourceType;
    private double amountOfConsumed = 123123123123.0;

    private Date dateFrom;
    private Date dateTo;

    public ConsumptionData(HomeDevice homeDevice, SourceType sourceType) {
        this.homeDevice = homeDevice;
        this.sourceType = sourceType;
        dateFrom = new Date();
        dateTo = dateFrom;
    }

    public ConsumptionData(SourceType sourceType) {
        this.sourceType = sourceType;
    }



}
