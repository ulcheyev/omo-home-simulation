package cvut.omo.entity.activity;

import cvut.omo.entity.Responsible;
import cvut.omo.event.Event;
import cvut.omo.home_structure.nulls.NullRoom;
import cvut.omo.home_structure.room_builder.Room;
import cvut.omo.home_structure.room_builder.RoomName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public abstract class Activity {

    protected ActivityType activityType;
    protected Room room = new NullRoom();
    protected boolean isExecuted = false;
    protected Responsible responsible;
    protected Event event;


    public Activity(Responsible responsible, Event event, ActivityType activityType) throws InterruptedException {

        this.event = event;
        this.activityType = activityType;
        this.responsible = responsible;

        event.addActivity(this);
        assignRoom();
    }

    public void execute() throws InterruptedException {
        responsible.lock();
        doWork(responsible);
        responsible.unlock();
        isExecuted = true;
        event.checkSolving();
    };

    protected abstract void doWork(Responsible responsible) throws InterruptedException;


    public  boolean isExecuted(){
        return isExecuted;
    }

    private void assignRoom() {

        if(checkIfActivityTypeContainsStubRoom()){
            return;
        }

        else if(checkIfActivityTypeContainsCommonRoom()){
            this.room = event.getRoom();
            return;
        }

        else if(checkResponsibleRoom()){
            this.room = responsible.getRoom();
            return;
        }

        else{
            this.room = responsible.getRoom();
        }

    }

    private boolean checkIfActivityTypeContainsCommonRoom() {
        return activityType.getRoomNames().contains(RoomName.COMMON);
    }

    private boolean checkIfActivityTypeContainsStubRoom(){
        return activityType.getRoomNames().contains(RoomName.STUB);
    }

    private boolean checkResponsibleRoom(){
        boolean res = false;
        if(!responsible.isNull()){
            res =  activityType
                    .getRoomNames()
                    .contains(responsible.getRoom().getRoomName());
        }
        return res;
    }

}
