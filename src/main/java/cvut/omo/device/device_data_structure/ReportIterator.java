package cvut.omo.device.device_data_structure;

import cvut.omo.app_utils.Constants;
import cvut.omo.device.HomeDevice;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@NoArgsConstructor
public class ReportIterator implements Iterator{

    private final ConsumptionAndUsageCollection collection = ConsumptionAndUsageCollection.getInstance();
    private int currIdx = 0;

    private Date from;
    private Date to;

    public ReportIterator(Date from, Date to) {this.from = from; this.to = to;}

    @Override
    public String next() {
        StringBuilder res = new StringBuilder();
        HomeDevice curr = collection.getHomeDeviceAt(currIdx);
        Map<List<ConsumptionData>, List<UsageData>> dataAbout = collection.getDataAbout(curr);

        res.append(curr.getClass().getSimpleName() + //NAME OF DEVICE
                        " in " + curr.getRoom().getRoomType() + // NAME OF ROOM
                        " on the " + curr.getRoom().getFloor().getNumberOfFloor() + " floor" + //NUMBER OF FLOOR
                        " has consumed ");

        dataAbout.forEach(
                (cons, us) -> {
                                if (cons.stream().anyMatch(x-> x.getSourceType() == SourceType.NOT_CONSUME)) {
                                    res.delete(0, res.length());
                                }
                                if(cons.stream().anyMatch(x-> x.getSourceType() == SourceType.ENERGY)){
                                    double sumStub = cons.stream()
                                            .filter(x->x.getSourceType()==SourceType.ENERGY)
                                            .mapToDouble(x -> x.getAmountOfConsumed()).sum();
                                    res.append("energy: ")
                                            .append(sumStub)
                                            .append(Constants.ELECTRICITY_UNIT_OF_MEASURMENT)
                                            .append(" (SUM = " + (Constants.ELECTRICITY_PRICE_KWH * sumStub)+"$)");
                                }
                                if(cons.stream().anyMatch(x-> x.getSourceType() == SourceType.WATER)){
                                    double sumStub = cons.stream()
                                            .filter(x->x.getSourceType()==SourceType.WATER)
                                            .mapToDouble(x -> x.getAmountOfConsumed()).sum();
                                    res.append(", water: ")
                                        .append(sumStub)
                                        .append(Constants.WATER_UNIT_OF_MEASURMENT)
                                        .append(" (SUM = " + (Constants.WATER_PRICE_M3 * sumStub)+"$)");
                                }
                });
        currIdx++;
        return res.toString();
    }

    @Override
    public boolean hasNext() {
        return currIdx != collection.getSize();
    }

}
