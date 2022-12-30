package cvut.omo.device;

import cvut.omo.app_utils.Constants;
import cvut.omo.data_collections.consumption.ConsumptionCollection;
import cvut.omo.device.documentation.Documentation;
import cvut.omo.device.notifier.EmailListener;
import cvut.omo.device.notifier.EventListener;
import cvut.omo.device.notifier.SmsListener;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public abstract class Sensor extends HomeAppliances {

    protected List<EventListener> listenerList;
    protected static Documentation documentation;

    public Sensor(double lifeTime) {super(lifeTime);}

    @Override
    protected void identify() {
        this.currentConsumption.put(SourceType.ENERGY, Constants.DEVICE_OFF_STATE_ELECTRICITY);
        ConsumptionCollection.getInstance().put(this);
        listenerList = new ArrayList<>();
        listenerList.add(new EmailListener());
        listenerList.add(new SmsListener());
    }

    public  void enable(){
        this.setCurrentConsumption(SourceType.ENERGY, Constants.SENSOR_ELECTRICITY_IDDLE_STATE_CONSUMPTION);
    };


    public void goIntoIddleMode(){
        this.setCurrentConsumption(SourceType.ENERGY, Constants.SENSOR_ELECTRICITY_IDDLE_STATE_CONSUMPTION);
    };

    public void run(){
        this.setCurrentConsumption(SourceType.ENERGY, Constants.SENSOR_ELECTRICITY_RUN_STATE_CONSUMPTION);
        try {
            alert();
        } catch (IOException | MessagingException e) {
            e.printStackTrace();
        }
    };

    public abstract void addListener(EventListener listener);
    public abstract void removeListener(EventListener listener);
    public abstract void alert() throws IOException, MessagingException;
}
