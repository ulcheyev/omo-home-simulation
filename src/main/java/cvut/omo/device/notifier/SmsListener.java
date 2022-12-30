package cvut.omo.device.notifier;

import cvut.omo.device.Sensor;

import java.util.logging.Logger;

public class SmsListener implements EventListener {

    private static Logger logger = Logger.getLogger(SmsListener.class.getSimpleName());

    @Override
    public void update(String message, Sensor sensor) {
//        logger.info(message);
    }
}
