package cvut.omo.data_collections.consumption;
import cvut.omo.device.HomeDevice;
import cvut.omo.device.SourceType;
import lombok.*;

/**
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsumptionData {

    private HomeDevice homeDevice;
    private SourceType sourceType;
    private double amountOfConsumed = 0.0;

    /**
     * @param homeDevice
     * @param sourceType
     */
    public ConsumptionData(HomeDevice homeDevice, SourceType sourceType) {
        this.homeDevice = homeDevice;
        this.sourceType = sourceType;
    }

    /**
     *
     */
    public void update(){
        this.amountOfConsumed += homeDevice.getCurrentConsumption(sourceType);
    }



}
