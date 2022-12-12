package cvut.omo.device.device_data_structure;

import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public interface Iterator {

     Pair<List<ConsumptionData>, List<UsageData>> next();
     boolean hasNext();
}
