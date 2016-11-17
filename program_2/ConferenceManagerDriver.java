/**
*    ConfrenceManagerDriver.java
*
*    @author   Philip Westrich
*    @version  November 09, 2013
*
*    This is the driver for the ConferenceManager and associated
*    classes. It will handle all user input.
*/

public class ConferenceManagerDriver{
     
     /** The ConferenceManager object. */
     public static ConferenceManager manager;
     
     /**
      *   The main method. Handles all delegation of tasks.
      *
      *   @param    args      Command-line arguments. Are ignored.
      */
     
     public static void main(String[] args){
          
          manager = new ConferenceManager();
          
          System.out.println("Welcome to the ConferenceManager!");
          System.out.println("This program will manage a number of conferences.\n");
          
          String choice = null; //Choice made by user
          
          do {
               
               choice = menu(); //Display the menu
               
               if (choice == null){
                    
                    System.out.println("Invalid input! Try again.");
                    continue;
                    
               }
               
               else if (choice.equalsIgnoreCase("Q")){
                    
                    break;
                    
               }
               
               else if (choice.equalsIgnoreCase("+")){
                    
                    addConference();
                    
               }
               
               else if (choice.equalsIgnoreCase("F")){
                    
                    readFile();
                    
               }
               
               else if (choice.equalsIgnoreCase("W")){
                    
                    writeFile();
                    
               }
             
               else if (choice.equalsIgnoreCase("L")){
                  
                  listAll();
                  
               }
               
               else if (choice.equalsIgnoreCase("S")){
                    
                    
                    printStatus();
               }
               
               else if (choice.equalsIgnoreCase("U")){
                    
                    submit();
                    
               }
               
               else if (choice.equalsIgnoreCase("G")){
                    
                    register();
                    
               }
                        
               else if (choice.equalsIgnoreCase("C")){
                    
                    cancel();
                    
               }
               
               else if (choice.equalsIgnoreCase("X")){
                    
                    close();
                    
               }
               
               else if (choice.equalsIgnoreCase("A")){
                    
                    accept();
                    
               }
               
               else if (choice.equalsIgnoreCase("R")){
                    
                    reject();
                    
               }
               
               else if (choice.equalsIgnoreCase("$")){
                    
                    revenue();
                    
               }
               
               else if (choice.equalsIgnoreCase("#")){
                    
                    attendance();
                    
               }
               
               else if (choice.equalsIgnoreCase("V")){
                    
                    avgRevenue();
                    
               }
               
               else if (choice.equalsIgnoreCase("*")){
                    
                    sort();
                    
               }
               
               else {
                    
                    System.out.println("Invalid Input!");
                    
               }
               
               
          } while (true);
          
     }
     
     /**
      *   Displays the menu for the program.
      *
      *   @return   String    The choice made by the user.
      */
     
     public static String menu(){
          
          System.out.println("MENU");
          System.out.println("------------------------------");
          System.out.println("+ : Add a conference");
          System.out.println("F : Read conferences from file");
          System.out.println("W : Write conferences to file");
          System.out.println("L : List all conferences");
          System.out.println("S : Print status of a conference");
          System.out.println("U : Submit");
          System.out.println("G : Register for a conference");
          System.out.println("C : Cancel a registration");
          System.out.println("X : Close a conference");
          System.out.println("A : Accept a submission");
          System.out.println("R : Reject a submission");
          System.out.println("$ : Display revenue for a conference");
          System.out.println("# : Display projected attendance for a conference");
          System.out.println("V : Display average revenue per attendee");
          System.out.println("* : Sort conferences by a value");
          System.out.println("Q : Quit the program");
          
          return Keyboard.readString("Your choice: ");
                             
     }
     
     /**
      *   Prompts user for input, checks it, then proccesses it by adding
      *   a conference with the given data to the manager.
      */
     
     public static void addConference(){
          
          boolean dataBad = false;
          
          //Ask user for data
          String name = Keyboard.readString("Please enter the name of the conference: ");
          String chair = Keyboard.readString("Please enter the name of the chair: ");
          String startDate = Keyboard.readString("Please enter the start date (yy/mm/dd): ");
          String endDate = Keyboard.readString("Please enter the end date (yy/mm/dd): ");
          int maxAttendees = Keyboard.readInt("Please enter the max number of attendees: ");
          double fee = Keyboard.readDouble("Please enter the conference fee: ");
          boolean isCancelFee = Boolean.parseBoolean(Keyboard.readString("Is ther a cancellation fee? (Y/N): "));
          
          try { //Try to add conference
               
               manager.add(name, chair, startDate, endDate, maxAttendees, fee, isCancelFee);
               
          }
          
          catch (ConferenceManagerException it){
               
               System.out.println(it.getMessage());
               System.out.println("Conference not added.\n");
               
          }
          
     }
     
