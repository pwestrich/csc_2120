
/**
*    ConferenceException.java
*
*    @author   Philip Westrich
*    @version  November 9, 2013
*
*    This is the exception for the Conference class.
*    Is a checked exception.
*
*    @see Exception
*/

public class ConferenceException extends Exception{
     
     /**
      *   Only constructor. Passes message to parent.
      *
      *   @param    message   Message to be stored
      */
     
     public ConferenceException(String message){
          
          super(message);
          
     }
     
}
