
package ki;

public abstract class KeyedItem {

     Comparable key;
     
     public KeyedItem(Comparable newKey){
          
          key = newKey;
          
     }
     
     public Comparable getKey(){
          
          return key;
          
     }

}
