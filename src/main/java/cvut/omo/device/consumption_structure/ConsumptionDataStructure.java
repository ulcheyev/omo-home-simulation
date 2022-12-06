package cvut.omo.device.consumption_structure;

import cvut.omo.device.HomeAppliances;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//TODO singleton
public class ConsumptionDataStructure {

    private HashMap<HomeAppliances, ConsumptionData> hashMap;

    public boolean add(ConsumptionData consumptionData, HomeAppliances homeDevice){
        return true;
    }

    public boolean update(HomeAppliances homeDevice){
        hashMap.get(homeDevice).update();
        return true;
    }

    public boolean remove(ConsumptionData consumptionData, HomeAppliances homeDevice){
        return true;
    }

    public boolean containsKey(HomeAppliances homeDevice){
        return true;
    }

    public boolean containsData(ConsumptionData consumptionData){
        return true;
    }

    public List<ConsumptionData> getData(HomeAppliances homeDevice){
        return new ArrayList<>();
    }

    public void addAll(List<HomeAppliances> homeDevices){
        for(HomeAppliances homeDevice: homeDevices){
            hashMap.put(homeDevice, homeDevice.getConsumptionData());
        }
    }
}
