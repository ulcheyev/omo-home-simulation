package cvut.omo.usable.item;


import cvut.omo.entity.Responsible;
import cvut.omo.usable.Usable;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.Queue;

@Getter
@Setter
public abstract class Item implements Usable {

    private ItemState itemState = ItemState.FREE;
    private Queue<Responsible> listeners =  new LinkedList<>();

    private Responsible currentResponsible;

    public Item() {}

    public synchronized void use(Responsible responsible) throws InterruptedException {
        currentResponsible = responsible;
       itemState = ItemState.IN_USE;
       Thread thread = new Thread(()->
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


    public synchronized void release(){
        itemState = ItemState.FREE;
        try {
            detachFirst().update();
        }
        catch (NullPointerException ignored){}
    }

    public synchronized boolean isFree(){
        return itemState.equals(ItemState.FREE);
    }

    public void attach(Responsible responsible){
        listeners.add(responsible);
    }

    public Responsible detachFirst(){
        return listeners.poll();
    }

    public boolean alreadyListen(Responsible responsible){
        return listeners.contains(responsible);
    }

    public Responsible getCurrentUser(){
        return currentResponsible;
    }

}
