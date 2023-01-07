package cvut.omo.data_collections.consumption;

import cvut.omo.entity.device.HomeDevice;

import java.io.IOException;
import java.util.List;

/**
 * Interface for consumption collection.
 */
public interface IConsumptionCollection {

    /**
     * Puts the device and its consumption types in the collection.
     * @param homeDevice device to put
     */
    void put(HomeDevice homeDevice);

    /**
     * Return device on specified index.
     * @param idx specified index
     * @return device on specified index
     */
    HomeDevice getAt(int idx);

    /**
     * Return all ConsumptionData of specified device.
     * @param homeDevice specified device
     * @return list of consumption data
     */
    List<ConsumptionData> getDataAbout(HomeDevice homeDevice);

    /**
     * Return size of collection.
     * @return size of collection (quantity of elements)
     */
    int getSize();

    /**
     * Generates consumption report.
     * @throws IOException when directory does not exist
     */
    void generateReport() throws IOException;

    /**
     * Update every consumption data of specified device.
     * @param homeDevice device to update
     */
    void update(HomeDevice homeDevice);

}
