package cvut.omo.data_collections.consumption;

import cvut.omo.app_utils.Constants;
import cvut.omo.data_collections.Iterator;
import cvut.omo.entity.device.HomeDevice;
import cvut.omo.entity.device.SourceType;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

/**
 * Class represents iterating function over {@link ConsumptionCollection}
 * to generate consumption report
 */
public class ConsumptionReportIterator implements Iterator {

    private int currIdx = 0;
    private static ConsumptionCollection data = ConsumptionCollection.getInstance();
    /*stores data about source types and general consumption*/
    private final Dictionary<SourceType, Double> sourceTypes = new Hashtable<>();

    /**
     * Constructor for class.
     * Reset all values in {@link #sourceTypes}
     */
    public ConsumptionReportIterator() {
        for (SourceType sourceType : SourceType.values()) {
            sourceTypes.put(sourceType, 0.0);
        }
    }

    @Override
    public String next() {
        StringBuilder res = new StringBuilder();
        HomeDevice curr = data.getAt(currIdx);


        if(!curr.isNotConsume()) {
            List<ConsumptionData> dataAbout = data.getDataAbout(curr);

            res.append("[").append(currIdx+1).append("] ")
                    .append(curr.getClass().getSimpleName() + //NAME OF DEVICE
                    " in " + curr.getRoom().getRoomName() + // NAME OF ROOM
                    " on the " + curr.getRoom().getFloor().getNumberOfFloor() + " floor" + //NUMBER OF FLOOR
                    " has consumed ");

            if(dataAbout.stream().anyMatch(cons -> cons.getSourceType().equals(SourceType.ENERGY))){
                generateRow(dataAbout, res, SourceType.ENERGY, Constants.CURRENCY);
            }
            if(dataAbout.stream().anyMatch(cons -> cons.getSourceType().equals(SourceType.WATER))){
                generateRow(dataAbout, res, SourceType.WATER, Constants.CURRENCY);
            }
        }


        currIdx++;
        if(currIdx == data.getSize()){
            addInfoAboutConsumptionFromAllTime(res);
        }
        return res.toString();
    }

    /*generate single row*/
    private void generateRow(List<ConsumptionData> consumptionData, StringBuilder res,
                             SourceType sourceType, char currency)
    {
        double sumStub = consumptionData.stream().filter(x->x.getSourceType().equals(sourceType))
                .mapToDouble(ConsumptionData::getAmountOfConsumed).sum();

        double allConsumed = sourceTypes.get(sourceType) + sumStub;
        sourceTypes.put(sourceType, allConsumed);

        res.append(sourceType.name()).append(": ")
                .append(sumStub)
                .append(sourceType.getUNIT_OF_MEASUREMENT()).append(" (SUM = ").append(sourceType.getPRICE_FOR_UNIT() * sumStub)
                .append(currency).append(") ");
    }

    /*generate general info*/
    private void addInfoAboutConsumptionFromAllTime(StringBuilder res){
        res.append("\n");
        for (java.util.Iterator<SourceType> it = sourceTypes.keys().asIterator(); it.hasNext(); ){
            SourceType sourceType = it.next();
            if(!sourceType.equals(SourceType.NOT_CONSUME)) {
                Double sum = sourceTypes.get(sourceType);
                res.append(Constants.STARS)
                        .append(sum)
                        .append(sourceType.getUNIT_OF_MEASUREMENT()).append(" of ")
                        .append(sourceType).append(" was consumed during the whole time.")
                        .append(" (SUM = ").append(sourceType.getPRICE_FOR_UNIT() * sum)
                        .append(Constants.CURRENCY).append(") ");
            }
        }
        res.append(Constants.STARS);
    }

    @Override
    public boolean hasNext() {
        return currIdx != data.getSize();
    }

}
