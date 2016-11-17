import java.util.Random;
import java.awt.Color;
import plot.*;
//add import statement

public class SortTime
{

   private static DataPlot graph;
   private static Comparable[] original;
   private static Comparable[] originalSorted;
   private Comparable[] sorted;
   private Plot plot;

   public void doTask(Comparable[] sort, int n, String choice)
   {
      //here is where we call the various sorting techniques
      //explain why the parameter lists appear as they do
      if (choice.equals("Selection"))
      {
         Sorting.selectionSort(sort,n);
      }
      else if (choice.equals("Insertion"))
      {
         Sorting.insertionSort(sort,n);
      }
      else if (choice.equals("Bubble"))
      {
         Sorting.bubbleSort(sort,n);
      }
      else if (choice.equals("Merge"))
      {
         Sorting.mergeSort(sort,n);
      }
      else if (choice.equals("Quick"))
      {
         Sorting.quickSort(sort,n);
      }
      //DO THIS add shell sort here
      else if (choice.equals("Shell"))
      {
         Sorting.shellSort(sort,n);
      }
      else if (choice.equals("Insertion 2"))
      {
         Sorting.insertion2Sort(sort,n);
      }

   }

   public void go (int start, int stop, int step, String title, Color color)
   {
      //creates a new set of data with the specified color
      DataList data = new DataList(title, Color.white, color, 6);
      plot.add(data);

      //start over with an unsorted array, but the number of items to sort will increase
      for (int i = start; i < stop; i += step)  
      {
         //start again with an unsorted array
         for (int x = 0; x < original.length; x++)
         {
            //DO THIS (when you get to quick sort)
            //switch the comments below to test quicksort worst case
            //REMEMBER to switch back when finished with the corresponding lab part

            sorted[x] = original[x];
            //sorted[x] = originalSorted[x];
         }

         //try to garbage collect the old array that is no longer being used
         System.gc();

         //here is the actual timing calculation
         long startTime = System.currentTimeMillis();
         doTask(sorted, i, title);
         long stopTime = System.currentTimeMillis();
         int time = (int) (stopTime - startTime);

         //add a point to the data set
         data.addPoint(i, time);

         //if we exceed the boundaries of the graph, reset Graph scale
         int ymax = plot.getYMAX();
         if (time > ymax)
         {
            plot.setYMAX(ymax + time/2);
         }

         //show the updated graph
         graph.repaint();
      }

   }

   public SortTime (int xAxis, int yAxis, String graphTitle)
   {
      plot = new Plot(0, xAxis, 0, yAxis);

      //create the graph object with the specified x-axis, y-axis, and title
      graph = new DataPlot(xAxis, yAxis, graphTitle, plot);
      Random rand = new Random();
 
      //create an array of CDs that have random titles for sorting
      original = new CD[xAxis];
      originalSorted = new CD[xAxis];
      sorted = new CD[xAxis];

      for (int y = 0; y < original.length; y++)
      {
         String title = "";
         for (int x = 0; x < 5; x++)
         {
            char theChar;
            int temp = Math.abs(rand.nextInt()) % 36;
            if (temp < 10)
            {
               theChar = (char) (temp + 48);
            }
            else
            {
               theChar = (char) (temp + 87);
            }

            title = title + theChar;
         }
         original[y] = new CD(title, "Dark Tranquillity", 12.99, 11);
      }

      for (int y = 0; y < original.length; y++)
      {
         originalSorted[y] = original[y];
      }

      //need a sorted version to test quicksort in the worst case
      Sorting.quickSort((Comparable[])originalSorted, original.length-1);
   }

   public static void main(String[] args)
   {

      int xRange = 20000;  //maximum number of items to search through
      int yRange = 2000;  //milliseconds
      int stepSize = 100;  //searching increment

      //set up the graph with the x-range, y-range, and title
      //the x-axis is number of items to sort, and the y-axis is time
      SortTime timer = new SortTime(xRange, yRange, "Sorting Times");

      //run simulations for various sorting techinques 

      //starting number of items to sort, ending number of items to sort, 
      //step size for simulation (sort 1, then 101, etc.), 
      //title of set of points, color of set of points
      //timer.go(100, 3100, stepSize, "Selection", Color.blue);
      timer.go(100, 4300, stepSize, "Insertion", Color.red);
      //timer.go(100, 3000, stepSize, "Bubble", new Color(220, 138, 8));  //orange

      timer.go(100, 20000, stepSize, "Merge", Color.green);
      timer.go(100, 20000, stepSize, "Quick", Color.black);

      //DO THIS
      //timer.go(100, 19500, stepSize, "Shell", new Color(255, 0, 255));         //purple
      timer.go(100, 19500, stepSize, "Insertion 2", new Color(9, 15, 140));    //dark blue
   }
  
}


