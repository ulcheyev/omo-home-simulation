package cvut.omo.entity.device;

import cvut.omo.app_utils.Constants;
import cvut.omo.data_collections.consumption.ConsumptionCollection;
import cvut.omo.entity.device.documentation.Documentation;
import cvut.omo.entity.device.notifier.EmailListener;
import cvut.omo.entity.device.notifier.EventListener;
import cvut.omo.entity.device.notifier.SmsListener;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * General class for all sensors.
 */
public abstract class Sensor extends HomeAppliances {

    protected List<EventListener> listenerList;
    protected static Documentation documentation;

    public Sensor(double lifeTime) {
        super(lifeTime);
    }

    /**
     * Creates and add {@link EventListener} to {@link #listenerList}.
     */
    @Override
    protected void identify() {
        this.currentConsumption.put(SourceType.ENERGY, Constants.DEVICE_OFF_STATE_ELECTRICITY);
        ConsumptionCollection.getInstance().put(this);
        listenerList = new ArrayList<>();
        listenerList.add(new EmailListener());
        listenerList.add(new SmsListener());
    }

    public void enable() {
        this.setCurrentConsumption(SourceType.ENERGY, Constants.SENSOR_ELECTRICITY_IDDLE_STATE_CONSUMPTION);
    }

    ;


    public void goIntoIddleMode() {
        this.setCurrentConsumption(SourceType.ENERGY, Constants.SENSOR_ELECTRICITY_IDDLE_STATE_CONSUMPTION);
    }

    ;

    public void run() throws MessagingException, IOException {
        this.setCurrentConsumption(SourceType.ENERGY, Constants.SENSOR_ELECTRICITY_RUN_STATE_CONSUMPTION);
        alert();
    }

    ;

    /**
     * Causes alarm in the house.
     * Each sensor has its own implementation.
     * Notifies all {@link #listenerList}
     */
    public abstract void alert() throws MessagingException, IOException;
}
