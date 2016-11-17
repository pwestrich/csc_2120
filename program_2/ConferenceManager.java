/**
 *   ConferenceManager.java
 *
 *   @author   Philip Westrich
 *   @version  November 9, 2013
 *
 *   This is the class that will sort and managage an arbitrary
 *   number of Confrences.
 */

import java.io.Serializable;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class ConferenceManager implements Statable, Serializable {
     
     /** ArrayList of conferences. */
     private ArrayList<Conference> conferences;

     /** Name of this manager. */
     private String managerName;
   
     /** Name comparator */
     private static ConferenceComparatorName nameComparator = new ConferenceComparatorName();
   
     /** registration comparator */
     private static ConferenceComparatorRegistrations registrationComparator
                                                   = new ConferenceComparatorRegistrations();
     /** Revenue comparator */
     private static ConferenceComparatorRevenue revenueComparator = new ConferenceComparatorRevenue();
   
     /** Current comparator */
     private Comparator<Conference> comparer;
   
     /** Max number of conferences allowed. */
     public static final int MAX_CONFERENCES = 100;
     
     /** Default constructor. Initalizes the object. */
     public ConferenceManager(){
          
          conferences = new ArrayList<Conference>();
          managerName = "None";
          comparer = null;
        
     }
     
     /**
      *   Initializes the object and reads data from the file with 
      *   the given name.
      *
      *   @param    filename  Name of file to be read from.
      *
      *   @throws   ConferenceManagerException if the filename is invalid,
      *             or otherwise bad.
      */
     
     public ConferenceManager(String filename) throws ConferenceManagerException {
        
        this();
        
        FileInputStream inFile = null;
        ObjectInputStream in = null;
        
        try {
           
           inFile = new FileInputStream(filename);
           in = new ObjectInputStream(inFile);
           
           managerName = (String) in.readObject();
           
           int numItems = (int) in.readInt();
           
           for (int i = 0; i < numItems; i++){
              
              conferences.add((Conference) in.readObject());
              
           }
           
        }
        
        catch (NullPointerException it){
           
           throw new ConferenceManagerException("Error reading file: " + it);
           
        }
        
        catch (IOException it){
           
           throw new ConferenceManagerException("Error reading file: " + it);
           
        }
        
        catch (ClassNotFoundException it){
           
           throw new ConferenceManagerException("Bad data in file!");
           
        }
        
     }
     
     /**
      *   Sets the name of the conference manager.
      *
      *   @param    mgr  Name of manager.
      */
     
     public void setManager(String mgr){
          
          managerName = mgr;
          
     }
     
     /**
      *   Creates a conference with the given parameters and adds it to the manager.
      *
      *   @param    name           Name of conference
      *   @param    chair          Name of chairperson
      *   @param    start          Start of conference
      *   @param    end            End of conference
      *   @param    max            Max number of attendees
      *   @param    fee            Cost of conference attendance
      *   @param    isCancelFree   Is cancellation free?
      *
      *   @return   Success of operation
      *
      *   @throws   ConferenceException if data is bad.
      */
     
     public boolean add(String name, String chair, String start, String end, int max,
                        double fee, boolean isCancelFree)
                        throws ConferenceManagerException {
          
          if (Utilities.invalidDate(start)){
               
               throw new ConferenceManagerException("Invalid date: " + start + "\nDate " +
                                                    "must be in yy/mm/dd format, and be " +
                                                    "actual dates on the calendar.");
               
               
          }
          
          if (Utilities.invalidDate(end)){
               
               throw new ConferenceManagerException("Invalid date: " + end +
                                                    "Dates must be in yy/mm/dd format, and be actual"
                                                    + "dates on the calendar.");
               
               
          }
          
          if (Utilities.datesInvalid(start, end)){
               
               throw new ConferenceManagerException("Invalid dates!" +
                                                    "Start date must be before end date");
               
          }
          
          if (max <= 0){
               
               throw new ConferenceManagerException("Invalid number of atendees: " +
                                                    max +
                                                    "Must be greater than zero!");
               
          }
          
          if (fee < 0.0) {
               
               throw new ConferenceManagerException("Invalid fee: " + fee +
                                                    "Must be greater than zero!");
               
          }
          
          try {
          
               conferences.add(new Conference(name, chair, start, end, max, fee,
                                              isCancelFree));
               return true;

          }
          
          catch (ConferenceException it){
               
               throw new ConferenceManagerException("Invalid data!");
               
          }
          
     }
     
     /**
      *   Adds a conference to the manager
      *
      *   @param    conf The conference to be added.
      *
      *   @return   boolean Success or failure of operation.
      *
      *   @throws   ConferenceExeption if item passed in is null
      */
     
     public boolean add(Conference conf) throws ConferenceException{
          
          if (conf == null) throw new ConferenceException("Invalid conference!");
          
          conferences.add(conf);
          
          return true;
          
     }
     
     /**
      *   Saves the object to a text file.
      *
      *   @param    filename  Name of file for data to be stored.
      */
     
     public void writeStateToTextFile(String filename) throws ConferenceManagerException {
          
          try { //Try to open file
               
               FileIO outFile = new FileIO(filename, FileIO.FOR_WRITING);
             
               outFile.writeLine("Manager name: " + managerName);
             
               for (Conference item : conferences){ //Write each conference to the file
                    
                    outFile.writeLine(item.toString());
                    
               }
          
          }
          
          catch (FileIOException it){ //Catch exception, pass it along
               
               throw new ConferenceManagerException(it.toString());
               
          }
          
     }
     
     /**
      *   Saves the manager to an object file.
      *
      *   @param    filename  Name of file for data to be stored.
      *
      *  @throws  ConferenceManagerException    if error writing file
      */
     
     public void writeToObjectFile(String filename) throws ConferenceManagerException {
        
        FileOutputStream outFile = null;
        ObjectOutputStream out = null;
        
        try {
           
           outFile = new FileOutputStream(filename);
           out = new ObjectOutputStream(outFile);
           
           out.writeObject(managerName);
           out.writeInt(conferences.size());
           
           for (Conference item : conferences){
              
              out.writeObject(item);
              
           }
           
        }
        
        catch (NullPointerException it){
           
           throw new ConferenceManagerException("Error writing to file: " + it);
           
        }
        
        catch (IOException it){
           
           throw new ConferenceManagerException("Error reading file: " + it);
           
        }
        
     }
     
     /**
      *   Submits a submission to the given conference
      *
      *   @param    confName  Name of conference to be submitted
      *   @param    sub       Submission to be submitted
      *
      *   @return   String    Message with result of operation
      */
     
     public String submit(String confName, Submission sub){
          
          try { //Try to find confernce
               
               Conference item = conferences.get(findConference(confName));
               return item.submit(sub);
               
          }
          
          catch (IndexOutOfBoundsException it){
               
               return "Conference " + confName + " does not exist.";
               
          }
          
     }
     
     /**
      *   Registers a person to a conference
      *
      *   @param    confName  Conference to be registered to.
      *   @param    regName   Name of person to be registered
      *
      *   @return   String    Message with result of operation
      */
     
     public String register(String confName, String regName){
          
          try { //Try to find confernce
          
               Conference item = conferences.get(findConference(confName));
               return item.register(regName);

          }
               
          catch (IndexOutOfBoundsException it){
                    
               return "Conference " + confName + " does not exist.";
                    
          }
          
     }
     
     /**
      *   Accepts a submission by number for the given conference
      *
      *   @param    confName  Name of conference
      *   @param    subNum    Number of submission to be accepted
      *
      *   @return   String    Message with result of operation
      */
     
     public String accept(String confName, int subNum){
          
          try { //Try to find confernce
               
               Conference item = conferences.get(findConference(confName));
               return item.accept(subNum);
               
          }
          
          catch (IndexOutOfBoundsException it){
               
               return "Conference " + confName + " does not exist.";
               
          }
          
     }
     
     /**
      *   Rejects a submission by number for the given conference
      *
      *   @param    confName  Name of conference
      *   @param    subNum    Number of submission
      *
      *   @return   String    Message with the result of the operation
      */
     
     public String reject(String confName, int subNum){
          
          try { //Try to find confernce
               
               Conference item = conferences.get(findConference(confName));
               return item.reject(subNum);
               
          }
          
          catch (IndexOutOfBoundsException it){
               
               return "Conference " + confName + " does not exist.";
               
          }
          
     }
     
     /**
      *   Closes submissions for a conference.
      *
      *   @param    confName  Name of confernce
      *
      *   @return   boolean   Success of operation.
      */
     
     public boolean closeSubmissions(String confName){
          
          try { //Try to find confernce
               
               Conference item = conferences.get(findConference(confName));
               item.closeSubmissions();
               return true;
               
          }
          
          catch (IndexOutOfBoundsException it){
               
               return false;
               
          }
          
     }
     
     /**
      *   Closes registrations for a conference.
      *
      *   @param    confName  Name of conference to be closed
      *
      *   @return   String    Message with result of operation
      */
     
     public String closeRegistrations(String confName){
          
          try { //Try to find confernce
               
               Conference item = conferences.get(findConference(confName));
               return item.closeRegistrations();
               
          }
          
          catch (IndexOutOfBoundsException it){
               
               return "Conference " + confName + " does not exist.";
               
          }
          
     }
     
     /**
      *   Cancels someone's registration for a conference
      *
      *   @param    confName  Name of conference
      *   @param    who       Who is cancelling
      *
      *   @return   String    Message with result of operation
      */
     
     public String cancel(String confName, String who){
          
          try { //Try to find confernce
               
               Conference item = conferences.get(findConference(confName));
               return item.cancel(who);
               
          }
          
          catch (IndexOutOfBoundsException it){
               
               return "Conference " + confName + " does not exist.";
               
          }
          
     }
     
     /**
      *   Gets toe total revenue from all conferences.
      *
      *   @return   double    Total revenue
      */
     
     public double getTotalRevenue(){
          
          double totalRevenue = 0.0;
          
          for (Conference item : conferences){
               
               totalRevenue += item.getRevenue();
               
          }
          
          return totalRevenue;
          
     }
     
     /**
      *   Gets the total number of people attending
      *
      *   @return   int  Number of people attending
      */
     
     public int getTotalAttendance(){
          
          int numAttendees = 0;
          
          for (Conference item : conferences){
               
               numAttendees += item.getNumRegistrants();
               
          }
          
          return numAttendees;
          
     }
     
     /**
      *   Calculates the revenue per person.'
      *
      *   @return   (Total Revenue) / (Total Attendance).
      */
     
     public double getAvgRevenuePerHead(){
          
          return (getTotalRevenue() / ((double) getTotalAttendance()));
          
     }
     
     /**
      *   Gets the state of the manager
      *
      *   @return   String    State of the manager
      */
     
     public String getState(){
          
          if (conferences.isEmpty()){
               
               return "Manager is empty.";
               
          }
          
          String returnValue = "";
          
          for (Conference item : conferences){
               
               returnValue += item.getState();
               returnValue += "\n";
               
          }
          
          return returnValue;
          
     }
     
     /**
      *   Gets the state of the object in String form
      *
      *   @return   String    State of the object.
      */
     
     public String toString(){
          
          if (conferences.isEmpty()){
               
               return "Manager is empty.";
               
          }
          
          String returnValue = "Manager name: " + managerName + "\n";
          
          for (Conference item : conferences){
               
               returnValue += item.toString();
               returnValue += "\n";
               
          }
          
          return returnValue;
          
     }
     
     /**
      *   Gets the number of conferences
      *
      *   @return   int  Number of conferences
      */
     
     public int getNumConferences(){
          
          return conferences.size();
          
     }
   
   public String getConferenceInfo(String info){
      
      int location = findConference(info);
      
      if (location == -1){
         
         return "Conference " + info + " not found.";
         
      }
      
      else return conferences.get(location).toString();
      
   }
     
     /**
      *   Sorts the conferences.
      *
      *   @param    field     Field to sort by (1, 2, or 3)
      *   @param    alg       Algorithm to sort by (1, 2, or 3)
      *
      *   @throws   ConferenceManagerException    if data or field is bad
      */
     
     public void sort(int field, int alg) throws ConferenceManagerException {
        
        comparer = getComparator(field);
        
        if (comparer == null){
           
           throw new ConferenceManagerException("Invalid field: " + field);
           
        }
        
        if (conferences.size() == 0){
           
           throw new ConferenceManagerException("No conferences to sort.");
           
        }
        
        Conference[] items = new Conference[conferences.size()];
        int i = 0;
        
        for (Conference item : conferences){ //Put conferences in array to sort
           
           items[i] = item;
           i++;
           
        }
        
        switch(alg){
              
           case 1:
              
              Utilities.<Conference>selectionSort(items, comparer);
              break;
              
           case 2:
              
              Utilities.<Conference>insertionSort(items, comparer);
              break;
              
           case 3:
              
              Utilities.<Conference>bubbleSort(items, comparer);
              break;
              
           default:
              
              throw new ConferenceManagerException("Invalid algorithm:" + alg);
              
              
        }
        
        conferences = new ArrayList<Conference>();
        
        for (Conference item : items){
           
           conferences.add(item);
           
        }
          
     }
     
     /**
      *   Finds a conference given its name
      *
      *   @param    name      Name of confernce to find
      *
      *   @return   int       The index of the item, or -1 if not found.
      */
     
     private int findConference(String name){
          
          Conference current = null;
          
          for (int i = 0; i < conferences.size(); i++){
               
               current = conferences.get(i);
               
               if (current.getName().equals(name)){
                    
                    return i; //Item found!
                    
               }
               
          }
          
          return -1; //Item not found.
          
     }
   
   /**
    * Gets the correct comparator for the desired field.
    *
    * @param   field    Field number
    *
    * @return  Comparator<Conference>  Comparator needed, null if bad data given.
    */
   
   private Comparator<Conference> getComparator(int field){
      
      switch(field){
            
         case 1:
            
            return nameComparator;
            
         case 2:
            
            return revenueComparator;
            
         case 3:
            
            return registrationComparator;
            
         default:
            
            return null;
            
      }
      
   }

}
