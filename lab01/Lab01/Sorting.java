
public class Sorting
{

  /*

     evaluate the sum i=1 to i=(n-1) of i
     (n-1) + (n-2) + ... + 1
     1     + 2     + ... + (n-1)
     ---------------------------
     n     + n     + ... + n      = n*(n-1)  so the original sum is n*(n-1)/2

  */

   /**
    * Sorts the specified array of comparable objects using the selection
    * sort algorithm.
    */
   public static Comparable[] selectionSort(Comparable[] sort) //convenience overloaded method
   {
      int n = sort.length;
      Comparable[] temp = new Comparable[n];
      for (int i = 0; i < n; i++)
      {
         temp[i] = sort[i];
      }

      selectionSort(temp, n);
      return temp;
   }

   public static void selectionSort (Comparable[] sort, int n)
   {
      int min;
      Comparable temp;

      if (n > sort.length || n <= 0)
      {
         n = sort.length;
      }

      for (int index = 0; index < n - 1; index++)
      {
         min = index;
         for (int scan = index + 1; scan < n; scan++)
         {
            if (sort[scan].compareTo(sort[min]) < 0)
            {
		min = scan;
            }
         }

         // Swap the values
         temp = sort[min];
         sort[min] = sort[index];
         sort[index] = temp;
      }
   }

   /**
    * Sorts the specified array of comparable objects using the insertion
    * sort algorithm.
    */
   public static Comparable[] insertionSort(Comparable[] sort) //convenience overloaded method
   {
      int n = sort.length;
      Comparable[] temp = new Comparable[n];
      for (int i = 0; i < n; i++)
      {
         temp[i] = sort[i];
      }

      insertionSort(temp, n);
      return temp;
   }

   public static void insertionSort (Comparable[] sort, int n)
   {
      Comparable temp;
      int position;

      if (n > sort.length || n <= 0)
      {
         n = sort.length;
      }

      for (int index = 1; index < n; index++)
      {
         temp = sort[index];
         position = index;

         // shift larger values to the right
         while (position > 0 && sort[position-1].compareTo(temp) > 0)
         {
            sort[position] = sort[position-1];
            position--;
         }
            
         sort[position] = temp;
      }
   }

   /**
    *  Sorts the items in an array into ascending order.
    *  Precondition: sort is an array of n items.
    *  Postcondition: sort is sorted into ascending 
    *  order.
    */
   public static Comparable[] bubbleSort(Comparable[] sort) //convenience overloaded method
   {
      int n = sort.length;
      Comparable[] temp = new Comparable[n];
      for (int i = 0; i < n; i++)
      {
         temp[i] = sort[i];
      }

      bubbleSort(temp, n);
      return temp;
   }

   public static void bubbleSort(Comparable[] sort, int n) 
   {
      if (n > sort.length || n <= 0)
      {
         n = sort.length;
      }

      boolean sorted = false;  // false when swaps occur
      for (int pass = 1; (pass < n) && !sorted; pass++) 
      {
         sorted = true;  // assume sorted
         for (int index = 0; index < n - pass; index++) 
         {
            if (sort[index].compareTo(sort[index + 1]) > 0) 
            {
               // exchange items
               Comparable temp = sort[index];
               sort[index] = sort[index + 1];
               sort[index + 1] = temp;
               sorted = false;  // signal that an exchange occured
            }  
         }  
      } 
   }

   public static Comparable[] mergeSort(Comparable[] sort)
   {
      int n = sort.length;
      Comparable[] temp = new Comparable[n];
      for (int i = 0; i < n; i++)
      {
         temp[i] = sort[i];
      }

      mergeSort(temp, 0, n - 1);
      return temp;
   }

   public static void mergeSort(Comparable[] sort, int n)
   {
      if (n > sort.length || n <= 0)
      {
         n = sort.length;
      }

      mergeSort(sort, 0, n - 1);
   }

