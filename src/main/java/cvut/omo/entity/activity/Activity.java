package cvut.omo.entity.activity;

import cvut.omo.app_utils.Utils;
import cvut.omo.data_collections.activity_events.SmartHomeEventCollection;
import cvut.omo.entity.Responsible;
import cvut.omo.event.Event;
import cvut.omo.home_structure.home_builder.Home;
import cvut.omo.home_structure.nulls.NullRoom;
import cvut.omo.home_structure.room_builder.Room;
import cvut.omo.home_structure.room_builder.RoomName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class represents activity in house.
 */
@Getter
@Setter
@NoArgsConstructor
public abstract class Activity {

    protected ActivityType activityType;
    protected Room room = new NullRoom();
    protected boolean isExecuted = false;
    protected Responsible responsible;
    protected Event event;

    /**
     * Constructor fot class.
     * Add this activity to activities in specified event.
     *
     * @param responsible  responsible for this activity
     * @param event        the event to which this activity relates
     * @param activityType {@link ActivityType} type of activity
     */
    public Activity(Responsible responsible, Event event, ActivityType activityType) {

        this.event = event;
        this.activityType = activityType;
        this.responsible = responsible;

        event.addActivity(this);
        assignRoom();
    }


    /**
     * Execute this activity.
     * Lock responsible for activity, then unlock.
     * If execute is success, activity executed {@link #isExecuted} flag is true.
     * Check event solving.
     */
    public void execute() {
        responsible.lock();
        checkResponsibleLocation();

        if (doWork(responsible)) {
            isExecuted = true;
        }

        responsible.unlock();
        event.checkSolving();
    }

    ;

    /**
     * Template method for concrete activities.
     *
     * @param responsible responsible for activity
     * @return true, if activity execution is successfully
     */
    protected abstract boolean doWork(Responsible responsible);


    /**
     * @return {returns true, if activity is executed}
     */
    public boolean isExecuted() {
        return isExecuted;
    }

    private void assignRoom() {

        if (checkIfActivityTypeContainsStubRoom()) {
            return;
        } else if (checkIfActivityTypeContainsCommonRoom()) {
            this.room = event.getRoom();
            return;
        } else if (checkResponsibleRoom()) {
            this.room = responsible.getRoom();
            return;
        } else {
            RoomName name = Utils.getRandomObjFromList(activityType.getRoomNames());
            this.room = Home.INSTANCE.searchRoomByType(name);
        }

    }

    private boolean checkIfActivityTypeContainsCommonRoom() {
        return activityType.getRoomNames().contains(RoomName.COMMON);
    }

    private boolean checkIfActivityTypeContainsStubRoom() {
        return activityType.getRoomNames().contains(RoomName.STUB);
    }

    private boolean checkResponsibleRoom() {
        boolean res = false;
        if (!responsible.isNull()) {
            res = activityType.getRoomNames().contains(responsible.getRoom().getRoomName());
        }
        return res;
    }

    /**
     * @return string representation of object
     */
    @Override
    public String toString() {

        String type = responsible.getResponsibleType().isNull() ? responsible.getClass().getSimpleName() : responsible.getResponsibleType().toString();

        Room location = room.isNull() ? responsible.getRoom() : room;

        return type + " in room " + location.getRoomName() + " on the " + location.getFloor().getNumberOfFloor() + " floor " + "did " + activityType.name();
    }

    private void checkResponsibleLocation() {
        if (!room.isNull() && !responsible.getRoom().equals(room)) {
            RelocateActivity relocateActivity = ActivityFactory.createRelocateActivity(responsible, event, room);
            relocateActivity.doWork(responsible);
            SmartHomeEventCollection.swapTwoEndActivities(event);
        }
    }
}
