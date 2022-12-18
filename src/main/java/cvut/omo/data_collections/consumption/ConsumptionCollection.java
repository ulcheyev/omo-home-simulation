package cvut.omo.data_collections.consumption;

import cvut.omo.app_utils.Constants;
import cvut.omo.app_utils.FileWriter;
import cvut.omo.data_collections.SmartHomeCollection;
import cvut.omo.device.HomeDevice;
import cvut.omo.device.SourceType;

import java.io.IOException;
import java.util.*;

public class ConsumptionCollection implements SmartHomeCollection {

    private static final HashMap<HomeDevice, List<ConsumptionData>> data = new HashMap<>();
    private static ConsumptionCollection collection;
    private static int size = 0;

    private ConsumptionCollection(){}

    public static ConsumptionCollection getInstance(){
        if(collection == null){
            return new ConsumptionCollection();
        }
        return collection;

    }

    @Override
    public void put(HomeDevice homeDevice) {
        if(!data.containsKey(homeDevice)) {
            List<ConsumptionData> list = new ArrayList<>();
            for (SourceType sourceType : homeDevice.getSourceTypes()) {
                list.add(new ConsumptionData(homeDevice, sourceType));
            }
            data.put(homeDevice, list);
            size++;
        }
    }

    @Override
    public void addConsumptionData(ConsumptionData consumptionData) {
        data.get(consumptionData.getHomeDevice()).add(consumptionData);
    }

    @Override
    public void update(HomeDevice homeDevice) {
        for(ConsumptionData consumptionData: data.get(homeDevice)){
            consumptionData.update();
        }
    }

    @Override
    public HomeDevice getHomeDeviceAt(int idx){
        return (HomeDevice) data.keySet().toArray()[idx];
    }

    @Override
    public List<ConsumptionData> getDataAbout(HomeDevice homeDevice){
        return data.get(homeDevice);
    }

    @Override
    public int getSize() {return size;}

    @Override
    public void generateReport() throws IOException {
        StringBuilder res = new StringBuilder();
        res.append(Constants.CONSUMPTION_REPORT_HEADER);
        ConsumptionReportIterator iterator = createConsumptionReportIterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            if(!next.isBlank()) {
                res.append(next).append("\n");
            }
        }
        FileWriter.generateNewLog("consumption_report" + new Random().nextInt(), res.toString());
    }


    public ConsumptionReportIterator createConsumptionReportIterator() {
        return new ConsumptionReportIterator();
    }
}
