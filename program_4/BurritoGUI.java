/**
*  BurritoGUI.java
*
*  @author  Philip Westrich
*  @version December 12, 2013
*
*  This is the GUI class for the "Decorated Burritos" assignment from earlier
*  in class. It will create and manage the GUI, asking a BurritoBuilder object
*  to build a burrito.
*  
*  It contains four private listener classes to aid in processing the buttons.
*
*  I worked on this assignment by myself, not because I hate everyone in class, 
*  but because I thought it would be easier that way. Besides, no one asked me anyway.
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.LinkedList;
import burrito.*;

public class BurritoGUI extends JFrame {
   
   //Stuff for text area
   private JTextArea infoArea;         //bottom text area
   private JScrollPane textScroller;   //Scroll pane for text area
   
   private JPanel buttonPanel;         //top panel for all buttons
   
   //Stuff for starting buttons
   private JPanel startPanel;          //panel for starting buttons
   private JLabel startLabel;          //label for startPane
   private StartListener sListener;    //Listener for starting buttons
   private JButton makeYourOwn;        //Make your Own button
   private JButton makeVeggie;         //make veggie button
   private JButton makeMeaty;          //make meaty button
   private JButton quitButton;         //quit button
   
   //Stuff for tortilla buttons
   private JPanel tortillaPanel;       //panel for torrtilla buttons
   private JLabel tortillaLabel;       //label for torilla pane
   private TortillaListener tListener; //Listener for tortilla buttons
   private JButton spinachButton;      //button to add spinach
   private JButton wheatButton;        //button to add wheat
   private JButton chipotleButton;     //button to add chipotle
   
   //Stuff for filler buttons
   private JPanel fillerPanel;         //panel for filler buttons
   private JLabel fillerLabel;         //label for filler panel
   private FIllerListener fListener;   //Listener for filler buttons
   private JButton chickenButton;      //button to add chicken
   private JButton steakButton;        //button to add steak
   private JButton beanButton;         //button to add beans
   private JButton fillerDoneButton;   //done button

   //Stuff for condiment buttons
   private JPanel condimentPanel;      //panel for condiment buttons
   private JLabel condimentLabel;      //label for condimment panel
   private condimentListener cListener;//Listener for condiment buttons
   private JButton picoButton;         //button to add pico
   private JButton guacamoleButton;    //button to add guacamole
   private JButton jalapenoButton;     //button to add jalapeno
   private JButton onionButton;        //button to add onion
   private JButton condimentDoneButton;//done button for condiments
   
   //other things needed
   private BurritoBuilder bb;          //Object that will build burritos
   private double totalPrice;          //Total price of all burritos
   private int numBurritos;            //number of burritos
   
   //Minimum size of window
   private final Dimension MIN_SIZE = new Dimension(710, 370);
   
   //Maximun size of window (hope no one has a 4K monitor...)
   private final Dimension MAX_SIZE = new Dimension(1920, 1080);
   
   /**
    * Only constructor. Initializes the object, including all buttons, 
    * Listeners, labels, panels, and such. <br>
    */
   
   public BurritoGUI(){
      
      //Set up the object
      super("CSC 2120 Assignemnt #4: BurritoGUI"); //Call parent constructor
      this.setLayout(new BorderLayout()); //Set this object's layout
      this.setSize(700, 700); //size of window (x, y)
      this.setMinimumSize(MIN_SIZE); //don't let user screw up window
      this.setMaximumSize(MAX_SIZE); //don't let them screw it up this way either
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //quit when window is closed
      bb = null; //don't set it just yet
      totalPrice = 0.0;
      numBurritos = 0;
      
      //Set up the top panel
      buttonPanel = new JPanel();
      buttonPanel.setLayout(new GridLayout(4, 1));
      buttonPanel.setPreferredSize(new Dimension (700, 180));
      
      //set up the subpanels
      initStartPanel();
      initTortillaPanel();
      initFillerPanel();
      initCondimentPanel();
      
      //Add the finished top panel to the object
      this.add(buttonPanel, BorderLayout.NORTH);
      
      //Initalize text area
      infoArea     = new JTextArea();
      infoArea.setEditable(false); //don't let user type stuff in the box
      textScroller = new JScrollPane(infoArea);
      this.add(textScroller, BorderLayout.CENTER);
      
      //Set panels to visible
      buttonPanel.setVisible(true);
      infoArea.setVisible(true);
      startPanel.setVisible(true);
      tortillaPanel.setVisible(true);
      fillerPanel.setVisible(true);
      condimentPanel.setVisible(true);
      
      //set initial button avalibility
      setStartButtonsEnabled(true);
      setTortillaButtonsEnabled(false);
      setFillerButtonsEnabled(false);
      setCondimentButtonsEnabled(false);
      
      //GUI is ready, set visible
      this.setVisible(true);
      
   }
   
   /**
    * Sets up the first panel:   <br>
    * Initalizes all buttons     <br>
    * Creates all frames         <br>
    * Creates the listener object<br>
    * Registers the listener with all buttons
    */
   
   private void initStartPanel(){
      
      //set up starting panel, buttons, and label
      startPanel  = new JPanel();
      sListener   = new StartListener();
      startLabel  = new JLabel("What would you like to do?");
      makeYourOwn = new JButton("Make Your Own");
      makeMeaty   = new JButton("Spicy Meaty");
      makeVeggie  = new JButton("Spicy Veggie");
      quitButton  = new JButton("Save and quit");
      
      //register Listener with buttons
      makeYourOwn.addActionListener(sListener);
      makeMeaty.addActionListener(sListener);
      makeVeggie.addActionListener(sListener);
      quitButton.addActionListener(sListener);
      
      //add buttons and label to panel
      startPanel.add(startLabel);
      startPanel.add(makeYourOwn);
      startPanel.add(makeMeaty);
      startPanel.add(makeVeggie);
      startPanel.add(quitButton);
      
      buttonPanel.add(startPanel); //add start panel to button panel
      
   }
   
   /**
    * Sets up the tortilla panel:<br>
    * Initalizes all buttons     <br>
    * Creates all frames         <br>
    * Creates the listener object<br>
    * Registers the listener with all buttons
    */
   
   private void initTortillaPanel(){
      
      //Set up tortilla panel
      tortillaPanel  = new JPanel();
      tListener      = new TortillaListener();
      tortillaLabel  = new JLabel("Pick your tortilla:");
      spinachButton  = new JButton("Spinach");
      wheatButton    = new JButton("Wheat");
      chipotleButton = new JButton("Chipotle");
      
      //register Listener with buttons
      spinachButton.addActionListener(tListener);
      wheatButton.addActionListener(tListener);
      chipotleButton.addActionListener(tListener);
      
      //add label and buttons to panel
      tortillaPanel.add(tortillaLabel);
      tortillaPanel.add(spinachButton);
      tortillaPanel.add(wheatButton);
      tortillaPanel.add(chipotleButton);
      
      buttonPanel.add(tortillaPanel); //add tortilla panel to button panel
      
   }
   
   /**
    * Sets up the filler panel:  <br>
    * Initalizes all buttons     <br>
    * Creates all frames         <br>
    * Creates the listener object<br>
    * Registers the listener with all buttons
    */
   
   private void initFillerPanel(){
      
      //set up filler panel
      fillerPanel      = new JPanel();
      fListener        = new FIllerListener();
      fillerLabel      = new JLabel("Pick your fillers:");
      chickenButton    = new JButton("Grilled Chicken");
      steakButton      = new JButton("Steak");
      beanButton       = new JButton("Black Bean");
      fillerDoneButton = new JButton("Done");
      
      //register Listener with buttons
      chickenButton.addActionListener(fListener);
      steakButton.addActionListener(fListener);
      beanButton.addActionListener(fListener);
      fillerDoneButton.addActionListener(fListener);
      
      //add label and buttons to panel
      fillerPanel.add(fillerLabel);
      fillerPanel.add(chickenButton);
      fillerPanel.add(steakButton);
      fillerPanel.add(beanButton);
      fillerPanel.add(fillerDoneButton);
      
      buttonPanel.add(fillerPanel); //add filler panel to button panel
      
   }
   
   /**
    * Sets up the condiment panel:<br>
    * Initalizes all buttons      <br>
    * Creates all frames          <br>
    * Creates the listener object <br>
    * Registers the listener with all buttons
    */
   
   private void initCondimentPanel(){
      
      //Set up condiment panel
      condimentPanel      = new JPanel();
      cListener           = new condimentListener();
      condimentLabel      = new JLabel("Pick your condiments:");
      picoButton          = new JButton("Pico de Gallo");
      guacamoleButton     = new JButton("Guacamole");
      jalapenoButton      = new JButton("Jalapeno");
      onionButton         = new JButton("Onion");
      condimentDoneButton = new JButton("Done");
      
      //register listerner with buttons
      picoButton.addActionListener(cListener);
      guacamoleButton.addActionListener(cListener);
      jalapenoButton.addActionListener(cListener);
      onionButton.addActionListener(cListener);
      condimentDoneButton.addActionListener(cListener);
      
      //Add buttons to panel
      condimentPanel.add(condimentLabel);
      condimentPanel.add(picoButton);
      condimentPanel.add(guacamoleButton);
      condimentPanel.add(jalapenoButton);
      condimentPanel.add(onionButton);
      condimentPanel.add(condimentDoneButton);
      
      buttonPanel.add(condimentPanel); //add condiment panel to button panel
      
   }
   
   /**
    * Called when exiting the program when the quit button is pressed. <br>
    * It will handle saving the file to disk. <br>
    *
    * It can be cancelled by the user if they hit the cancel button in the file chooser.
    */
   
   private void exitProgram(){
      
      //print final messages
      infoArea.append("\nYou ordered " + numBurritos + " burritos, for a total cost of "
                      + Currency.formatCurrency(totalPrice) + ".\n");
      
      infoArea.append("Exiting program... \nSaving data... \n");
      
      //make a file chooser, set it up
      JFileChooser chooser = new JFileChooser(".");
      chooser.setMultiSelectionEnabled(false);
      chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
      
      //Display the dialog and get its success value
      int returnVal = chooser.showSaveDialog(this);
      
      if (returnVal == JFileChooser.CANCEL_OPTION){
         
         //display cancel message
         infoArea.append("Save cancleled by user. \n");
         return;
         
      }
      
      else if (returnVal == JFileChooser.ERROR_OPTION){
         
         //display error message
         infoArea.append("An error has occured in file selection. \n");
         return;
         
      }
      
      File outputFile = chooser.getSelectedFile();
      FileWriter writer = null;
      
      try { //try to write to output file
         
         writer = new FileWriter(outputFile); //Make writer
         writer.write(infoArea.getText()); //Write contents of text area to document
         writer.close();   //close writer
         System.exit(0);   //exit program
         
      }
         
      catch (IOException it){
         
         infoArea.append("Could not write to the directory: \"\n\t" + outputFile 
                         + "\" \n");
         
      }
      
   }
   
   /**
    * Handles ordering a burrito. <br>
    * It will add the ordered burrito to the list of burrito, and add the
    * proper output to the text window.
    */
   
   private void orderBurrito(){
      
      //Get the order
      BurritoItem orderedBurrito = bb.orderBurrito();
      
      //Display messages
      infoArea.append("\nBurrito ordered: \n");
      infoArea.append(orderedBurrito.toString() + "\n");
      infoArea.append("Price: " + Currency.formatCurrency(orderedBurrito.getPrice()));
      
      //get rid of the builder
      bb = null;
      
      //increment number of orders, add to total price
      numBurritos++;
      totalPrice += orderedBurrito.getPrice();
      
   }
   
   private void setStartButtonsEnabled(boolean status){
      
      //Pass status to all items in the frame
      startLabel.setVisible(status);
      makeYourOwn.setEnabled(status);
      makeMeaty.setEnabled(status);
      makeVeggie.setEnabled(status);
      quitButton.setEnabled(status);
      
   }
   
   private void setTortillaButtonsEnabled(boolean status){
      
      //Pass status to all items in the frame
      tortillaLabel.setVisible(status);
      spinachButton.setEnabled(status);
      wheatButton.setEnabled(status);
      chipotleButton.setEnabled(status);
      
   }
   
   private void setFillerButtonsEnabled(boolean status){
      
      //Pass status to all items in the frame
      fillerLabel.setVisible(status);
      beanButton.setEnabled(status);
      chickenButton.setEnabled(status);
      steakButton.setEnabled(status);
      fillerDoneButton.setEnabled(status);
      
   }
   
   private void setCondimentButtonsEnabled(boolean status){
      
      //Pass status to all items in the frame
      condimentLabel.setVisible(status);
      picoButton.setEnabled(status);
      jalapenoButton.setEnabled(status);
      guacamoleButton.setEnabled(status);
      onionButton.setEnabled(status);
      condimentDoneButton.setEnabled(status);
      
   }
   
   //Inner listener classes for the buttons start here
   
   /** Listener for the first panel. */
   
   private class StartListener implements ActionListener {
      
      /**
       * Detects an action performed from the first panel. <br>
       * It will determine what button was pressed and take the 
       * apropriate action.
       *
       * @param   event ActionEvent from the button
       */
      
      public void actionPerformed(ActionEvent event){
         
         //Get the address of the source button
         Object source = event.getSource();
         
         //find where it came from
         if (source == makeYourOwn){
            
            //make builder
            bb = new BurritoBuilder();
            
            //print message
            infoArea.append("Make Your Own: \n");
            
            //Disable buttons
            setStartButtonsEnabled(false);
            
            //set tortilla buttons to visible
            setTortillaButtonsEnabled(true);
            
         }
         
         else if (source == makeMeaty){
            
            //print message
            infoArea.append("Ordering Spicy Meaty burrito. \n");
            
            //make builder
            bb = new SpicyMeaty();
            
            //order
            orderBurrito();
            
         }
         
         else if (source == makeVeggie){
            
            //Print message
            infoArea.append("Ordering Spicy Veggie burrito. \n");
            
            //make builder
            bb = new SpicyVeggie();
            
            //order
            orderBurrito();
            
         }
         
         else if (source == quitButton){
            
            //Exit program
            exitProgram();
            
         }
         
         else { //something really bad has happened if we get here
            
            throw new RuntimeException("A fatal error has occured.");
            
         }
         
      }
      
   }
      
   /** Listener for the buttons in the Tortilla pane.*/
   
   private class TortillaListener implements ActionListener {
      
      /**
       * Determines the source of the incoming ActionEvent and takes
       * the appropriate action, changing control to another pane if needed. <br>
       *
       * @param   event    ActionEvent generated by a button in the pane
       *
       * @throws  RuntimeException  if, for some reason, a button pressed isn't
       *                            one of the buttons in the pane.
       */
      
      public void actionPerformed(ActionEvent event){
         
         Object source = event.getSource();
         
         if (source == spinachButton){
            
            bb.buildTortilla('S');
            infoArea.append("Spinach tortilla ordered.\n");
            
         }
         
         else if (source == wheatButton){
            
            bb.buildTortilla('W');
            infoArea.append("Wheat tortilla ordered.\n");
            
         }
         
         else if (source == chipotleButton){
            
            bb.buildTortilla('C');
            infoArea.append("Chipotle tortilla ordered.\n");
            
         }
         
         else { //something really bad has happened if we get here
            
            throw new RuntimeException("A fatal error has occured.\n");
            
         }
         
         //disable tortilla buttons
         setTortillaButtonsEnabled(false);
         
         //enable filler buttons
         setFillerButtonsEnabled(true);
         
      }
      
   }
   
   /** Listener for the buttons in the Filler pane */
   
   private class FIllerListener implements ActionListener {
      
      /** Has at least one filler been selected? */
      private boolean atLeastOne;
      
      /**
       * Determines the source of the incoming ActionEvent, and takes the 
       * appropriate action, changing control to the next pane if needed. <br>
       *
       * @param   event    ActionEvent generated by a button in the Filler pane
       *
       * @throws  RuntimeException  if, for some reason, a button pressed isn't
       *                            one of the buttons in the Filler pane.
       */
      
      public void actionPerformed(ActionEvent event){
         
         Object source = event.getSource();
         
         if (source == chickenButton){
            
            atLeastOne = true;
            bb.buildFiller('C');
            infoArea.append("Grilled chicken added.\n");
            
         }
         
         else if (source == steakButton){
            
            atLeastOne = true;
            bb.buildFiller('S');
            infoArea.append("Steak added.\n");
            
         }
         
         else if (source == beanButton){
            
            atLeastOne = true;
            bb.buildFiller('B');
            infoArea.append("Black bean added.\n");
            
         }
         
         else if (source == fillerDoneButton){
            
            if (!atLeastOne) return; //do nothing
            
            //otherwise, change to next panel
            fillerPanel.setEnabled(false);
            condimentPanel.setEnabled(true);
            
            //reset boolean
            atLeastOne = false;
            
            //disable filler buttons
            setFillerButtonsEnabled(false);
            
            //enable condiment buttons
            setCondimentButtonsEnabled(true);
            
         }
         
         else { //something really bad has happened if we get here
            
            throw new RuntimeException("A fatal error has occured.");
            
         }
         
      }
      
   }
      
   /** Listener for the buttons in the Condiment pane */
   
   private class condimentListener implements ActionListener {
      
      /**
       * Determines the source of the incoming ActionEvent, and takes the 
       * appropriate action, changing control to the next pane if needed. <br>
       *
       * @param   event    ActionEvent generated by a button in the Condiment pane
       *
       * @throws  RuntimeException  if, for some reason, a button pressed isn't
       *                            one of the buttons in the Condiment pane.
       */
      
      public void actionPerformed(ActionEvent event){
         
         //get the source of the event
         Object source = event.getSource();
         
         //find where it came from
         if (source == picoButton){
            
            picoButton.setEnabled(false); //make button unusable
            bb.buildCondiment('P');       //build pico
            infoArea.append("Pico de Gallo added.\n");
            
         }
         
         else if (source == jalapenoButton){
            
            jalapenoButton.setEnabled(false);   //make button unusable
            bb.buildCondiment('J');
            infoArea.append("Jalapeno added.\n");
            
         }
         
         else if (source == guacamoleButton){
                        
            guacamoleButton.setEnabled(false);  //make button unusable
            bb.buildCondiment('G');
            infoArea.append("Guacamole added.\n");
            
         }
         
         else if (source == onionButton){
                        
            onionButton.setEnabled(false);   //make button unusable
            bb.buildCondiment('O');
            infoArea.append("Onion added.\n");
            
         }
         
         else if (source == condimentDoneButton){
            
            //order the burrito
            orderBurrito();
            
            //disable condiment buttons
            setCondimentButtonsEnabled(false);
            
            //enable start buttons
            setStartButtonsEnabled(true);
            
         }
         
         else { //something really bad has happened if we get here
            
            throw new RuntimeException("A fatal error has occured.");
            
         }
         
      }
      
   }
      
   /**
    * The main method. <br>
    * Creates a BurritoGUI object.
    *
    * @param   args  Ignored.
    */
   
   public static void main(String[] args){
      
      //create GUI
      BurritoGUI gui = new BurritoGUI();
      
   }
   
}
