
/**
 *  Utility class to provide sorting algorithms and simple date invalidation.
 */

import java.util.Comparator;

public class Utilities {
   
   /**
    * Sorts an array of items with the given Comparator 
    * using the BubbleSort algorithm.
    *
    * @param   items    Items to be sorted
    * @param   comparer Object to compare items
    */
   
   public static <T> void bubbleSort(T[] items, Comparator<T> comparer){
      
      if (items != null && comparer != null){
         
         bubbleSort(items, comparer, items.length);
         
      }
      
   }
   
   /**
    * Sorts the given array with the given Comparator 
    * using the bubbleSort algorithm.
    *
    * @param   items       Array of items to be sorted
    * @param   comparer    Object to do the comparing
    * @param   numValues   Number of items in the array to sort
    */
   
   private static <T> void bubbleSort(T[] items, Comparator<T> comparer,
                                      int numValues){
      
      int current = 0;
      boolean sorted = false;
      
      while ((current < (numValues - 1)) && !sorted){
         
         bubbleUp(items, current, numValues - 1, sorted, comparer);
         current++;
         
      }
      
   }
   
   /**
    * Bubbles values up as needed.
    *
    * @param   items       Array of items to be sorted
    * @param   startIndex  Index to start at
    * @param   endIndex    Index to end at
    * @param   sorted      Is the array sorted for the indecies given?
    * @param   comparer    Object to compare the items in the array
    */
   
   private static <T> void bubbleUp(T[] items, int startIndex, int endIndex,
                                    boolean sorted, Comparator<T> comparer){
      
      sorted = true;
      
      for (int i = endIndex; i > startIndex; i--){
         
         if (comparer.compare(items[i], items[i - 1]) < 0){
         
            swap(items[i], items[i - 1]);
            sorted = false;
            
         }
         
      }
      
   }

   /**
    * Sorts the given array with the given Comparator.
    *
    * @param   items    Array to be sorted.
    * @param   comparer Object to compare items
    */
   
   public static <T> void selectionSort(T[] items, Comparator<T> comparer){
        
      if (items != null && comparer != null) {
         
           selectionSort(items, items.length, comparer);
           
      }
        
   }

   /**
    * Actually sorts the array of items.
    *
    * @param   items       Array of items to be sorted.
    * @param   numValues   Number of items in the array.
    * @param   comparer    Object to compare items.
    */
     
   private static <T> void selectionSort (T[] items, int numValues,
                                          Comparator<T> comparer){
      
      int endIndex = numValues - 1;
      
      for (int i = 0; i < endIndex; i++){
         
         swap(items[i], items[minIndex(items, i, endIndex, comparer)]);
         
      }
        
   }
   
   /**
    * Finds the index of the minimum items in the array within the given bounds.
    *
    * @param   items       Array of items to look in
    * @param   startIndex  Index to start searching at
    * @param   endIndex    Index to stop seatching at
    * @param   comparer    Object to compare the items
    *
    * @return  int         The index of the minimum item within the given bounds.
    */
   
   private static <T> int minIndex(T[] items, int startIndex, int endIndex,
                                   Comparator<T> comparer){
      
      int indexOfMin = startIndex;
      
      for (int i = startIndex + 1; i <= endIndex; i++){
         
         if (comparer.compare(items[i], items[indexOfMin]) < 0){
            
            indexOfMin = i;
            
         }
         
      }
      
      return indexOfMin;
      
   }
     
   /**
    * Sorts the given array with the object given.
    *
    * @param   items    Arary of items to be sorted
    * @param   comparer Object to compare items with
    */
     
   public static <T> void insertionSort(T[] items, Comparator<T> comparer) {
        
        if (items != null && comparer != null) {
             
             insertionSort(items, items.length, comparer);
             
        }
        
   }
   
   /**
    * Actually does the work for sorting the items.
    *
    * @param   items       Array of items to sort
    * @param   numValues   Number of items in the array
    * @param   comparer    Object to compare the items
    */
     
