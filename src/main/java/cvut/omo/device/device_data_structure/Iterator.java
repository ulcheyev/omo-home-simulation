package cvut.omo.device.device_data_structure;

import cvut.omo.device.HomeDevice;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public interface Iterator {
     Object next();
     boolean hasNext();
}