   /** 
    * Sorts the items in an array into ascending order. 
    * Precondition: sort[first..last] is an array.
    * Postcondition: sort[first..last] is sorted in 
    * ascending order.
    * Calls: merge.
    */
   private static void mergeSort(Comparable[] sort, int first, int last) 
   {
      if (first < last) 
      {
         // sort each half
         int mid = (first + last)/2;   // index of midpoint

         // sort left half sort[first..mid]
         mergeSort(sort, first, mid);

         // sort right half sort[mid + 1..last]   
         mergeSort(sort, mid + 1, last);  
 
         // merge the two halves
         merge(sort, first, mid, last);
      } 
   } 

   /** 
    *  Merges two sorted array segments sort[first..mid] and 
    *  sort[mid+1..last] into one sorted array.
    *  Precondition: first <= mid <= last. The subarrays 
    *  sort[first..mid] and sort[mid+1..last] are 
    *  each sorted in increasing order.
    *  Postcondition: sort[first..last] is sorted.
    *  Implementation note: This method merges the two
    *  subarrays into a temporary array and copies the result
    *  into the original array anArray.
    */
   private static void merge(Comparable[] sort, int first, int mid, int last) 
   {
     //DO THIS
     //create the temporary array
     //change required here (think about how large the temp array needs to be to get the job done)
     Comparable[] temp = new Comparable[last - first + 1];
     //Comparable[] temp = new Comparable[sort.length]; 
    
     // initialize the local indexes to indicate the subarrays
     int first1 = first;    // beginning of first subarray
     int last1  = mid;      // end of first subarray
     int first2 = mid + 1;  // beginning of second subarray
     int last2  = last;     // end of second subarray
     // while both subarrays are not empty, copy the
     // smaller item into the temporary array

     //DO THIS
     //change required here
     //int index = first1;    // next available location in 
     int index=0;                       // tempArray

     while ((first1 <= last1) && (first2 <= last2)) 
     {
       if (sort[first1].compareTo(sort[first2])<=0)  //careful here for stable sorting
       {
         temp[index] = sort[first1];
         first1++;
       }
       else 
       {
         temp[index] = sort[first2];
         first2++;
       }  

       index++;
     } 

     // finish off the nonempty subarray

     // finish off the first subarray, if necessary
     while (first1 <= last1) 
     {
       temp[index] = sort[first1];
       first1++;
       index++;
     }  

     // finish off the second subarray, if necessary
     while (first2 <= last2)
     {
       temp[index] = sort[first2];
       first2++;
       index++;
     }  

     //DO THIS
     // copy the result back into the original array
     //this for loop must be changed
     for (index = 0; index <= last - first; index++) 
     {
       sort[index + first] = temp[index];
     }  
     /*
     for (index = first; index <= last; index++) 
     {
       sort[index] = temp[index];
     }  
     */
   }  

   public static Comparable[] quickSort(Comparable[] sort)
   {
      int n = sort.length;
      Comparable[] temp = new Comparable[n];
      for (int i = 0; i < n; i++)
      {
         temp[i] = sort[i];
      }

      quickSort(temp, 0, n - 1);
      return temp;
   }

   public static void quickSort(Comparable[] sort, int n)
   {
      if (n > sort.length || n <= 0)
      {
         n = sort.length;
      }

      quickSort(sort, 0, n - 1);
   }

   /** 
    * Sorts the items in an array into ascending order.
    * Precondition: sort[first..last] is an array.
    * Postcondition: sort[first..last] is sorted.
    * Calls: partition.
    */
   private static void quickSort(Comparable[] sort, int first, int last) 
   {
      int pivotIndex;

      if (first < last)
      {
        // create the partition: S1, Pivot, S2
        pivotIndex = partition(sort, first, last);

        // sort regions S1 and S2
        quickSort(sort, first, pivotIndex - 1);
        quickSort(sort, pivotIndex + 1, last);
      }  
   } 

