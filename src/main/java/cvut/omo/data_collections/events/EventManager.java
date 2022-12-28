package cvut.omo.data_collections.events;

import cvut.omo.event.Event;

public class EventManager {


    public static void listenTo(Event event) {
      Thread thread = new Thread(() -> {
          try {
              event.solve();
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
      });
      thread.start();
      try {
          thread.join();
      }catch (InterruptedException e){
          e.printStackTrace();
      }
    }
}

