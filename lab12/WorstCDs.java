
import java.util.ArrayList;

public class WorstCDs implements Command<CD> {

   private ArrayList<CD> list;
   
   private int worstRating;
   
   public WorstCDs(){
      
      list = new ArrayList<CD>();
      worstRating = 11;
      
   }
   
   public void execute(CD item){
      
      if (item.getRating() < worstRating){
         
         list = new ArrayList<CD>();
         list.add(item);
         worstRating = item.getRating();
         
      }
      
      else if (item.getRating() == worstRating){
         
         list.add(item);
         
      }
      
   }
   
   public ArrayList<CD> getWorstCDs(){
      
      ArrayList<CD> temp = list;
      list = new ArrayList<CD>();
      return temp;
      
   }

}
