package cvut.omo.device.consumption_structure;
import lombok.*;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ConsumptionData {

    private SourceType sourceType;

    private double amountOfConsumed;
    private double deviceConsumptionPerHour;

    private Date dateFrom;
    private Date dateTo;

    public ConsumptionData(SourceType sourceType, double consumptionPerHour) {
        this.sourceType = sourceType;
        amountOfConsumed = 0.0;
        dateFrom = new Date();
        dateTo = dateFrom;
        deviceConsumptionPerHour = consumptionPerHour;
    }

    public void update(){
        dateTo = DateUtils.addHours(dateTo, 1);
        if(!dateTo.equals(dateFrom)){
            //TODO
        }
        amountOfConsumed += deviceConsumptionPerHour;
    }


}