     public static void readFile(){
          
          String filename = Keyboard.readString("Please enter the filename: ");
          
          try {
               
               manager = new ConferenceManager(filename);
               
          }
          
          catch (ConferenceManagerException it){
               
               System.out.println(it.getMessage());
               
          }
          
     }
     
     public static void writeFile(){
          
          String filename = Keyboard.readString("Please enter the filename: ");
          
          try {
          
               manager.writeToObjectFile(filename);
          
          }
          
          catch (ConferenceManagerException it){
               
               System.out.println(it.getMessage());
               
          }
        
     }
   
      public static void listAll(){
      
         System.out.println(manager);
      
      }
     
     public static void printStatus(){
          
        String input = Keyboard.readString("Enter the name of the conference: ");
        
        String info = manager.getConferenceInfo(input);
        
        System.out.println(info);
        
     }
     
     public static void submit(){
          
          Submission sub = null;
          
          String conferenceName = Keyboard.readString("Please enter the name of the conference: ");
          String author = Keyboard.readString("Please enter the name of the author: ");
          String title = Keyboard.readString("Please enter the title: ");
          String type = Keyboard.readString("Poster or paper? Y/y for poster, anything else for paper: ");
          
          try {
          
               if (type.equalsIgnoreCase("Y")){
               
                    String desc = Keyboard.readString("Please enter the poster's description: ");
                    desc += "\n";
                    
                    sub = new Poster(author, title, desc);
               
               }
          
               else {
                    
                    String subject = Keyboard.readString("Please enter the paper's subject: ");
                    String url = Keyboard.readString("Please enter the paper's URL: ");
                    int numPages = Keyboard.readInt("Please enter the number of pages: ");
               
                    sub = new Paper(author, title, subject, url, numPages);
               
               }
               
               System.out.println(manager.submit(conferenceName, sub));
               
          }
          
          catch (SubmissionException it){
               
               System.out.println(it.getMessage());
               
          }
          
     }
     
     public static void register(){
          
          String conferenceName = Keyboard.readString("Please enter the name of the conference: ");
          String name = Keyboard.readString("Please enter the name of the registrant: ");
          
          System.out.println(manager.register(conferenceName, name));
          
     }
     
     public static void cancel(){
          
          String conferenceName = Keyboard.readString("Please enter the name of the conference: ");
          String name = Keyboard.readString("Please enter the name of the registrant: ");
          
          System.out.println(manager.cancel(conferenceName, name));
          
     }
     
     public static void close(){
          
          String conferenceName = Keyboard.readString("Please enter the name of the conference: ");
          int choice = Keyboard.readInt("Choose 1 for submissions, anything else for registrations: ");
          
          if (choice == 1){
               
               if (manager.closeSubmissions(conferenceName)){
                    
                    System.out.println("Success!");
                    
               }
               
               else {
                    
                    System.out.println("Failure!");
                    
               }
               
          }
          
          else {
               
               System.out.println(manager.closeRegistrations(conferenceName));
               
          }
          
     }
     
     public static void accept(){
          
          String conferenceName = Keyboard.readString("Please enter the name of the conference: ");
          int subNum = Keyboard.readInt("Please enter the submission number: ");
          
          System.out.println(manager.accept(conferenceName, subNum));
          
     }
     
     public static void reject(){
          
          String conferenceName = Keyboard.readString("Please enter the name of the conference: ");
          int subNum = Keyboard.readInt("Please enter the submission number: ");
          
          System.out.println(manager.reject(conferenceName, subNum));
          
     }
     
     public static void revenue(){
          
          System.out.println("Total: " + Currency.formatCurrency(manager.getTotalRevenue()));
          
     }
     
     public static void attendance(){
          
          System.out.println("Total projected attenance: " + manager.getTotalAttendance());
          
     }
     
     public static void avgRevenue(){
          
          System.out.println("Average $ per person: "
                             + Currency.formatCurrency(manager.getAvgRevenuePerHead()));
          
     }
     
     public static void sort(){
          
          System.out.println("1. conference name (asc)");
          System.out.println("2. conference revenue (desc)");
          System.out.println("3. conference registrations (asc)");
          int field = Keyboard.readInt("Please enter the sort field (1, 2, or 3): ");
          
          System.out.println("1. Selection sort");
          System.out.println("2. Insertion sort");
          System.out.println("3. Bubble sort");
          int alg = Keyboard.readInt("Please enter the algorithm number (1, 2, or 3): ");
        
        try {
           
           manager.sort(field, alg);
           
        }
        
        catch (ConferenceManagerException it){
           
           System.out.println(it.getMessage());
           
        }
        
     }
     
}
