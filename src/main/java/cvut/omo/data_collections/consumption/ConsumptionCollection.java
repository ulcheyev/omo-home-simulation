package cvut.omo.data_collections.consumption;

import cvut.omo.app_utils.Constants;
import cvut.omo.app_utils.FileWriter;
import cvut.omo.app_utils.Utils;
import cvut.omo.entity.device.HomeDevice;
import cvut.omo.entity.device.SourceType;
import lombok.Getter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class represents collection, which store all {@link ConsumptionData} about devices.
 */
@Getter
public class ConsumptionCollection implements IConsumptionCollection {

    private static final HashMap<HomeDevice, List<ConsumptionData>> data = new HashMap<>();
    private static ConsumptionCollection collection;
    private static int size = 0;

    private ConsumptionCollection(){}

    /**
     * Collection is defining like a singleton.
     * @return instance of collection
     */
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
    public void update(HomeDevice homeDevice) {
        for(ConsumptionData consumptionData: data.get(homeDevice)){
            consumptionData.update();
        }
    }

    @Override
    public HomeDevice getAt(int idx){
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
        System.out.println("GENERATE CONSUMPTION REPORT");
        StringBuilder res = new StringBuilder();
        res.append(Constants.CONSUMPTION_REPORT_HEADER);
        ConsumptionReportIterator iterator = createConsumptionReportIterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            if(!next.isBlank()) {
                res.append(next).append("\n");
            }
        }
        FileWriter.generateNewReport("consumption_report" + Utils.getRandomInt(), res.toString());
    }


    /**
     * {@return {@link ConsumptionReportIterator} for generating report}
     */
    public ConsumptionReportIterator createConsumptionReportIterator() {
        return new ConsumptionReportIterator();
    }

}
