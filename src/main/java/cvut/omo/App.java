package cvut.omo;
import cvut.omo.data_collections.consumption.ConsumptionCollection;
import cvut.omo.data_collections.activity_events.SmartHomeEventCollection;
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
        home.onAllDevices();

        //GENERATE EVENTS FOR PERSONS, HOMEDEVICES AND ROOMS
        EventGenerator.generateRandomEvents(1000);

        while (!SmartHomeEventCollection.allSolved()) {
            home.update();
        }

        SmartHomeEventCollection.generateEventReport();
        SmartHomeEventCollection.generateActivityAndUsageReport();
        ConsumptionCollection.getInstance().generateReport();
        HomeConfigurationReportVisitor visitor = new HomeConfigurationReportVisitor();
        visitor.generateReport();

    }
}
























//System.out.println("\n" +
//        "███████╗███╗   ███╗ █████╗ ██████╗ ████████╗    ██╗  ██╗ ██████╗ ███╗   ███╗███████╗\n" +
//        "██╔════╝████╗ ████║██╔══██╗██╔══██╗╚══██╔══╝    ██║  ██║██╔═══██╗████╗ ████║██╔════╝\n" +
//        "███████╗██╔████╔██║███████║██████╔╝   ██║       ███████║██║   ██║██╔████╔██║█████╗  \n" +
//        "╚════██║██║╚██╔╝██║██╔══██║██╔══██╗   ██║       ██╔══██║██║   ██║██║╚██╔╝██║██╔══╝  \n" +
//        "███████║██║ ╚═╝ ██║██║  ██║██║  ██║   ██║       ██║  ██║╚██████╔╝██║ ╚═╝ ██║███████╗\n" +
//        "╚══════╝╚═╝     ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝   ╚═╝       ╚═╝  ╚═╝ ╚═════╝ ╚═╝     ╚═╝╚══════╝\n" +
//        "                                                                                    \n");