   /** 
    * Partitions an array for quicksort.
    * Precondition: sort[first..last] is an array; 
    * first <= last.
    * Postcondition: Returns the index of the pivot element of
    * sort[first..last]. Upon completion of the method, 
    * this will be the index value lastS1 such that
    *    S1 = sort[first..lastS1-1] <  pivot
    *         sort[lastS1]          == pivot
    *    S2 = sort[lastS1+1..last]  >= pivot
    * Calls: choosePivot.
    */
   private static int partition(Comparable[] sort, int first, int last) 
   {
     // tempItem is used to swap elements in the array
     Comparable temp; 
     // place pivot in sort[first]             
     choosePivot(sort, first, last);      
     Comparable pivot = sort[first];   // reference pivot

     // initially, everything but pivot is in unknown
     int lastS1 = first;          // index of last item in S1

     // move one item at a time until unknown region is empty

     for (int unknown = first + 1; unknown <= last; unknown++) 
     {

       // move item from unknown to proper region
       if (sort[unknown].compareTo(pivot) < 0)  
       {
         // item from unknown belongs in S1
         lastS1++;
         temp = sort[unknown];
         sort[unknown] = sort[lastS1];
         sort[lastS1] = temp;
       } 
     // else item from unknown belongs in S2
     } 

     // place pivot in proper position and mark its location
     temp = sort[first];
     sort[first] = sort[lastS1];
     sort[lastS1] = temp;
     return lastS1;
   } 

   /** 
    * Chooses a pivot for quicksort's partition algorithm and 
    * swaps it with the first item in an array.
    * Precondition: sort[first..last] is an array; 
    * first <= last.
    * Postcondition: sort[first] is the pivot.
    */
   private static void choosePivot(Comparable[] sort, int first, int last) 
   {

     //DO THIS
     //select a better choice for a pivot, and move it to the first index
     // Implementation left as an exercise.

     int mid = (first + last)/2;
     Comparable temp = sort[first];

     sort[first] = sort[mid];
     sort[mid] = temp;   //simply swap first and mid to take care of worst case for quicksort
     
   } 

   public static Comparable[] shellSort(Comparable[] sort) //convenience overloaded method
   {
      int n = sort.length;
      Comparable[] temp = new Comparable[n];
      for (int i = 0; i < n; i++)
      {
         temp[i] = sort[i];
      }

      shellSort(temp, n);
      return temp;

   }

   public static void shellSort(Comparable[] sort, int n) 
   {
      int loc;
      Comparable nextItem;
      for (int h = n/2; h > 0; h = h/2) 
      {
        for (int unsorted = h; unsorted < n; unsorted++) 
        {
          nextItem = sort[unsorted];
          loc = unsorted;
          while ( (loc >= h) && (sort[loc - h].compareTo(nextItem) > 0) ) 
          {
            sort[loc] = sort[loc - h];
            loc = loc - h;
          }  
          sort[loc] = nextItem;
       }  
     }
   }

   public static Comparable[] insertion2Sort(Comparable[] sort) //convenience overloaded method
   {
      int n = sort.length;
      Comparable[] temp = new Comparable[n];
      for (int i = 0; i < n; i++)
      {
         temp[i] = sort[i];
      }

      insertion2Sort(temp, n);
      return temp;
   }

   public static void insertion2Sort (Comparable[] sort, int n)
   {
      Comparable temp;
      int position;

      if (n>sort.length || n<=0)
      {
         n = sort.length;
      }

      for (int index = 1; index < n; index++)
      {
         //DO THIS
         //obtain the new object uncovered by insertion sort
         temp = sort[index];

         //shift larger values to the right
         //obtain the insertion location using a binary search (insertLocation method below)
         position = insertLocation(sort,temp,index-1);  //binary search to minimize comparisons

         //shift items to make room for the item to be inserted (use a for loop)
         for(int i = index; i > position; i--)
         {
            sort[i] = sort[i-1];
         }
            
         sort[position] = temp;
      }
   }


   private static int insertLocation(Comparable[] sort, Comparable temp, int last)
   {

     int first = 0;
     int mid = (last + first)/2;

     while (first <= last)
     {
        if (temp.compareTo(sort[mid]) == 0)
        {
           break;
        }
        else if (temp.compareTo(sort[mid]) < 0)
        {
           first = first;
           last = mid - 1;
           mid = (last + first)/2;
        }
        else if (temp.compareTo(sort[mid]) > 0)
        {
           first = mid + 1;
           last = last;
           mid = (last + first)/2;
        }
     }

     if (last >= 0)
     {
        mid++;
     }

     return mid;
   }

}