   private static <T> void insertionSort (T[] items, int numValues,
                                          Comparator<T> comparer) {
        
      for (int i = 0; 0 < numValues; i++){
         
         insertItem(items, 0, i, comparer);
         
      }
        
   }
   
   /**
    * Inserts the correct item into the array of items.
    *
    * @param   items       Array of items to be sorted
    * @param   startIndex  Index to start at
    * @param   endInedx    Index to end at
    * @param   comparer    Object to compare the items
    */
   
   private static <T> void insertItem(T[] items, int startIndex, int endIndex,
                                      Comparator<T> comparer){
      
      boolean finished = false;
      int current = endIndex;
      boolean moreToSearch = (current != startIndex);
      
      while (moreToSearch && !finished){
         
         if (comparer.compare(items[current], items[current - 1]) < 0){
            
            swap(items[current], items[current - 1]);
            current--;
            moreToSearch = (current != startIndex);
            
         }
         
         else finished = true;
         
      }
      
   }
   
   /**
    * Swaps the two items given with each other.
    *
    * @param   left  Item #1
    * @param   right Item #2
    */
   
   private static <T> void swap(T left, T right){
      
      T temp = left;
      left = right;
      right = temp;
      
   }
   
     /**
      *   Checks the given date if it is valid or not. A valid date must be in the form
      *   yy/mm/dd, integers, between 0 and 99, 01 and 12, and 01 and the last day of 
      *   the month, respectivley.
      *
      *   @param    date Date to be checked
      *
      *   @return   Whether or not the date is invalid.
      */
     
     public static boolean invalidDate(String date){
          
          int[] data = getDateInfo(date); //Get info from string
                                          //0: year, 1: month, 2: day
          
          try {
               
             if (data[0] < 0 || data[0] > 99) return true; //check year
             
             if (data[2] < 1 || data[2] > 12) return true; //check month
             
             switch(data[1]){ //check for the correct # of days in the month
                   
                case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                   
                   return (data[2] > 31);
                   
                case 4: case 6: case 9: case 11:
                   
                   return (data[2] > 30);
                   
                case 2: //February
                   
                   //assuming no leap years, because the rules for that
                   //are way too complicated
                   return (data[2] > 28);
                   
                default:
                   
                   return false; //if we've made it this far, the date is good
                   
             }
                            
          }
          
          catch (Exception it){ //If any exception is caught, date is invalid
               
               return true;
               
          }
          
     }
     
     /**
      *   Checks to see if start is before end or not.
      *
      *   @param    start     Start date
      *   @param    end       End date
      *
      *   @return   boolean   Whether or not dates are valid
      */
     
     public static boolean datesInvalid(String start, String end){
          
          if (invalidDate(start) || invalidDate(end)){
               
               return true;
               
          }
          
          //Get numeric info from string
          int[] startInfo = getDateInfo(start);
          int[] endInfo = getDateInfo(end);
          
          try { //Try to compare it
               
               //Compare years
               if (startInfo[0] > endInfo[0]) return true;
               
               //Compare months
               if (startInfo[1] > endInfo[1]) return true;
               
               //Compare days
               if (startInfo[2] > endInfo[2]) return true;
               
               //If none of the statements return, date is good
               return false;
               
          }
          
          catch (Exception it){
               
               //If exception is generated, dates are bad
               return true;
               
          }
          
     }
     
     /**
      *   Splits the string with the date info into its number parts.
      *
      *   @param    date      String with date in it
      *
      *   @return   int[]     Array of data, with indexes from 0 to 3, <br>
      *                       or null if bad data was given
      */
     
     private static int[] getDateInfo(String date){
          
          try { //Try to split string
          
               String[] data = date.split("/");
             int[] info = new int[3];
          
               info[0] = Integer.parseInt(data[0]);
               info[1] = Integer.parseInt(data[1]);
               info[2] = Integer.parseInt(data[2]);
               
               return info;
               
          }
          
          catch (Exception it){ //If any exception is generated, data is bad
               
               return null;
               
          }
          
     }

}
