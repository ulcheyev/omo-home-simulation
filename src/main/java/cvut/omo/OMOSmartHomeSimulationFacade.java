package cvut.omo;

import cvut.omo.app_utils.Utils;
import cvut.omo.data_collections.activity_events.SmartHomeEventCollection;
import cvut.omo.data_collections.consumption.ConsumptionCollection;
import cvut.omo.data_collections.visitor.HomeConfigurationReportReportVisitor;
import cvut.omo.entity.device.notifier.EmailListener;
import cvut.omo.home_structure.home_builder.Home;
import cvut.omo.home_structure.home_builder.SmartHomeBuilder;
import cvut.omo.home_structure.home_builder.SmartHomeBuilderDirector;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static cvut.omo.app_utils.Utils.checkInput;
import static cvut.omo.app_utils.Utils.hello;

/**
 * Facade for simulate life in {@link Home} and generate reports.
 */
public class OMOSmartHomeSimulationFacade {


    private Home home;

    /**
     * Instance of {@link OMOSmartHomeSimulationFacade }
     */
    public final static OMOSmartHomeSimulationFacade INSTANCE = new OMOSmartHomeSimulationFacade();

    private OMOSmartHomeSimulationFacade() {
        hello();
        checkInput();
    }

    /**
     * Creates large configuration in {@link SmartHomeBuilderDirector }
     */
    public void createLargeConfig(){
        SmartHomeBuilderDirector.createLargeHomeConfiguration(SmartHomeBuilder.INSTANCE);
        home = SmartHomeBuilder.INSTANCE.getResult();
    }

    /**
     * Creates small configuration in {@link SmartHomeBuilderDirector }
     */
    public void createSmallConfig(){
        SmartHomeBuilderDirector.createSmallHomeConfiguration(SmartHomeBuilder.INSTANCE);
        home = SmartHomeBuilder.INSTANCE.getResult();
    }

    /**
     * Start simulation
     */
    public void simulate(){
        int inputNumber = Utils.getInputNumber("Enter days quantity of simulation:");
        Simulation.simulate(inputNumber);

    }


    /**
     * Generates events report
     */
    public void generateEventReport(){
        try {
            SmartHomeEventCollection.generateEventReport();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generates activity and usage report
     */
    public void generateActivityAndUsageReport(){
        try {
            SmartHomeEventCollection.generateActivityAndUsageReport();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generates consumption report
     */
    public void generateConsumptionReport(){
        try {
            ConsumptionCollection.getInstance().generateReport();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generates home configuration report
     */
    public void generateHomeConfigurationReport(){
        try {
            HomeConfigurationReportReportVisitor visitor = new HomeConfigurationReportReportVisitor();
            visitor.generateReport();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generates all reports
     */
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
        Scanner ano_ne_obj = new Scanner(System.in);
        System.out.println("Do you want to receive email notifications?");
        String ano_ne = ano_ne_obj.nextLine();
        Pattern pattern = Pattern.compile("^((\\w|[-+])+(\\.[\\w-]+)*@[\\w-]+((\\.[\\d\\p{Alpha}]+)*(\\.\\p{Alpha}{2,})*)*)$");
        while (!(ano_ne.equals("yes") || ano_ne.equals("no"))) {
            System.out.println("Please enter a valid value: yes/no");
            ano_ne = ano_ne_obj.nextLine();
        }
        if (ano_ne.equals("yes")) {
            EmailListener.setWantEMail(true);
            System.out.println("Enter email for accept alerts: ");
            String email = myObj.nextLine();
            Matcher matcher = pattern.matcher(email);
            while (!matcher.find()) {
                System.out.println("Please enter a valid email for accept alerts");
                email = myObj.nextLine();
            }
            EmailListener.setEmail(email);
        } else if (ano_ne.equals("no")) {
            EmailListener.setWantEMail(false);
        }
    }
}




}
