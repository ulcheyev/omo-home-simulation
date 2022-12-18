package cvut.omo.data_collections.consumption;

import cvut.omo.app_utils.Constants;
import cvut.omo.device.HomeDevice;
import cvut.omo.data_collections.Iterator;
import cvut.omo.device.SourceType;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class ConsumptionReportIterator implements Iterator {

    private int currIdx = 0;
    private static ConsumptionCollection data = ConsumptionCollection.getInstance();

    @Override
    public String next() {
        StringBuilder res = new StringBuilder();
        HomeDevice curr = data.getHomeDeviceAt(currIdx);

        if(!curr.isNotConsume()) {
            List<ConsumptionData> dataAbout = data.getDataAbout(curr);

            res.append(curr.getClass().getSimpleName() + //NAME OF DEVICE
                    " in " + curr.getRoom().getRoomType() + // NAME OF ROOM
                    " on the " + curr.getRoom().getFloor().getNumberOfFloor() + " floor" + //NUMBER OF FLOOR
                    " has consumed ");

            if(dataAbout.stream().anyMatch(cons -> cons.getSourceType().equals(SourceType.ENERGY))){
                generateRow(dataAbout, res, SourceType.ENERGY,
                        Constants.ELECTRICITY_UNIT_OF_MEASUREMENT,
                        Constants.ELECTRICITY_PRICE_KWH, Constants.CURRENCY);
            }
            if(dataAbout.stream().anyMatch(cons -> cons.getSourceType().equals(SourceType.WATER))){
                generateRow(dataAbout, res, SourceType.WATER,
                        Constants.WATER_UNIT_OF_MEASUREMENT,
                        Constants.WATER_PRICE_M3, Constants.CURRENCY);
            }
        }

        currIdx++;
        return res.toString();
    }

    private void generateRow(List<ConsumptionData> consumptionData, StringBuilder res,
                             SourceType sourceType, String unit, double price, char currency)
    {
        double sumStub = consumptionData.stream().filter(x->x.getSourceType().equals(sourceType))
                .mapToDouble(ConsumptionData::getAmountOfConsumed).sum();
        res.append(sourceType.name()).append(": ")
                .append(sumStub)
                .append(unit).append(" (SUM = ").append(price * sumStub).append(currency).append(") ");
    }

    @Override
    public boolean hasNext() {
        return currIdx != data.getSize();
    }

}
