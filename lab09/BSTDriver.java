import java.util.ArrayList;
import java.util.Iterator;

public class BSTDriver
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
             return; //look up exit later
             
        }
        
        BinarySearchTree tree = new BinarySearchTree();
        int size = cds.length;
        
        try {
        
             for (CD item : cds){ //Insert all items in array into tree
             
                  tree.insert(item);
             
             }
        
        }
        
        catch (TreeException it){
             
             System.out.println(it.getMessage());
             return;
             
        }
        
        try { //Try removing a few items
             
             System.out.println("Removing CDs...");
             
             System.out.println("retrieved: " + tree.retrieve(cds[size - 3].getKey()));
             tree.delete(cds[size - 3].getKey());
             
             System.out.println("retrieved: " + tree.retrieve(cds[0].getKey()));
             tree.delete(cds[0].getKey());
             
             System.out.println("retrieved: " + tree.retrieve(cds[size - 3].getKey()));
             tree.delete(cds[size - 3].getKey());
             
        }
        
        catch (TreeException it){
             
             System.out.println(it.getMessage());
             
        }
        
        try { //Try adding a few items
             
             System.out.println("Inserting CDs...");
             System.out.println("Inserting: " + cds[0].getKey());
             tree.insert(cds[0]);
             
             System.out.println("Inserting: " + cds[size - 3].getKey());
             tree.insert(cds[size - 3]);
             
             System.out.println("Inserting: " + cds[0].getKey());
             tree.insert(cds[0]);
             
        }
        
        catch (TreeException it){
             
             System.out.println(it.getMessage());
             
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
