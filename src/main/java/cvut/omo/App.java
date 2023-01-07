package cvut.omo;

public class App {


    public static void main(String[] args) {

        OMOSmartHomeSimulationFacade smartHome = OMOSmartHomeSimulationFacade.INSTANCE;

        smartHome.createSmallConfig();

        smartHome.simulate();

        smartHome.generateAllReports();
    }
}



