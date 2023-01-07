package cvut.omo.data_collections.consumption;

import cvut.omo.entity.device.HomeDevice;
import cvut.omo.entity.device.SourceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class store how many specified source type the device has consumed.
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
     * Constructor for class.
     * @param homeDevice home device
     * @param sourceType source type
     */
    public ConsumptionData(HomeDevice homeDevice, SourceType sourceType) {
        this.homeDevice = homeDevice;
        this.sourceType = sourceType;
    }

    /**
     * Updates consumed data dependent on current device consumption.
     */
    public void update(){
        this.amountOfConsumed += homeDevice.getCurrentConsumption(sourceType);
    }



}
