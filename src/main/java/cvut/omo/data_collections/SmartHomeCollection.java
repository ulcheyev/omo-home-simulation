package cvut.omo.data_collections;

import cvut.omo.device.HomeDevice;
import cvut.omo.data_collections.consumption.ConsumptionData;

import java.io.IOException;
import java.util.List;

public interface SmartHomeCollection {

    void put(HomeDevice homeDevice);
    void addConsumptionData(ConsumptionData consumptionData);

    HomeDevice getHomeDeviceAt(int idx);
    List<ConsumptionData> getDataAbout(HomeDevice homeDevice);
    int getSize();
    void generateReport() throws IOException;

    void update(HomeDevice homeDevice);

}
