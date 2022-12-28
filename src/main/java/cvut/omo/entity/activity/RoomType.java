package cvut.omo.entity.activity;

import cvut.omo.app_utils.Utils;
import cvut.omo.entity.person.FamilyRoleType;
import cvut.omo.home_structure.room_builder.RoomName;
import lombok.Getter;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static cvut.omo.home_structure.room_builder.RoomName.*;

public enum RoomType {

    CHILL(BEDROOM, CHILDRENS_ROOM),
    HOUSEHOLD(GARAGE, KITCHEN, HALL),
    PERSONAL_NEEDS(BATHROOM);

    RoomType(RoomName ... roomName){
        rooms.addAll(Arrays.asList(roomName));
    }

    @Getter
    private final List<RoomName> rooms = new ArrayList<>();

    public static List<RoomName> getAll(){
        List<RoomName> res = new ArrayList<>();
        res.addAll(CHILL.rooms);
        res.addAll(HOUSEHOLD.rooms);
        res.addAll(PERSONAL_NEEDS.rooms);
        return res;
    }
}
