package cvut.omo;

import cvut.omo.app_utils.Utils;
import cvut.omo.data_collections.activity_events.SmartHomeEventCollection;
import cvut.omo.data_collections.consumption.ConsumptionCollection;
import cvut.omo.data_collections.visitor.HomeConfigurationReportVisitor;
import cvut.omo.device.notifier.EmailListener;
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
        int inputNumber = Utils.getInputNumber("Input days quantity of simulation:");
        Simulation.simulate(inputNumber);

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
        Scanner input = new Scanner(System.in);
        System.out.print("Enter email for alerts:");
        EmailListener.setEmail(input.nextLine());
    }





}
