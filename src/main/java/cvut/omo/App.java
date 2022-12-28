package cvut.omo;
import cvut.omo.data_collections.events.EventCollection;
import cvut.omo.event.EventGenerator;
import cvut.omo.home_structure.home_builder.Home;
import cvut.omo.home_structure.home_builder.SmartHomeBuilder;
import cvut.omo.home_structure.home_builder.SmartHomeBuilderDirector;

import java.io.IOException;

public class App {


    public static void main(String[] args) throws IOException, InterruptedException {


        SmartHomeBuilderDirector.createLargeHomeConfiguration(SmartHomeBuilder.INSTANCE);
        Home home = SmartHomeBuilder.INSTANCE.getResult();



        //GENERATE EVENTS FOR PERSONS, HOMEDEVICES AND ROOMS
        EventGenerator.generateRandomEvents(15);
        EventCollection.generateReport();

        while(!EventCollection.allSolved()){
            home.update();
        }



//        Thread.sleep(500);

//        home.testRep2();

    }
}
//            for(Room room: floor.getRooms()){
//                System.out.println("++++++++++++++++++++++++++ROOM++++++++++++++++++++++++");
//                System.out.println(room.getRoomName() + " on floor " + room.getFloor().getNumberOfFloor());
//                System.out.println("******************PERSONS IN ROOM*********************");
//                for(Person person: room.getPersons()){
//                    System.out.println(person.getName() + " " + person.getResponsibleType());
//                }
//                System.out.println("******************************************************");
//                System.out.println("******************DEVICES IN ROOM*********************");
//                for(HomeDevice device: room.getHomeDevices()){
//                    System.out.println(device.getClass().getSimpleName());
//                }
//                System.out.println("******************************************************");
//                for(Person person2: room.getPersons()){
//                    System.out.println("******************PERSONS EVENTS**********************");
//                    for(Event event: person2.getSelfEvents()){
//                        System.out.print(person2.getName() + " has event: " +  event.getEventType() + "|");
//                        for(ActivityType activityType: event.getEventType().getChainToSolve()){
//                            System.out.print("-> " + activityType);
//                        }
//                        System.out.println();
//                    }
//
//                    System.out.println("******************************************************");
//                }
//                System.out.println("******************HOMEDEVICES EVENTS**********************");
//                for(HomeDevice homeDevice: room.getHomeDevices()){
//                    for(Event event: ((HomeAppliances)homeDevice).getSelfEvents()){
//                        System.out.print(homeDevice.getClass() + " has event: " + event.getEventType() + "|");
//                        for(ActivityType activityType: event.getEventType().getChainToSolve()){
//                            System.out.print("->" + activityType);
//                        }
//                        System.out.println();
//                    }
//                }
//                System.out.println("******************************************************");
//
//                System.out.println("******************ROOM EVENTS**********************");
//                for(Event event: room.getEvents()){
//                    System.out.println(event.getEventType());
//                }
//            }
//        }
//        }

//        ConsumptionCollection.getInstance().generateReport();
//        HomeConfigurationReportVisitor visitor = new HomeConfigurationReportVisitor();
//        visitor.generateReport();


