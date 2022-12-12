package cvut.omo;
import cvut.omo.device.HomeDevice;
import cvut.omo.device.device_data_structure.ConsumptionAndUsageDataStructure;
import cvut.omo.entity.person.Person;
import cvut.omo.home_structure.*;
import cvut.omo.home_structure.home_builder.SmartHomeBuilder;
import cvut.omo.home_structure.home_builder.SmartHomeBuilderDirector;
import cvut.omo.home_structure.room_builder.SmartHomeRoomBuilder;
import cvut.omo.home_structure.room_builder.SmartHomeRoomBuilderDirector;
import org.checkerframework.checker.units.qual.C;

public class App {


    public static void main(String[] args) {




        SmartHomeBuilderDirector.createSmallHomeConfiguration(SmartHomeBuilder.INSTANCE);
        Home home = SmartHomeBuilder.INSTANCE.getResult();

        ConsumptionAndUsageDataStructure data = new ConsumptionAndUsageDataStructure(home.getHomeDevices());



//        int count = 0;
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

        System.out.println(data.generateReport());
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
