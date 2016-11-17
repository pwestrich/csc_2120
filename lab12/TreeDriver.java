import java.util.ArrayList;
import java.util.Iterator;

public class TreeDriver
{
   public static void main(String[] args)
   {
      //call the height and isBalanced methods and display the results with all items inserted
        
        CD[] cds = null;
        
        try {
             
             cds = readMusic(args[0]); //Read file with string passed in
             
        }
        
        catch (FileIOException it){
             
             System.out.println(it.getMessage());
             return; //look up exit later?
             
        }
        
        //BinarySearchTree tree = new BinarySearchTree();
        AVLTree tree = new AVLTree(); //test AVLTree
        int size = cds.length;
        
        try {
        
             for (CD item : cds){ //Insert all items in array into tree
             
                tree.insert(item); //should work
             
             }
        
        }
        
        catch (TreeException it){
             
             System.out.println(it.getMessage());
             return;
             
        }
        
      WorstCDs worst = new WorstCDs();
      tree.execute(worst);
      ArrayList<CD> worstList = worst.getWorstCDs();
      
      System.out.println("Printing worst CDs...");
      
      for (CD item : worstList){
         
         System.out.println(item);
         
      }
      
      LongestSong longest = new LongestSong();
      tree.execute(longest);
      ArrayList<Song> longList = longest.getLongestSongs();
      
      System.out.println("Printing longest songs...");
      
      for (Song item : longList){
         
         System.out.println(item);
         
      }
      
   }

   private static CD[] readMusic(String fileName)
   {
      FileIO file = new FileIO(fileName, FileIO.FOR_READING);
      String str = file.readLine();
      ArrayList<CD> cds = new ArrayList<CD>();
      while (!file.EOF())
      {
         String title = file.readLine();
         int year = Integer.parseInt(file.readLine());
         int rating = Integer.parseInt(file.readLine());
         int numTracks = Integer.parseInt(file.readLine());
         CD cd = new CD(title, str, year, rating, numTracks);

         cds.add(cd);
         int tracks = 1;

         while (tracks <= numTracks)
         {
            String temp = file.readLine();
            String[] line = temp.split(",");
            String len = line[0];
            String songTitle = line[1];
            cd.addSong(songTitle, len);
            tracks++;
         }

         str = file.readLine();
      }

      CD[] cds_array = new CD[cds.size()];
      int i = 0;
      for(CD cd : cds)
      {
         cds_array[i] = cds.get(i);
         i++;
      }
      return cds_array;
   }
}
