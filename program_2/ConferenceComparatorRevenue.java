/**
 *  ConferenceComparatorRevenue.java
 *
 *  @author   Philip Westrich
 *  @version  November 11, 2013
 *
 *  This is a comparator class for Conferences.
 *  It will compare the conferences by their revenue.
 */

import java.util.Comparator;

public class ConferenceComparatorRevenue implements Comparator<Conference>{
   
   /**
    *   Compares left to right, using the compareTo() method of left.
    *
    *   @param    left      Object for right to be compared against
    *   @param    right     Object for left to be compared to
    *
    *   @return   int       Negative if left < right <br>
    *                       Positive if left > right <br>
    *                       Zero if left = right
    */
   
   public int compare(Conference left, Conference right){
      
      //Wrap the velues in Doubles; they're Comparable
      Double leftRevenue = new Double(left.getRevenue());
      Double rightRevenue = new Double(right.getRevenue());
      
      //Compare the two, reversing so they sort correctly
      return (leftRevenue.compareTo(rightRevenue) * -1);
      
   }
   
   /**
    * Compares other objects to this one. <br>
    * All the method does is check if the incoming object 
    *    is an instanceof this one.
    *
    * @param   other    Object to compare to this one.
    *
    * @return  boolean  Result of comparison.
    */
   
   public boolean equals(Object other){
      
      return (other instanceof ConferenceComparatorRevenue);
      
   }
   
}
