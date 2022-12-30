package cvut.omo;

public class App {


    public static void main(String[] args) {

        OMOSmartHomeSimulationFacade smartHome = OMOSmartHomeSimulationFacade.INSTANCE;

        //CHOOSE CONFIG
        smartHome.createSmallConfig();
//        smartHome.createLargeConfig();

        //SIMULATE
        smartHome.simulate();

        //GENERATE REPORTS
        smartHome.generateAllReports();

    }
}



