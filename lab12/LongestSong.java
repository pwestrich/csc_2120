
import java.util.ArrayList;

public class LongestSong implements Command<CD> {

   private ArrayList<Song> list;
   
   private int longestLength;
   
   public LongestSong(){
      
      list = new ArrayList<Song>();
      longestLength = 0;
      
   }
   
   public void execute(CD item){
      
      for (int i = 0; i < item.getNumberOfTracks(); i++){
         
         Song song = item.getSong(i);
         
         if (song.getLength() > longestLength){
            
            list = new ArrayList<Song>();
            list.add(song);
            longestLength = song.getLength();
            
         }
         
         else if (song.getLength() == longestLength){
            
            list.add(song);
            
         }
         
      }
      
   }
   
   public ArrayList<Song> getLongestSongs(){
      
      ArrayList<Song> temp = list;
      list = new ArrayList<Song>();
      return temp;
      
   }

}
