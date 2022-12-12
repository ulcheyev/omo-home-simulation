package cvut.omo.device.device_data_structure;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import cvut.omo.device.HomeDevice;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

//TODO singleton
public class ConsumptionAndUsageDataStructure {


    public ConsumptionAndUsageDataStructure(List<HomeDevice> homeDevices) {
        for(HomeDevice homeDevice: homeDevices){
            putHomeDevice(homeDevice);
        }
    }

    private Table<HomeDevice, List<ConsumptionData>, List<UsageData>> data = HashBasedTable.create();

    public void putHomeDevice(HomeDevice homeDevice){
        data.put(homeDevice, homeDevice.getConsumptionData(), homeDevice.getUsageDataList());
    }

    public void addConsumptionData(ConsumptionData consumptionData){
        Map<List<ConsumptionData>, List<UsageData>> row = data.row(consumptionData.getHomeDevice());

        for(List<ConsumptionData> consumptionData1: row.keySet()){
            consumptionData1.add(consumptionData);
        }
    }

    public void addUsageData(UsageData usageData){
        Collection<List<UsageData>> values = data.row(usageData.getHomeDevice()).values();

        for(List<UsageData> consumptionData1:values){
            consumptionData1.add(usageData);
        }
    }

    public StringBuilder generateReport(){

        Predicate<ConsumptionData> water = i -> i.getSourceType() == SourceType.WATER;

        StringBuilder res = new StringBuilder();
        data.cellSet()
                .stream()
                .forEach(
                 cell ->
                 res.append(
                 cell.getRowKey().getClass().getSimpleName() +                                            //Name of device

                 " in " + cell.getRowKey().getRoom().getRoomType().name().toLowerCase(Locale.ROOT) +       //Name of room

                 " on the " + cell.getRowKey().getRoom().getFloor().getNumberOfFloor() + " floor"         //Number of floor

                 +" has energy consumed  " + cell.getColumnKey().stream()
                         .map(x-> x.getAmountOfConsumed()).collect(Collectors.summingDouble(Double::doubleValue))           //Quantity of consumed energy

                + ((cell.getColumnKey().stream().filter(x-> x.getSourceType() == SourceType.WATER)
                         .map(x-> x.getAmountOfConsumed()).reduce(0.0, Double::sum) != 0.0) ? ", water consumed ":"")

                + "\n")
                );
        return res;
    }
}
