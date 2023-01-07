package cvut.omo.entity.device.notifier;

import cvut.omo.entity.device.Sensor;

import java.util.logging.Logger;

/**
 * Class represents sms notifier (implemented with logger).
 */
public class SmsListener implements EventListener {

    private static Logger logger = Logger.getLogger(SmsListener.class.getSimpleName());

    /**
     * Writes alarm events to logger
     * @param message message to send
     * @param sensor sensor, which alarm went off
     */
    @Override
    public void update(String message, Sensor sensor) {
        logger.info(message);
    }
}
