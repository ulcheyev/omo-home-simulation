package cvut.omo;

public class App {


    public static void main(String[] args) {
        OMOSmartHomeSimulationFacade smartHome = OMOSmartHomeSimulationFacade.INSTANCE;

        //CREATE CONFIG
        smartHome.createSmallConfig();

        //DO SIMULATION
        smartHome.simulate();

        //GENERATE REPORTS
        smartHome.generateAllReports();

    }
}



