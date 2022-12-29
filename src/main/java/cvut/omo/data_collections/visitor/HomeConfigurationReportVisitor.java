package cvut.omo.data_collections.visitor;

import cvut.omo.app_utils.Constants;
import cvut.omo.app_utils.FileWriter;
import cvut.omo.app_utils.Utils;
import cvut.omo.device.HomeDevice;
import cvut.omo.entity.person.Person;
import cvut.omo.entity.pet.Pet;
import cvut.omo.home_structure.Floor;
import cvut.omo.home_structure.home_builder.Home;
import cvut.omo.home_structure.HomeComponent;
import cvut.omo.home_structure.room_builder.Room;

import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;


/**
 *
 */
public class HomeConfigurationReportVisitor implements SmartHomeVisitor{

    private final Dictionary<Class<? extends HomeComponent>, Integer> quantities = new Hashtable<>();

    /**
     * @throws IOException
     */
    public void generateReport() throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(Constants.HOME_CONFIG_REPORT_HEADER);
        for(HomeComponent homeComponent: Home.INSTANCE.getComponentsForReport()){
            sb.append(homeComponent.accept(this));
        }
        sb.append(getComponentQuantity());
        FileWriter.generateNewConsumptionReport("home_config_report" + Utils.getRandomInt(), sb.toString());
    }

    /**
     * @param floor
     * @return
     */
    @Override
    public String visitFloor(Floor floor) {
        StringBuilder sb = new StringBuilder();
        sb.append("-Floor "+ floor.getNumberOfFloor() + " contains: " + "\n");
        registerComponentQuantity(floor);
        for(Room room: floor.getRooms()){
            sb.append(room.accept(this));
        }
        return sb.toString();
    }

    /**
     * @param room
     * @return
     */
    @Override
    public String visitRoom(Room room) {
        StringBuilder sb = new StringBuilder();
        registerComponentQuantity(room);
        sb.append("---Room " +  room.getRoomName() + " with " + room.getWindows().size() + " windows and contains: \n");
        if(room.getHomeDevices().isEmpty()){
            sb.append("nothing\n");
        }else{
            for (HomeDevice homeDevice : room.getHomeDevices()) {
                sb.append(homeDevice.accept(this));
            }
        }
        return sb.toString();

    }

    /**
     * @param homeDevice
     * @return
     */
    @Override
    public String visitHomeDevice(HomeDevice homeDevice) {
        registerComponentQuantity(homeDevice);
        return "-----Home device " + homeDevice.getClass().getSimpleName() + "\n";
    }

    /**
     * @param person
     * @return
     */
    @Override
    public String visitPerson(Person person) {
        registerComponentQuantity(person);
        return "\n+++Person " + person.getName() + " lives in the home with a role " + person.getResponsibleType();
    }

    /**
     * @param pet
     * @return
     */
    @Override
    public String visitPet(Pet pet) {
        registerComponentQuantity(pet);
        return "\n***Pet lives in the house with a role "  + pet.getResponsibleType();
    }

    private void registerComponentQuantity(HomeComponent component){
        Integer res = quantities.get(component.getClass());
        if (res == null) res = 1;
        else res++;
        quantities.put(component.getClass(), res);
    }

    private String getComponentQuantity(){
        StringBuilder res = new StringBuilder();
        res.append(Constants.STARS_UP);
        for (Iterator<Class<? extends HomeComponent>> it = quantities.keys().asIterator(); it.hasNext(); ) {
            Class<? extends HomeComponent> hc = it.next();
            Integer quant = quantities.get(hc);
            res.append("Home has " + hc.getSimpleName() + " in the amount of " + quant + " pieces.\n");
        }
        res.append(Constants.STARS_DOWN);
        return res.toString();
    }


}
