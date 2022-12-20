package cvut.omo;
import cvut.omo.device.HomeDevice;
import cvut.omo.data_collections.consumption.ConsumptionCollection;
import cvut.omo.home_structure.*;
import cvut.omo.home_structure.home_builder.SmartHomeBuilder;
import cvut.omo.home_structure.home_builder.SmartHomeBuilderDirector;

import java.io.IOException;

public class App {


    public static void main(String[] args) throws IOException {


        SmartHomeBuilderDirector.createSmallHomeConfiguration(SmartHomeBuilder.INSTANCE);
        Home home = SmartHomeBuilder.INSTANCE.getResult();




        int count = 0;
        for(Floor floor: home.getFloors()){
            for(Room room: floor.getRooms()){
                System.out.println(room.getRoomType());
                for(HomeDevice homeDevice: room.getHomeDevices()){
                    count++;
                }
            }
        }
        System.out.println(count);
//        System.out.println(ConsumptionAndUsageCollection.generateReport());
        System.out.println("___________________________________________________________");

        for(Floor floor: home.getFloors()){
            for(Room room: floor.getRooms()){
                for(int i = 0; i < 48; i ++) {
                    for (HomeDevice homeDevice : room.getHomeDevices()) {
                        homeDevice.clickOn();
                        homeDevice.update();
                    }
                }
            }
        }
        ConsumptionCollection.getInstance().generateReport();


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
