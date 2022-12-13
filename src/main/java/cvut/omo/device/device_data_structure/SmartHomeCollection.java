package cvut.omo.device.device_data_structure;

public interface SmartHomeCollection {
    Iterator createIteratorForGenerateReports();
    void  addConsumptionData(ConsumptionData consumptionData);
    void addUsageData(UsageData usageData);
}
