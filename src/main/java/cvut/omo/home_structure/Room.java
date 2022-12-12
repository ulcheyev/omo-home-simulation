package cvut.omo.home_structure;

import cvut.omo.device.HomeDevice;
import cvut.omo.item.Item;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Room {

    private List<HomeDevice> homeDevices = new ArrayList<>();
    private List<Window> windows = new ArrayList<>();
    private List<Item> items= new ArrayList<>();

    private Floor floor;
    private RoomType roomType;

    public Room(Floor floor, RoomType roomType){
        this.floor = floor;
        this.roomType = roomType;
    }

    public void addHomeDevice(HomeDevice homeDevice){
        homeDevices.add(homeDevice);
        homeDevice.setRoom(this);
    }

    public void addWindow(){
        windows.add(new Window());
    }

    public void removeWindow(Window window){
        windows.remove(window);
    }

    public void removeHomeDevice(HomeDevice homeDevice){
        homeDevices.remove(homeDevice);
    }

    public void addItem(Item item){items.add(item);}

    public void removeItem(Item item){items.remove(item);}
}
