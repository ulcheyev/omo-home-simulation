package cvut.omo;

import cvut.omo.data_collections.activity_events.SmartHomeEventCollection;
import cvut.omo.data_collections.consumption.ConsumptionCollection;
import cvut.omo.data_collections.visitor.HomeConfigurationReportVisitor;
import cvut.omo.device.notifier.EmailListener;
import cvut.omo.event.EventGenerator;
import cvut.omo.home_structure.home_builder.Home;
import cvut.omo.home_structure.home_builder.SmartHomeBuilder;
import cvut.omo.home_structure.home_builder.SmartHomeBuilderDirector;

import java.io.IOException;
import java.util.Scanner;

public class OMOSmartHomeSimulationFacade {


    private Home home;
    public final static OMOSmartHomeSimulationFacade INSTANCE = new OMOSmartHomeSimulationFacade();

    private OMOSmartHomeSimulationFacade(){
        hello();
        checkInput();
    }


    public void createLargeConfig(){
        SmartHomeBuilderDirector.createLargeHomeConfiguration(SmartHomeBuilder.INSTANCE);
        home = SmartHomeBuilder.INSTANCE.getResult();
    }

    public void createSmallConfig(){
        SmartHomeBuilderDirector.createSmallHomeConfiguration(SmartHomeBuilder.INSTANCE);
        home = SmartHomeBuilder.INSTANCE.getResult();
    }

    public void simulate(){
        EventGenerator.generateRandomEvents(1000);
        while (!SmartHomeEventCollection.allSolved()) {
            try {
                home.update();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void generateEventReport(){
        try {
            SmartHomeEventCollection.generateEventReport();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateActivityAndUsageReport(){
        try {
            SmartHomeEventCollection.generateActivityAndUsageReport();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateConsumptionReport(){
        try {
            ConsumptionCollection.getInstance().generateReport();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateHomeConfigurationReport(){
        try {
            HomeConfigurationReportVisitor visitor = new HomeConfigurationReportVisitor();
            visitor.generateReport();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateAllReports(){
        generateEventReport();
        generateActivityAndUsageReport();
        generateConsumptionReport();
        generateHomeConfigurationReport();
    }

    private void hello() {
        System.out.println("\n" +
        "███████╗███╗   ███╗ █████╗ ██████╗ ████████╗    ██╗  ██╗ ██████╗ ███╗   ███╗███████╗\n" +
        "██╔════╝████╗ ████║██╔══██╗██╔══██╗╚══██╔══╝    ██║  ██║██╔═══██╗████╗ ████║██╔════╝\n" +
        "███████╗██╔████╔██║███████║██████╔╝   ██║       ███████║██║   ██║██╔████╔██║█████╗  \n" +
        "╚════██║██║╚██╔╝██║██╔══██║██╔══██╗   ██║       ██╔══██║██║   ██║██║╚██╔╝██║██╔══╝  \n" +
        "███████║██║ ╚═╝ ██║██║  ██║██║  ██║   ██║       ██║  ██║╚██████╔╝██║ ╚═╝ ██║███████╗\n" +
        "╚══════╝╚═╝     ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝   ╚═╝       ╚═╝  ╚═╝ ╚═════╝ ╚═╝     ╚═╝╚══════╝\n" +
        "                                                                                    \n");
    }

    private void checkInput() {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter email for accept alerts: ");
        String email = myObj.nextLine();
        EmailListener.setEmail(email);
    }





}
