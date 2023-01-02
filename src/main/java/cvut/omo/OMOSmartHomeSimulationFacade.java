package cvut.omo;

import cvut.omo.app_utils.Utils;
import cvut.omo.data_collections.activity_events.SmartHomeEventCollection;
import cvut.omo.data_collections.consumption.ConsumptionCollection;
import cvut.omo.data_collections.visitor.HomeConfigurationReportReportVisitor;
import cvut.omo.home_structure.home_builder.Home;
import cvut.omo.home_structure.home_builder.SmartHomeBuilder;
import cvut.omo.home_structure.home_builder.SmartHomeBuilderDirector;
import java.io.IOException;
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
        System.out.println("**CHECK THE REPORTS FOLDER IN THE ROOT**");
    }


}





