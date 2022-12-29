package cvut.omo.usable.item;


import cvut.omo.usable.Usable;

public abstract class Item implements Usable {

    private ItemState itemState = ItemState.FREE;

    public Item() {}

    public void use(){
        itemState = ItemState.IN_USE;
    }

    public void release(){
        itemState = ItemState.FREE;
    }
}
