package cvut.omo.entity.item.item;


import cvut.omo.entity.Responsible;
import cvut.omo.entity.Usable;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.Queue;
/**
 * Class represents item, which can be use.
 */
@Getter
@Setter
public abstract class Item implements Usable {

    private ItemState itemState = ItemState.FREE;

    private Queue<Responsible> listeners = new LinkedList<>();

    private Responsible currentResponsible;


    public Item() {
    }

    /**
     * Allows using this item. Change {@link #itemState}. Start new thread, which simulates using.
     * @param responsible {@link Responsible}, which will use this item.
     * @throws InterruptedException {@link Thread}
     */
    public synchronized void use(Responsible responsible) throws InterruptedException {
        currentResponsible = responsible;
        itemState = ItemState.IN_USE;
        Thread thread = new Thread(() ->
        {
            try {
                Thread.sleep(1000);
                release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        );
        thread.start();
    }


    /**
     * Release item after use. Change {@link #itemState}.
     */
    public synchronized void release() {
        itemState = ItemState.FREE;
        try {
            pollFirst().update();
        } catch (NullPointerException ignored) {
        }
    }

    /**
     * @return true, if item is free
     */
    public synchronized boolean isFree() {
        return itemState.equals(ItemState.FREE);
    }

    /**
     * Add {@link Responsible} to {@link #listeners} of this item.
     * @param responsible
     */
    public void attach(Responsible responsible) {
        listeners.add(responsible);
    }

    /**
     * Poll first {@link Responsible} from {@link #listeners}.
     * @return first {@link Responsible} from queue to use this item
     */
    public Responsible pollFirst(){
        return listeners.poll();
    }

    /**
     * Check, if responsible is already in {@link #listeners}.
     * @param responsible responsible to check
     * @return true, if responsible is in queue
     */
    public boolean alreadyListen(Responsible responsible){
        return listeners.contains(responsible);
    }

    /**
     *
     * @return current user ({@link Responsible}) of this item
     */
    public Responsible getCurrentUser(){
        return currentResponsible;
    }

}
