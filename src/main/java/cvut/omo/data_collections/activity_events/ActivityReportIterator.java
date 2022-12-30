package cvut.omo.data_collections.activity_events;

import cvut.omo.app_utils.Constants;
import cvut.omo.app_utils.Utils;
import cvut.omo.data_collections.Iterator;
import cvut.omo.device.HomeAppliances;
import cvut.omo.device.HomeDevice;
import cvut.omo.device.SourceType;
import cvut.omo.entity.Responsible;
import cvut.omo.entity.activity.Activity;
import cvut.omo.entity.activity.DeviceActivity;
import cvut.omo.entity.activity.ItemActivity;
import cvut.omo.event.Event;
import cvut.omo.home_structure.home_builder.Home;
import cvut.omo.usable.Usable;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;


public class ActivityReportIterator implements Iterator {

    private final List<Responsible> responsibleList;
    private Map<Class<? extends Usable> , Integer> usageData;

    public ActivityReportIterator(){
        responsibleList = Home.INSTANCE.getAllEntityResponsibles();
        usageData = new Hashtable<>();
    }

    private static int currIdx = 0;

    @Override
    public Object next() {
        usageData = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        sb
                .append("[")
                .append(currIdx)
                .append("] ")
                .append(responsibleList.get(currIdx).getResponsibleType())
                .append(" activities :\n");

        Responsible responsible = responsibleList.get(currIdx++);

        for(Event event: SmartHomeEventCollection.getAll()){
            for(Activity activity : event.getChainToSolve()){
                if(activity.getResponsible().equals(responsible)){
                    checkOnUsageAktivity(activity);
                    sb.append("----").append(activity).append("\n");
                }
            }
        }
        sb.append(generateGeneralInfo(responsible));
        return sb.toString();
    }

    @Override
    public boolean hasNext() {
        return responsibleList.size() != currIdx;
    }

    private void checkOnUsageAktivity(Activity activity){
        Class<? extends Activity> solver = activity.getActivityType().getSolver();
        if(solver.equals(DeviceActivity.class)
        || solver.equals(ItemActivity.class))
        {
            Class<? extends Usable> toUse = identifuToUse(activity);

            if(!usageData.containsKey(toUse)){
                usageData.put(toUse, 1);
            }else{
                usageData.put(toUse, usageData.get(toUse)+1);
            }

        }

    }

    private String generateGeneralInfo(Responsible responsible) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Constants.STARS_DOWN);
        for(var item: usageData.keySet()){
            stringBuilder
                    .append("\n➨ ")
                    .append(responsible.getResponsibleType())
                    .append(" used " )
                    .append(item.getSimpleName())
                    .append(" ")
                    .append(usageData.get(item))
                    .append(" times.");
        }
        stringBuilder.append(Constants.STARS);
        return stringBuilder.toString();
    }

    private Class<? extends Usable> identifuToUse(Activity activity) {
        Class<? extends Usable> toUse = activity.getActivityType().getToUse();
        if(toUse == null){
            toUse = (((HomeAppliances) activity.getEvent().getResponsible()).getClass());
        }
        return toUse;
    }

}
