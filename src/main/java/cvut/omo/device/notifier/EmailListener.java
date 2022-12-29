package cvut.omo.device.notifier;

import cvut.omo.app_utils.Constants;
import cvut.omo.app_utils.FileWriter;
import cvut.omo.device.Sensor;

import java.io.IOException;
import java.util.Random;

/**
 *
 */
public class EmailListener implements EventListener{

    protected String nameOfDocumentation;


    /**
     * @param message
     * @param sensor
     * @throws IOException
     */
    @Override
    public void update(String message, Sensor sensor) throws IOException {
        StringBuilder res = new StringBuilder();

        res.append(Constants.PDF_EMAIL_HEADER);

        final String text =
                "Hello,\n we notify you that it worked in the house: " + sensor.toString() + message + "\nrespectfully, your Smart Home";

        nameOfDocumentation = sensor + "_email" + new Random().nextInt();
        FileWriter.generateNewEmail(nameOfDocumentation,res.toString());
    }

}
