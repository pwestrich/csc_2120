/**
 *    BurritoCondiment.java
 *
 *    for CSC 2120 Program #1
 *    "Decorated Burritos"
 *
 *    @author Philip Westrich
 *    @date   October 7, 2013
 *
 *   This is the abstract for a BurritoCondiment.
 */

package burrito;

public abstract class BurritoCondiment extends BurritoItem {
     
     /**
      *   The constructor when given another BurritoCondiment object.
      *   It must check if an instance of the same condiment already exists.
      *
      *   @param    item BurritoCondiment item or child.
      */
     
     public BurritoCondiment(BurritoCondiment item){
                    
          super(item);
          
     }
     
     /**
      *   The constructor when given a BurritoFiller object.
      *   Simply calls the parent constructor with the given item.
      *
      *   @param    item BurritoFiller
      */
     
     public BurritoCondiment(BurritoItem item){
          
          super(item);
          
     }
     
     /**
      *   Checks to see if it is a duplicate of a previous BurritoCondiment
      *
      *   @return   boolean, telling whether or not the object is a duplicate.
      */
     
     public boolean isDuplicate(){
          
          return super.isDuplicate(this);
          
     }
     
     /**
      *   The equals() method for each subclass. Must be implemented by children.
      *   
      *   @param bc      a Condiment object to be tested against the current
      *   @return        boolean, telling if is a duplicate or not.
      */
     
     public abstract boolean equals(BurritoCondiment bc);
     
     /**
      *   The toString() function.
      *
      *   @return   String, containing the entire previous order.
      */
     
     public String toString(){
          
          return super.toString() + "Condiment: ";
          
     }

}
