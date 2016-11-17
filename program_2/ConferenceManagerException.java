
/**
 *   ConferenceManagerException.java
 *
 *   @author   Philip Westrich
 *   @version  November 9, 2013
 *
 *   This is the exception class for the ConferenceManager.
 *   It is a checked exception.
 *
 *   @see Exception
 */

public class ConferenceManagerException extends Exception {

     /**
      *   Only constructor. Passes message to parent.
      *
      *   @param    message   Message to be used
      */
     
     public ConferenceManagerException(String message){
          
          super(message);
          
     }

}
