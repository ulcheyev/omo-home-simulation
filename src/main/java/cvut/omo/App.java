package cvut.omo;
import cvut.omo.data_collections.consumption.ConsumptionCollection;
import cvut.omo.data_collections.events.EventCollection;
import cvut.omo.data_collections.visitor.HomeConfigurationReportVisitor;
import cvut.omo.event.EventGenerator;
import cvut.omo.home_structure.home_builder.Home;
import cvut.omo.home_structure.home_builder.SmartHomeBuilder;
import cvut.omo.home_structure.home_builder.SmartHomeBuilderDirector;

import java.io.IOException;

public class App {


    public static void main(String[] args) throws IOException, InterruptedException {


        SmartHomeBuilderDirector.createLargeHomeConfiguration(SmartHomeBuilder.INSTANCE);
        Home home = SmartHomeBuilder.INSTANCE.getResult();
        home.connectAllDevices();
        //GENERATE EVENTS FOR PERSONS, HOMEDEVICES AND ROOMS
        EventGenerator.generateRandomEvents(10000);

        while (!EventCollection.allSolved()) {
            home.update();
        }

        EventCollection.generateReport();
        ConsumptionCollection.getInstance().generateReport();
        HomeConfigurationReportVisitor visitor = new HomeConfigurationReportVisitor();
        visitor.generateReport();
    }
}



