package cvut.omo.device.device_data_structure;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import cvut.omo.device.HomeDevice;

import java.util.*;

public class ConsumptionAndUsageCollection implements SmartHomeCollection{

    private static ConsumptionAndUsageCollection INSTANCE;
    private static Table<HomeDevice, List<ConsumptionData>, List<UsageData>> data = HashBasedTable.create();
    private static int size = 0;


    public static ConsumptionAndUsageCollection getInstance(){
        if(INSTANCE == null){
            INSTANCE = new ConsumptionAndUsageCollection();
        }
        return INSTANCE;
    }

    private ConsumptionAndUsageCollection() {
    }

    public void putHomeDevice(HomeDevice homeDevice){
          data.put(homeDevice, homeDevice.getConsumptionData(), homeDevice.getUsageDataList());
          size++;
    }


    public void addConsumptionData(ConsumptionData consumptionData){
        Map<List<ConsumptionData>, List<UsageData>> row = data.row(consumptionData.getHomeDevice());

        for(List<ConsumptionData> consumptionData1: row.keySet()){
            consumptionData1.add(consumptionData);
        }
    }

    public Set<HomeDevice> getHomeDevices(){
        return data.rowKeySet();
    }

    public HomeDevice getHomeDeviceAt(int idx){
        return (HomeDevice) data.rowKeySet().toArray()[idx];
    }

    public Map<List<ConsumptionData>, List<UsageData>> getDataAbout(HomeDevice homeDevice){
        return data.row(homeDevice);
    }

    public void addUsageData(UsageData usageData){
        Collection<List<UsageData>> values = data.row(usageData.getHomeDevice()).values();

        for(List<UsageData> consumptionData1:values){
            consumptionData1.add(usageData);
        }
    }


    public StringBuilder generateReport(Date from, Date to){
        StringBuilder res = new StringBuilder();
        res.append("\nREPORT FROM DATE TO DATE\n\n");
        Iterator iterator = createIteratorForGenerateReports(from, to);
        while (iterator.hasNext()){
            res.append(iterator.next()).append("\n");
        }
        return res;
    }

    public StringBuilder generateReport(){
        StringBuilder res = new StringBuilder();
        res.append("\nREPORT FROM ALL TIME\n\n");
        ReportIterator iterator = createIteratorForGenerateReports();
        while (iterator.hasNext()){
            String next = iterator.next();
            if(!next.isBlank()) {
                res.append(next).append("\n");
            }
        }
        return res;
    }

    @Override
    public  ReportIterator createIteratorForGenerateReports() {
        return new ReportIterator();
    }

    public ReportIterator createIteratorForGenerateReports(Date from, Date to) {
        return new ReportIterator(from, to);
    }

    public int getSize() {
        return size;
    }
}
