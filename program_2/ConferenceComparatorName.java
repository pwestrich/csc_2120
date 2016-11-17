/**
*  ConferenceComparatorName.java
*
*  @author   Philip Westrich
*  @version  November 11, 2013
*
*  This is a comparator class for Conferences.
*  It will compare the conferences by name.
*/

import java.util.Comparator;
//import java.text.Collator;
//import java.text.CollationKey;

public class ConferenceComparatorName /*extends Collator*/ implements Comparator<Conference>{
     
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
          
      String leftName = left.getName();
      String rightName = right.getName();
      
      return compare(leftName, rightName);
          
   }
   
   public int compare(String source, String target){
      
      return source.compareTo(target);
      
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
      
         return (other instanceof ConferenceComparatorName);
      
   }

   /** Not supported. *//*
   public int hashCode(){
      
      throw new UnsupportedOperationException("Not implemented.");
      
   }
   
                         /** Also not supported. *//*
   public CollationKey getCollationKey(String source){
      
      throw new UnsupportedOperationException("Not implemented.");
      
   }
    */
}
