package cvut.omo.data_collections;

/**
 * Standard interface for iterators.
 */
public interface Iterator {

     /**
      * Returns the specified next object from structure.
      * Iterator implements that interface have to specify return type.
      * @return next object
      */
     Object next();

     /**
      * Returns is the iterator at the end of structure or not.
      * @return true, if the iterator is at the end of structure or not
      */
     boolean hasNext();
}
