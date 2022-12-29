package cvut.omo.data_collections.consumption;

import cvut.omo.device.HomeDevice;

import java.io.IOException;
import java.util.List;

/**
 *
 */
public interface IConsumptionCollection {

    void put(HomeDevice homeDevice);
    HomeDevice getAt(int idx);
    List<ConsumptionData> getDataAbout(HomeDevice homeDevice);
    int getSize();
    void generateReport() throws IOException;
    void update(HomeDevice homeDevice);

}
