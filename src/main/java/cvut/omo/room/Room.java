package cvut.omo.room;

import cvut.omo.device.HomeDevice;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    private Floor floor;
    private double square;
    private List<HomeDevice> homeDevices;
    private RoomType roomType;
    private List<Window> windows;

    public void addHomeDevice(HomeDevice homeDevice){
        homeDevices.add(homeDevice);
    }

    public void addWindow(){
        windows.add(new Window());
    }

    public void removeWindow(Window window){
        windows.add(window);
    }

    public void removeHomeDevice(HomeDevice homeDevice){
        homeDevices.remove(homeDevice);
    }

}
