/**
 *  ConferenceComparatorAttendance.java
 *
 *  @author   Philip Westrich
 *  @version  November 11, 2013
 *
 *  This is a comparator class for Conferences.
 *  It will compare the conferences by their attendance.
 */

import java.util.Comparator;

public class ConferenceComparatorRegistrations
   implements Comparator<Conference>{
   
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
      
      //Wrap the velues in Integers; they're Comparable
      Integer leftAttendance = new Integer(left.getNumRegistrants());
      Integer rightAttendance = new Integer(right.getNumRegistrants());
      
      //Compare the two, reversing so they sort in the correct order
      return (leftAttendance.compareTo(rightAttendance) * -1);
      
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
      
      return (other instanceof ConferenceComparatorRegistrations);
      
   }
   
}
