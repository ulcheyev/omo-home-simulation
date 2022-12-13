package cvut.omo.device;

import cvut.omo.device.device_data_structure.ConsumptionData;
import cvut.omo.device.device_data_structure.SourceType;
import cvut.omo.device.device_data_structure.UsageData;
import cvut.omo.home_structure.Room;

import java.util.List;

public interface HomeDevice {
     void turnOn();
     void turnOff();
     void pause();
     void stop();
     List<ConsumptionData> getConsumptionData();
     List<UsageData> getUsageDataList();
     void setRoom(Room room);
     Room getRoom();
     void changeCurrentConsumption(SourceType sourceType, double currentConsumption);
     void update();
     double getWorkingHours();
     Documentation getDocumentation();
}
