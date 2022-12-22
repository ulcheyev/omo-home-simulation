package cvut.omo;
import cvut.omo.app_utils.FileWriter;
import cvut.omo.device.HomeDevice;
import cvut.omo.data_collections.consumption.ConsumptionCollection;
import cvut.omo.entity.person.Person;
import cvut.omo.event.Event;
import cvut.omo.event.EventGenerator;
import cvut.omo.home_structure.*;
import cvut.omo.home_structure.home_builder.SmartHomeBuilder;
import cvut.omo.home_structure.home_builder.SmartHomeBuilderDirector;

import java.io.IOException;

public class App {


    public static void main(String[] args) throws IOException {


        SmartHomeBuilderDirector.createLargeHomeConfiguration(SmartHomeBuilder.INSTANCE);
        Home home = SmartHomeBuilder.INSTANCE.getResult();


        int count = 0;
        for(Floor floor: home.getFloors()){
            for(Room room: floor.getRooms()){
                EventGenerator.generateRandomHomeEvent(room);
                System.out.println(room.getRoomType());
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

                for(Person person: room.getPersons()){
                    EventGenerator.generateRandomPersonEvent(person);
                    System.out.println(person.getName());
                }

                for (Event event: room.getEvents()){
                    System.out.println(event.getEventType());
                }
                System.out.println("___________________________________________________________");
                room.update();
                System.out.println("___________________________________________________________");


                for(HomeDevice homeDevice: room.getHomeDevices()){
                    count++;
                }
            }
        }
//        System.out.println(count);
//        System.out.println(ConsumptionAndUsageCollection.generateReport());
//        System.out.println("___________________________________________________________");

//        for(Floor floor: home.getFloors()){
//            for(Room room: floor.getRooms()){
//                for(int i = 0; i < 48; i ++) {
//                    for (HomeDevice homeDevice : room.getHomeDevices()) {
//                        homeDevice.update();
////                        homeDevice.breakDevice();
////                        homeDevice.getDocumentation();
//                        homeDevice.clickOn();
//                    }
//                }
//            }
//        }
        ConsumptionCollection.getInstance().generateReport();
        FileWriter.cleanDirectory("documentation/");


//
//        for(Person person: home.getPersons()){
//            System.out.println(person.getName());
//        }

//        System.out.println(count);



//        SmartHomeBuilderDirector.createLargeHomeConfiguration(SmartHomeBuilder.INSTANCE);
//        home = SmartHomeBuilder.INSTANCE.getResult();
//
//       count = 0;
//        for(Floor floor: home.getFloors()){
//            for(Room room: floor.getRooms()){
//                System.out.println(room.getRoomType());
//                for(HomeDevice homeDevice: room.getHomeDevices()){
//                    count++;
//                }
//            }
//        }
//
//        for(Person person: home.getPersons()){
//            System.out.println(person.getName());
//        }
//
//        System.out.println(count);
    }


}
